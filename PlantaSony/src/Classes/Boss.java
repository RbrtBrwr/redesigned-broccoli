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
    private final long counterReduceTime;
    private final Counter counter;
    
    public Boss(int salary, int counterReduceTime, Counter counter){
        this.salary = salary;
        this.counterReduceTime = counterReduceTime;
        this.isPlaying = false;
        this.isReducingTime = false;
        this.counter = counter;
    }
    
    @Override
    public void run(){
        while(true){
            if(!isPlaying){
            try {
                Thread.sleep(this.counterReduceTime);
                this.counter.reduceCounter();
            } catch (InterruptedException ex) {
                Logger.getLogger(Boss.class.getName()).log(Level.SEVERE, null, ex);
            }
            } else{
                
            }
            
        }
    }
    
}
