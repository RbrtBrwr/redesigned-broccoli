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
    private String lineType;
    private int productionTime;
    
    Producer(int producerWage, int productPerDay, String lineType){
        this.earningsPerDay = producerWage;
        this.productPerDay = productPerDay;
        this.lineType = lineType;
        this.productionTime = 1000 / productPerDay;
    }
    
    @Override 
    public void run(){
        try {
            while(ProductionPlant.countDown > 0){
                
                switch(lineType){
                    case "Screen":
                        ProductionPlant.screenSemaphore.acquire();
                        Thread.sleep(this.productionTime);
                        ProductionPlant.screenCount++;
                        System.out.println(this.getName() + " Nueva Pantalla. Total: " + ProductionPlant.screenCount);
//                        ProductionPlant.screenSemaphore.release();
                    case "Button":
                        ProductionPlant.buttonSemaphore.acquire();
                        Thread.sleep(this.productionTime);
                        ProductionPlant.buttonCount++;
                        System.out.println(this.getName() + " Nueva button. Total: " + ProductionPlant.buttonCount);
//                        ProductionPlant.buttonSemaphore.release();
                    case "Pin":
                        ProductionPlant.pinSemaphore.acquire();
                        Thread.sleep(this.productionTime);
                        ProductionPlant.pinCount++;
                        System.out.println(this.getName() + " Nueva pin. Total: " + ProductionPlant.pinCount);
//                        ProductionPlant.pinSemaphore.release();
                    case "Camera":
                        ProductionPlant.cameraSemaphore.acquire();
                        Thread.sleep(this.productionTime);
                        ProductionPlant.cameraCount++;
                        System.out.println(this.getName() + " Nueva camera. Total: " + ProductionPlant.cameraCount);
//                        ProductionPlant.cameraSemaphore.release();
                }
            }
        } catch (InterruptedException ex){
            
        }
    }
}
