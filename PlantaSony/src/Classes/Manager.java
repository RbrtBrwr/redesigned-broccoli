/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rober
 */

public class Manager extends Thread{
    
    public int salary;
    private final Counter counter;
    public Boss boss;
    Timer timer = new Timer();
    
    public Manager(Counter counter, Boss boss){
        this.salary = 180;
        this.counter = counter;
        this.boss = boss;
        
    }
    
    public void stopRun(){
        this.interrupt();
    }
    
    public int getRandomInt(int min, int max){
        Random rand = new Random();
        int randomNumber = rand.nextInt((max - min) + 1) + min;
        
        return randomNumber;
    }
    
    public void checkOnBoss(){
        if(this.boss.isPlaying){
            this.boss.salary -= 2;
            Main.interfazGrafica.setBossSalary(this.boss.salary);
            System.out.println("TE CACHE MALDITO. Ahora te voy a pagar " + this.boss.salary);
        }
        try {
            Thread.sleep(getRandomInt(21,63));
        } catch (InterruptedException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void checkCounter(){
        this.counter.checkCounter();
    }
    
    public void resetCounter(){
        this.counter.daysRemaining = 30;
    }
    
    public void deliverPhones(){
        //Aqui simplemente guardariamos el numero de telefonos que se hicieron ese dia en el CSV y seteamos a 0 para el nuevo dia
        Main.interfazGrafica.setNumeroTelefonos(0);
    }
    
    TimerTask checkOnBossTimer = new TimerTask(){
        @Override
        public void run() {
            checkOnBoss();
        }
        
    };
    
        TimerTask checkCounterTimer = new TimerTask(){
        @Override
        public void run() {
            checkCounter();
        }
        
    };
        
        TimerTask resetCounterAndDeliverPhones = new TimerTask(){
        @Override
        public void run() {
            deliverPhones();
            resetCounter();
        }
                
                };
    
    @Override
    public void run(){
        timer.schedule(checkCounterTimer, 0, 1000);
        timer.schedule(checkOnBossTimer, 0, getRandomInt(500, 750));
        timer.schedule(resetCounterAndDeliverPhones, 30000, 30000);
    }
    
    
    
}
