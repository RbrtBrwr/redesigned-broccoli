/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rober
 */
public class ScreenProducer extends Thread{
    protected ScreenProductionLine productLine;
    
    protected long productionTime;
    public String currentStatus;
    public boolean working = false;
    public int salary;
    public long eggingTime;
    
    ScreenProducer(ScreenProductionLine productLine, int tiempoProduccion){
        this.productionTime = tiempoProduccion;
        this.productLine = productLine;
        this.eggingTime = 0;
        this.salary = 3;
    }
    
    @Override 
    public void run(){
        while (true){
            long start = System.currentTimeMillis();
            this.currentStatus = "Ocioso";
            working = false;
            try {   
                this.productLine.capacitySem.acquire();
                working = true;
                this.currentStatus = "Trabajando";
                Thread.sleep(this.productionTime);
                this.productLine.addToStock();
                long end = System.currentTimeMillis();
                long difference = end - start - this.productionTime;
                this.eggingTime += difference;
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
