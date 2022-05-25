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
public class ProductionLine {
    private int capacity;
    private int stock;
    
    public Semaphore capacitySem;
    public Semaphore stockSem = new Semaphore(1);
    
    public String whereTo;
    
    ProductionLine(int capacity, int stock, String whereTo){
        this.capacity = capacity;
        this.stock = stock;
        this.capacitySem = new Semaphore(capacity);
        this.whereTo = whereTo;
    }
    
    public void addToStock(){
        try {
            this.stockSem.acquire();
            this.stock++;
            System.out.println(whereTo + ":" + this.stock);
            this.stockSem.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ProductionLine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    TODO en verdad no se si ese do while me sirva como quiero. capaz puedo poner la condicion de espera directamente en el ensamblador
    public void retrieveFromStock(int numNeeded){
        do {} while (this.stock < numNeeded);
        try {
            this.stockSem.acquire();
            this.stock -= numNeeded;
            this.stockSem.release();
            this.capacitySem.release(numNeeded);
        } catch (InterruptedException ex) {
            Logger.getLogger(ProductionLine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
