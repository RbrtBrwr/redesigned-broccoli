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
public class PinProductionLine {
    protected int capacity;
    protected volatile int stock;
    
    public Semaphore capacitySem;
    public Semaphore stockSem = new Semaphore(1);
    public Semaphore retrieveSem = new Semaphore(0);
    
    public String whereTo = "Pin";
    
    PinProductionLine(int capacity, int stock){
        this.capacity = capacity;
        this.stock = stock;
        this.capacitySem = new Semaphore(capacity);
    }
    
    public void addToStock(){
        try {
            this.stockSem.acquire();
            this.stock++;
            Main.interfazGrafica.setNumeroPinesListos(this.stock);
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
            Main.interfazGrafica.setNumeroPinesListos(this.stock);
            System.out.println(whereTo + ":" + this.stock);
            this.stockSem.release();
            this.capacitySem.release(numNeeded);
        } catch (InterruptedException ex) {
            Logger.getLogger(AssemblyLine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
