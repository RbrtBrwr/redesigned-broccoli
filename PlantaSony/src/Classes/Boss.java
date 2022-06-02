/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rober
 */
public class Boss extends Thread{
    public int salary;
    public String status;
    private final long counterReduceTime;
    private final Counter counter;
    private final double  clashTime;
    private final double papersTime;
    public boolean running;
    Timer timer = new Timer();
    public long dayDuration;
    
    public Boss(Counter counter){
        this.salary = 7;
        this.counterReduceTime = 1000;
        this.status = "Ocioso";
        this.counter = counter;
        this.clashTime = this.papersTime = 13.8888889;
        this.running = false;
    }

public void stopRun(){
        this.interrupt();
    }
    
    public void playCrashRoyale(){
        this.status = "Jugando Clash Royale";
        Main.interfazGrafica.setBossStatus(this.status);
        try {
//            System.out.println("Estoy jugando Clash Royale");
            Thread.sleep((long) this.clashTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Boss.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void checkPapers(){
        this.status = "Chequeando papeles";
        Main.interfazGrafica.setBossStatus(this.status);
        try {
//            System.out.println("Estoy revisando papeles");
            Thread.sleep((long) this.papersTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Boss.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reduceCounter(){
        this.status = "Reduciendo countdown";
        Main.interfazGrafica.setBossStatus(this.status);
        try {
            this.counter.reduceCounter();
            Main.interfazGrafica.addDay();
            Thread.sleep(this.counterReduceTime);
        } catch (InterruptedException ex) {
            Logger.getLogger(Boss.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    TimerTask playClashRoyaleTimer = new TimerTask(){
        @Override
        public void run() {
            playCrashRoyale();
            
        }   
    };
    
    TimerTask reduceCounterTimer = new TimerTask(){
        @Override
        public void run() {
            reduceCounter();
        }
    };
    
        TimerTask checkPapersTimer = new TimerTask(){
        @Override
        public void run() {
            checkPapers();
        }   
    };
    
    @Override
        public void run(){
            //Esto significa que va a esperar 1 segundo para ejecturar reduceCounterTime y luego va a ejecutarlo cada segundo
            timer.schedule(reduceCounterTimer, 1000, 1000);
            timer.schedule(checkPapersTimer, 0, 1);
            timer.schedule(playClashRoyaleTimer, 0, 1);
        
        }
        
    
}
