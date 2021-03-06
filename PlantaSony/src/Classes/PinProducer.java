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
public class PinProducer extends Thread{
    public boolean running = false;
    protected PinProductionLine productLine;
    
    protected long productionTime;
    public String currentStatus;
    public boolean working = false;
    public int salary;
    public long eggingTime;
    
    PinProducer(PinProductionLine productLine, int tiempoProduccion){
        this.productionTime = tiempoProduccion;
        this.productLine = productLine;
    }
    
    public void stopRun(){
        this.running = false;
    }
    
    @Override 
    public void run(){
        this.running = true;
        while (running){
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
                this.productLine.updateHueving(difference);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.interrupt();
    }
}
