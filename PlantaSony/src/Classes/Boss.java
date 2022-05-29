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
public class Boss extends Thread{
    public int salary;
    public boolean isPlaying;
    public boolean isReducingTime; 
    public boolean isPapering;
    private final long counterReduceTime;
    private final Counter counter;
    private final double  clashTime;
    private final double papersTime;
    
    public Boss(Counter counter){
        this.salary = 7;
        this.counterReduceTime = 250;
        this.isPlaying = false;
        this.isReducingTime = false;
        this.counter = counter;
        this.clashTime = this.papersTime = 13.8888889;
    }
    
    public void playCrashRoyale(){
        this.isPlaying = true;
        try {
            Thread.sleep((long) this.clashTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Boss.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.isPlaying = false;
    }
    
    public void checkPapers(){
        this.isPapering = true;
        try {
            Thread.sleep((long) this.papersTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Boss.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.isPapering = false;
    }
    
    public void reduceCounter(){
        this.isReducingTime = true;
        try {
            Thread.sleep(this.counterReduceTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Boss.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.isReducingTime = false;
        
    }
    
    @Override
    public void run(){
        while(true){
            if(){
                while(remainingTimeInDay != 0){
                    this.playCrashRoyale();
                    this.checkPapers();
                }
            } else{
                this.reduceCounter();
                while(remainingTimeInDay != 0){
                    this.playCrashRoyale();
                    this.checkPapers();
                }
            }
        }
    }
    
}
