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
    public String currentStatus;
    public boolean working = false;
    public String whereTo;
    
    Producer(ProductionLine productLine, int dayLength, float daysPerProduct){
        this.productionTime = (long) (24 / daysPerProduct);
        this.productLine = productLine;
    }
    
    public void timeForWork(){
        this.currentStatus = "Ocioso";
        working = false;
        try {   
            this.productLine.capacitySem.acquire();
            working = true;
            this.currentStatus = "Trabajando";
            Thread.sleep(this.productionTime);
            this.productLine.addToStock();
            timeForWork();
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
