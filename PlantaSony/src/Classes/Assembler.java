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
    public boolean running = false;
    
    private final CameraProductionLine cameraLine;
    private final ButtonProductionLine buttonLine;
    private final PinProductionLine pinLine;
    private final ScreenProductionLine screenLine;
    private final AssemblyLine phoneLine;
    
    private final int camerasNeeded = 2;
    private final int buttonsNeeded = 2;
    private final int pinsNeeded = 1;
    private final int screensNeeded = 1;
    
    
    private final int assemblyTime;
    public String currentStatus;
    
    public String whereTo;
    
    Assembler(AssemblyLine phoneLine, int assemblyTime,
            CameraProductionLine cameraLine, ButtonProductionLine buttonLine, PinProductionLine pinLine, 
            ScreenProductionLine screenLine){
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
    }

    public void stopRun(){
        running = false;
    }
    
    @Override 
    public void run(){
        running = true;
        while (running){
            getParts();
            assemblersAssemble();
        }
        this.interrupt();
            
    }
}
