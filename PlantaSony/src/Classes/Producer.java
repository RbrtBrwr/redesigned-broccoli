/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rober
 */
public class Producer extends Thread{
    private final ProductionLine productLine;
    
    private final long productionTime;
    private final int breakTime;
    public String currentStatus;
    
    public String whereTo;
    
    Producer(ProductionLine productLine, int dayLength, float daysPerProduct){
        this.breakTime = dayLength / 12;
        this.productionTime = (long) (20 / daysPerProduct);
        this.productLine = productLine;
    }
    
    public void timeForWork(){
        this.currentStatus = "Ocioso";
        try {   
            this.productLine.capacitySem.acquire();
            this.currentStatus = "Trabajando";
            Thread.sleep(this.productionTime);
            this.productLine.addToStock();
            this.timeForBreak();
        } catch (InterruptedException ex) {
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void timeForBreak(){
        this.currentStatus = "Descanso";
        try {
            Thread.sleep(this.breakTime);
            this.timeForWork();
        } catch (InterruptedException ex) {
            Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override 
    public void run(){
        while (true){
            this.timeForWork();
        }
            
    }
}
