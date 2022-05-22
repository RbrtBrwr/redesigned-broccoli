/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import java.util.concurrent.Semaphore;

/**
 *
 * @author rober
 */
public class Producer extends Thread{
    private int productPerDay;
    private int earningsPerDay;
    public Semaphore stockSem;
    public Semaphore earningSem;
    public boolean noLimits;
    
    Producer(int productPerDay, int earningsPerDay, Semaphore stockSem, Semaphore earningSem, boolean noLimits){
        this.productPerDay = productPerDay;
        this.earningsPerDay = earningsPerDay;
        this.stockSem = stockSem;
        this.earningSem = earningSem;
        this.noLimits = noLimits;
    }
    
    @Override
    public void run(){
    
    }
}
