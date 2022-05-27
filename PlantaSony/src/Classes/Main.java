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

        int maxCamaras = 15;
        int maxPantallas = 10;
        int maxBotones = 20;
        int maxPines = 12;
        
        ProductionLine camaras = new ProductionLine(maxCamaras, 0, "Camara");
        ProductionLine pantallas = new ProductionLine(maxPantallas, 0, "Pantalla");
        ProductionLine botones = new ProductionLine(maxBotones, 0, "Boton");
        ProductionLine pines = new ProductionLine(maxPines, 0, "Pin");
        ProductionLine assemblyLine = new ProductionLine(999, 0, "Assembled");
        
        int segundosEnDia = 9;
        int msecDia = segundosEnDia * 1000;
        
        float diasCamara = 1;
        float diasBoton = 1;
        float diasPantalla = 3;
        float diasPines = 3;
        float diasEnsamblaje = 3;
        
        Producer[] productoresCamaras = new Producer[11];
        Producer[] productoresPantallas = new Producer[11];
        Producer[] productoresBotones = new Producer[11];
        Producer[] productoresPines = new Producer[11];
        Assembler[] ensambladores = new Assembler[11];
        
        int numeroProductoresBotones = 2;
        int numeroProductoresCamaras = 2;
        int numeroProductoresPantallas = 5;
        int numeroProductoresPines = 3;
        int numeroEnsambladores = 3;
        
        interfazGrafica.setNumeroProductoresBotones(numeroProductoresBotones);
        interfazGrafica.setNumeroProductoresCamaras(numeroProductoresCamaras);
        interfazGrafica.setNumeroProductoresPantallas(numeroProductoresPantallas);
        interfazGrafica.setNumeroProductoresPines(numeroProductoresPines);
        
        for (int i = 0; i < numeroProductoresBotones; i++){
            productoresBotones[i] = new Producer(botones, msecDia, diasBoton);
            productoresBotones[i].start();
        }
        for (int i = 0; i < numeroProductoresCamaras; i++){
            productoresCamaras[i] = new Producer(camaras, msecDia, diasCamara);
            productoresCamaras[i].start();
        }
        for (int i = 0; i < numeroProductoresPantallas; i++){
            productoresPantallas[i] = new Producer(pantallas, msecDia, diasPantalla);
            productoresPantallas[i].start();
        }
        for (int i = 0; i < numeroProductoresPines; i++){
            productoresPines[i] = new Producer(pines, msecDia, diasPines);
            productoresPines[i].start();
        }
        for (int i = 0; i < numeroEnsambladores; i++){
            ensambladores[i] = new Assembler(assemblyLine, msecDia, diasEnsamblaje, camaras, botones, pines, pantallas);
            ensambladores[i].start();
        }
    }
    
}
