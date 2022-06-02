/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Classes;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author rober
 */
public class Main {
//    public static Semaphore assemblerRetrieveingSem = new Semaphore(1);
    public static InterfazGraficaPlanta interfazGrafica = new InterfazGraficaPlanta();
    
    public static int maxCamaras;
    public static int maxPantallas;
    public static int maxBotones;
    public static int maxPines;
    
    public static CameraProductionLine camaras;
    public static ScreenProductionLine pantallas;
    public static ButtonProductionLine botones;
    public static PinProductionLine pines;
    public static AssemblyLine assemblyLine;
    
    public static String selectedPlant = "";
    
    public static int startingDays = 30;
    
    public static int segundosEnDia = 1;
    public static int msecDia = segundosEnDia * 1000;

    public static int tiempoProduccionCamara = 3000;
    public static int tiempoProduccionBoton = 500;
    public static int tiempoProduccionPantalla = 500;
    public static int tiempoProduccionPines = 3000;
    public static int tiempoProduccionTelefono = 2000;

    public static Counter counter = new Counter(startingDays);

    public static CameraProducer[] productoresCamaras = new CameraProducer[11];
    public static ScreenProducer[] productoresPantallas = new ScreenProducer[11];
    public static ButtonProducer[] productoresBotones = new ButtonProducer[11];
    public static PinProducer[] productoresPines = new PinProducer[11];
    public static Assembler[] ensambladores = new Assembler[11];
    public static Boss jefe;
    public static Manager gerente;

    public static int numeroProductoresBotones;
    public static int numeroProductoresCamaras;
    public static int numeroProductoresPantallas;
    public static int numeroProductoresPines;
    public static int numeroEnsambladores;
    
    public static int totalTrabajadores;

    public static int startingDay = 1;
    
    public static boolean executing = false;
    public static boolean pausado = false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        interfazGrafica.setVisible(true);


        maxCamaras = 20;
        maxPantallas = 40;
        maxBotones = 45;
        maxPines = 15;
        

        camaras = new CameraProductionLine(maxCamaras, 0);
        pantallas = new ScreenProductionLine(maxPantallas, 0);
        botones = new ButtonProductionLine(maxBotones, 0);
        pines = new PinProductionLine(maxPines, 0);
        assemblyLine = new AssemblyLine(999, 0);
        
        segundosEnDia = 1;
        msecDia = segundosEnDia * 1000;
        
        tiempoProduccionCamara = 3000;
        tiempoProduccionBoton = 500;
        tiempoProduccionPantalla = 500;
        tiempoProduccionPines = 3000;
        tiempoProduccionTelefono = 2000;
        
        counter = new Counter(startingDays);

        productoresCamaras = new CameraProducer[11];
        productoresPantallas = new ScreenProducer[11];
        productoresBotones = new ButtonProducer[11];
        productoresPines = new PinProducer[11];
        ensambladores = new Assembler[11];
        jefe = new Boss(counter);
        gerente = new Manager(counter, jefe);
        
        numeroProductoresBotones = 2;
        numeroProductoresCamaras = 3;
        numeroProductoresPantallas = 4;
        numeroProductoresPines = 3;
        numeroEnsambladores = 3;
        
        startingDay = 1;
        
