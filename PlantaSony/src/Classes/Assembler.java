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
public class Assembler extends Thread{
    private final ProductionLine cameraLine;
    private final ProductionLine buttonLine;
    private final ProductionLine pinLine;
    private final ProductionLine screenLine;
    private final ProductionLine phoneLine;
    
    private final int camerasNeeded = 2;
    private final int buttonsNeeded = 2;
    private final int pinsNeeded = 1;
    private final int screensNeeded = 1;
    
    
    private final int assemblyTime;
    public String currentStatus;
    
    public String whereTo;
    
    Assembler(ProductionLine phoneLine, int assemblyTime,
            ProductionLine cameraLine, ProductionLine buttonLine, ProductionLine pinLine, 
            ProductionLine screenLine){
        this.assemblyTime = assemblyTime;
        this.phoneLine = phoneLine;
        this.cameraLine = cameraLine;
        this.buttonLine = buttonLine;
        this.pinLine = pinLine;
        this.screenLine = screenLine;
    }
    
    public void timeForWork(){
        this.getParts();
    }
    
    public void getParts(){
        this.currentStatus = "Esperando Partes";
        this.buttonLine.retrieveFromStock(buttonsNeeded);
        this.pinLine.retrieveFromStock(pinsNeeded);
        this.cameraLine.retrieveFromStock(camerasNeeded);
        this.screenLine.retrieveFromStock(screensNeeded);
        this.assemblersAssemble();

    }
    
    public void assemblersAssemble(){
        this.currentStatus = "Workin";
        try {
//            TODO Puedo crear una funcion dentro de los production lines que se encargue de esto.
            Thread.sleep(assemblyTime);
            this.phoneLine.addToStock();
        } catch (InterruptedException ex) {
            Logger.getLogger(Assembler.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.getParts();
    }

    
    @Override 
    public void run(){
        while (true){
            this.timeForWork();
        }
            
    }
}
