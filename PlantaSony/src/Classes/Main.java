/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Classes;

import org.json.simple.parser.ParseException;


/**
 *
 * @author rober
 */
public class Main {
//    public static Semaphore assemblerRetrieveingSem = new Semaphore(1);
    public static InterfazGraficaPlanta interfazGrafica = new InterfazGraficaPlanta();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        
        interfazGrafica.setVisible(true);
        
        JSONWriterReader.write();
        JSONWriterReader.read();


        int maxCamaras = 20;
        int maxPantallas = 40;
        int maxBotones = 45;
        int maxPines = 15;
        

        CameraProductionLine camaras = new CameraProductionLine(maxCamaras, 0);
        ScreenProductionLine pantallas = new ScreenProductionLine(maxPantallas, 0);
        ButtonProductionLine botones = new ButtonProductionLine(maxBotones, 0);
        PinProductionLine pines = new PinProductionLine(maxPines, 0);
        AssemblyLine assemblyLine = new AssemblyLine(999, 0);
        
        int segundosEnDia = 1;
        int msecDia = segundosEnDia * 1000;
        
        int tiempoProduccionCamara = 3000;
        int tiempoProduccionBoton = 500;
        int tiempoProduccionPantalla = 500;
        int tiempoProduccionPines = 3000;
        int tiempoProduccionTelefono = 2000;
        
        Counter counter = new Counter();

        CameraProducer[] productoresCamaras = new CameraProducer[11];
        ScreenProducer[] productoresPantallas = new ScreenProducer[11];
        ButtonProducer[] productoresBotones = new ButtonProducer[11];
        PinProducer[] productoresPines = new PinProducer[11];
        Assembler[] ensambladores = new Assembler[11];
        Boss jefe = new Boss(counter);
        Manager gerente = new Manager(counter, jefe);
        
        int numeroProductoresBotones = 2;
        int numeroProductoresCamaras = 3;
        int numeroProductoresPantallas = 4;
        int numeroProductoresPines = 3;
        int numeroEnsambladores = 3;
        
        int startingDay = 1;
        
        interfazGrafica.setNumeroProductoresBotones(numeroProductoresBotones);
        interfazGrafica.setNumeroProductoresCamaras(numeroProductoresCamaras);
        interfazGrafica.setNumeroProductoresPantallas(numeroProductoresPantallas);
        interfazGrafica.setNumeroProductoresPines(numeroProductoresPines);
        interfazGrafica.setNumeroEnsambladores(numeroEnsambladores);
        interfazGrafica.setCurrentDay(startingDay);
        interfazGrafica.setCountdown(counter.daysRemaining);
        interfazGrafica.setBossSalary(jefe.salary);
        
        for (int i = 0; i < numeroProductoresBotones; i++){
            productoresBotones[i] = new ButtonProducer(botones, tiempoProduccionBoton);
            productoresBotones[i].start();
        }
        for (int i = 0; i < numeroProductoresCamaras; i++){
            productoresCamaras[i] = new CameraProducer(camaras, tiempoProduccionCamara);
            productoresCamaras[i].start();
        }
        for (int i = 0; i < numeroProductoresPantallas; i++){
            productoresPantallas[i] = new ScreenProducer(pantallas, tiempoProduccionPantalla);
            productoresPantallas[i].start();
        }
        for (int i = 0; i < numeroProductoresPines; i++){
            productoresPines[i] = new PinProducer(pines, tiempoProduccionPines);
            productoresPines[i].start();
        }
        for (int i = 0; i < numeroEnsambladores; i++){
            ensambladores[i] = new Assembler(assemblyLine, tiempoProduccionTelefono, camaras, botones, pines, pantallas);
            ensambladores[i].start();
        }
        
        jefe.start();
        gerente.start();
    }
    
}
