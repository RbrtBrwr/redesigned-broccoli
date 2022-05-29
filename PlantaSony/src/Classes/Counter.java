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
 * @author ponsa
 */
public class Counter {
    public Semaphore accessCounter = new Semaphore(1);
    public int daysRemaining;
    
    public Counter(){
        this.daysRemaining = 30;
    }
    
    public void setDaysRemaining(int days){
        this.daysRemaining = days;
    }
    
    public void reduceCounter(){
        try {
            accessCounter.acquire();
            daysRemaining--;
            System.out.println("QUEDAN " + daysRemaining + " DIAS");
            accessCounter.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Counter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void checkCounter(){
        try {
            accessCounter.acquire();
            System.out.println("ESTOY VIENDO EL COUNTER");
        } catch (InterruptedException ex) {
            Logger.getLogger(Counter.class.getName()).log(Level.SEVERE, null, ex);
        }
        accessCounter.release();
    }
}


