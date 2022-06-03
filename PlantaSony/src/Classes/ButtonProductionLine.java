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
public class ButtonProductionLine{
    protected int capacity;
    protected volatile int stock;
    public volatile int huevingTime;
    
    public Semaphore capacitySem;
    public Semaphore stockSem = new Semaphore(1);
    public Semaphore retrieveSem = new Semaphore(0);
    public Semaphore hueving = new Semaphore(1);
    
    public String whereTo = "Button";
    
    ButtonProductionLine(int capacity, int stock){
        this.capacity = capacity;
        this.stock = stock;
        this.capacitySem = new Semaphore(capacity);
    }
    
    public void updateHueving(long n){
        try {
            System.out.println("AQUI ES hueving bopt");
            this.hueving.acquire();
            this.huevingTime += n;
            this.hueving.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(AssemblyLine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void addToStock(){
        try {
            this.stockSem.acquire();
            this.stock++;
            Main.interfazGrafica.setNumeroBotonesListos(this.stock);
            this.retrieveSem.release();
            System.out.println(whereTo + ":" + this.stock);
            this.stockSem.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ButtonProductionLine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void retrieveFromStock(int numNeeded){
        
        try {
            this.retrieveSem.acquire(numNeeded);
            this.stockSem.acquire();
            this.stock -= numNeeded;
            Main.interfazGrafica.setNumeroBotonesListos(this.stock);
            System.out.println(whereTo + ":" + this.stock);
            this.stockSem.release();
            this.capacitySem.release(numNeeded);
        } catch (InterruptedException ex) {
            Logger.getLogger(AssemblyLine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