        interfazGrafica.setNumeroProductoresBotones(numeroProductoresBotones);
        interfazGrafica.setNumeroProductoresCamaras(numeroProductoresCamaras);
        interfazGrafica.setNumeroProductoresPantallas(numeroProductoresPantallas);
        interfazGrafica.setNumeroProductoresPines(numeroProductoresPines);
        interfazGrafica.setNumeroEnsambladores(numeroEnsambladores);
        interfazGrafica.setCurrentDay(startingDay);
        interfazGrafica.setCountdown(counter.daysRemaining);
        interfazGrafica.setBossSalary(jefe.salary);
        
    }
    
    public static void getInputFromInterface(){
        if (checkValidInputs() && checkPlant()){
            executing = true;
            startAllThreads(); 
        }
    }
    
    public static boolean checkValidInputs(){
        numeroProductoresBotones = interfazGrafica.getNumeroProductoresBotones();
        numeroProductoresCamaras = interfazGrafica.getNumeroProductoresCamaras();
        numeroProductoresPantallas = interfazGrafica.getNumeroProductoresPantallas();
        numeroProductoresPines = interfazGrafica.getNumeroProductoresPines();
        numeroEnsambladores = interfazGrafica.getNumeroEnsambladores();    

        totalTrabajadores = numeroProductoresBotones + numeroProductoresCamaras + numeroProductoresPantallas + numeroProductoresPines + numeroEnsambladores;
        
        if (totalTrabajadores > 15 || numeroProductoresBotones < 1 || numeroProductoresCamaras < 1 || numeroProductoresPantallas < 1 || numeroProductoresPines < 1 || numeroEnsambladores < 1){
            JOptionPane.showMessageDialog(null, "No se pueden tener 0 productores en ninguna linea de produccion\nEl numero de empleados no puede exceder los 15");
            return false;
        }

        
        return true;
    }
    
    public static boolean checkPlant(){
        if ("".equals(selectedPlant)){
            JOptionPane.showMessageDialog(null, "Por favor seleccione una planta");
            return false;
        }
        selectedPlant = interfazGrafica.getSelectedPlant();
        return true;
    }
    
    public static void startAllThreads(){
        
//      Inicializo el numero maximo de threads que puedo tener en cada arreglo
        for (int i = 0; i < 11; i++){
            productoresBotones[i] = new ButtonProducer(botones, tiempoProduccionBoton);
        }
        for (int i = 0; i < 11; i++){
            productoresCamaras[i] = new CameraProducer(camaras, tiempoProduccionCamara);
        }
        for (int i = 0; i < 11; i++){
            productoresPantallas[i] = new ScreenProducer(pantallas, tiempoProduccionPantalla);
        }
        for (int i = 0; i < 11; i++){
            productoresPines[i] = new PinProducer(pines, tiempoProduccionPines);
        }
        for (int i = 0; i < 11; i++){
            ensambladores[i] = new Assembler(assemblyLine, tiempoProduccionTelefono, camaras, botones, pines, pantallas);
        }
        
//      Solo corro las que necesito
        for (int i = 0; i < numeroProductoresBotones; i++){
            productoresBotones[i].start();
        }
        for (int i = 0; i < numeroProductoresCamaras; i++){
            productoresCamaras[i].start();
        }
        for (int i = 0; i < numeroProductoresPantallas; i++){
            productoresPantallas[i].start();
        }
        for (int i = 0; i < numeroProductoresPines; i++){
            productoresPines[i].start();
        }
        for (int i = 0; i < numeroEnsambladores; i++){
            ensambladores[i].start();
        }
        
        jefe.start();
        gerente.start(); 
    }
    
    public static void terminateExec(){
//        Esto se esta pausando, pero el timer no se frena ????
        executing = false;
        jefe.timer.cancel();
        gerente.timer.cancel();
        for (int i = 0; i < 11; i++){
            productoresBotones[i].stopRun();
            productoresCamaras[i].stopRun();
            productoresPantallas[i].stopRun();
            productoresPines[i].stopRun();
            ensambladores[i].stopRun();
            
        }
        try {
            counter.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void pauseExec(){
        pausado = true;
        try {
            jefe.wait();
            gerente.wait();
            for (int i = 0; i < 11; i++){
                    productoresBotones[i].wait();
                    productoresCamaras[i].wait();
                    productoresPantallas[i].wait();
                    productoresPines[i].wait();
                    ensambladores[i].wait();

            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void resumeExec(){
        jefe.notify();
        gerente.notify();
        for (int i = 0; i < 11; i++){
            productoresBotones[i].notify();
            productoresCamaras[i].notify();
            productoresPantallas[i].notify();
            productoresPines[i].notify();
            ensambladores[i].notify();
            
        }
    }
    
}
