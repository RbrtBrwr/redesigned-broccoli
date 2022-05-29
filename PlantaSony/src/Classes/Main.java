/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Classes;


import java.util.concurrent.Semaphore;

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
    public static void main(String[] args) {
//        InterfazGraficaPlanta interfazGrafica = new InterfazGraficaPlanta();
        
        interfazGrafica.setVisible(true);


        int maxCamaras = 20;
        int maxPantallas = 40;
        int maxBotones = 45;
        int maxPines = 15;
       
        
        
        ProductionLine camaras = new ProductionLine(maxCamaras, 0, "Camara");
        ProductionLine pantallas = new ProductionLine(maxPantallas, 0, "Pantalla");
        ProductionLine botones = new ProductionLine(maxBotones, 0, "Boton");
        ProductionLine pines = new ProductionLine(maxPines, 0, "Pin");
        ProductionLine assemblyLine = new ProductionLine(999, 0, "Assembled");
        
        int segundosEnDia = 1;
        int msecDia = segundosEnDia * 1000;
        
        int tiempoProduccionCamara = msecDia / 2;
        int tiempoProduccionBoton = msecDia / 2;
        int tiempoProduccionPantalla = msecDia * 3;
        int tiempoProduccionPines = msecDia * 3;
        int tiempoProduccionTelefono = msecDia * 3;
        
        Counter counter = new Counter();

        Producer[] productoresCamaras = new Producer[11];
        Producer[] productoresPantallas = new Producer[11];
        Producer[] productoresBotones = new Producer[11];
        Producer[] productoresPines = new Producer[11];
        Assembler[] ensambladores = new Assembler[11];
        Boss jefe = new Boss(counter);
        Manager gerente = new Manager(counter, jefe);
        
        int numeroProductoresBotones = 2;
        int numeroProductoresCamaras = 2;
        int numeroProductoresPantallas = 5;
        int numeroProductoresPines = 3;
        int numeroEnsambladores = 3;
        
        int startingDay = 1;
        
        interfazGrafica.setNumeroProductoresBotones(numeroProductoresBotones);
        interfazGrafica.setNumeroProductoresCamaras(numeroProductoresCamaras);
        interfazGrafica.setNumeroProductoresPantallas(numeroProductoresPantallas);
        interfazGrafica.setNumeroProductoresPines(numeroProductoresPines);
        interfazGrafica.setNumeroEnsambladores(numeroEnsambladores);
        interfazGrafica.setCurrentDay(startingDay);
        
//        for (int i = 0; i < numeroProductoresBotones; i++){
//            productoresBotones[i] = new Producer(botones, tiempoProduccionBoton);
//            productoresBotones[i].start();
//        }
//        for (int i = 0; i < numeroProductoresCamaras; i++){
//            productoresCamaras[i] = new Producer(camaras, tiempoProduccionCamara);
//            productoresCamaras[i].start();
//        }
//        for (int i = 0; i < numeroProductoresPantallas; i++){
//            productoresPantallas[i] = new Producer(pantallas, tiempoProduccionPantalla);
//            productoresPantallas[i].start();
//        }
//        for (int i = 0; i < numeroProductoresPines; i++){
//            productoresPines[i] = new Producer(pines, tiempoProduccionPines);
//            productoresPines[i].start();
//        }
//        for (int i = 0; i < numeroEnsambladores; i++){
//            ensambladores[i] = new Assembler(assemblyLine, tiempoProduccionTelefono, camaras, botones, pines, pantallas);
//            ensambladores[i].start();
//        }
        
        jefe.start();
        gerente.start();
    }
    
}
