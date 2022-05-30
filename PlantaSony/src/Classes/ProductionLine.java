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
    private volatile int stock;
    
    public Semaphore capacitySem;
    public Semaphore stockSem = new Semaphore(1);
    public Semaphore retrieveSem = new Semaphore(0);
    
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
            if(this.whereTo == "Pin"){
                Main.interfazGrafica.setNumeroPinesListos(this.stock);
            } else if(this.whereTo == "Boton"){
                Main.interfazGrafica.setNumeroBotonesListos(this.stock);
            } else if(this.whereTo == "Pantalla"){
                Main.interfazGrafica.setNumeroPantallasListas(this.stock);
            } else if(this.whereTo == "Camara"){
                Main.interfazGrafica.setNumeroCamarasListas(this.stock);
            } else{

                    Main.interfazGrafica.setNumeroTelefonos(this.stock);
            }

            this.retrieveSem.release();
            System.out.println(whereTo + ":" + this.stock);
            this.stockSem.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ProductionLine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addToButtonStock(){
        try {
            this.stockSem.acquire();
            this.stock++;
            Main.interfazGrafica.setNumeroBotonesListos(this.stock);
            this.retrieveSem.release();
            System.out.println(whereTo + ":" + this.stock);
            this.stockSem.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ProductionLine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addToPinStock(){
        try {
            this.stockSem.acquire();
            this.stock++;
            Main.interfazGrafica.setNumeroPinesListos(this.stock);
            this.retrieveSem.release();
            System.out.println(whereTo + ":" + this.stock);
            this.stockSem.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ProductionLine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addToScreenStock(){
        try {
            this.stockSem.acquire();
            this.stock++;
            Main.interfazGrafica.setNumeroPantallasListas(this.stock);
            this.retrieveSem.release();
            System.out.println(whereTo + ":" + this.stock);
            this.stockSem.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ProductionLine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addToCameraStock(){
        try {
            this.stockSem.acquire();
            this.stock++;
            Main.interfazGrafica.setNumeroCamarasListas(this.stock);
            this.retrieveSem.release();
            System.out.println(whereTo + ":" + this.stock);
            this.stockSem.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ProductionLine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    TODO en verdad no se si ese do while me sirva como quiero. capaz puedo poner la condicion de espera directamente en el ensamblador
    public void retrieveFromStock(int numNeeded){
        
        try {
            this.retrieveSem.acquire(numNeeded);
            this.stockSem.acquire();
            this.stock -= numNeeded;
            switch (whereTo){
                case "Pin":
                    Main.interfazGrafica.setNumeroPinesListos(this.stock);
                case "Boton":
                    Main.interfazGrafica.setNumeroBotonesListos(this.stock);
                case "Pantalla":
                    Main.interfazGrafica.setNumeroPantallasListas(this.stock);
                case "Camara":
                    Main.interfazGrafica.setNumeroCamarasListas(this.stock);
            }
            System.out.println(whereTo + ":" + this.stock);
            this.stockSem.release();
            this.capacitySem.release(numNeeded);
        } catch (InterruptedException ex) {
            Logger.getLogger(ProductionLine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkStock(int numNeeded){
        boolean temp = false;
        
        try {
            this.stockSem.acquire();
            temp = numNeeded > this.stock;
            this.stockSem.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(ProductionLine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
    
    public int getStock(){
        int temp = 0;
        try {
            this.stockSem.acquire();
            temp = this.stock;
            this.stockSem.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ProductionLine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;

    }
}
