/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Classes;

/**
 *
 * @author rober
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProductionLine camaras = new ProductionLine(15, 0, "Camara");
        ProductionLine pantallas = new ProductionLine(10, 0, "Pantalla");
        ProductionLine botones = new ProductionLine(20, 0, "Boton");
        ProductionLine pines = new ProductionLine(12, 0, "Pin");
        
        int segundosEnDia = 4;
        int msecDia = segundosEnDia * 100;
        
        float diasCamara = 1;
        float diasBoton = 1;
        float diasPantalla = 3;
        float diasPines = 3;
        System.out.println(diasCamara);
        System.out.println(diasBoton);
        System.out.println(diasPantalla);
        System.out.println(diasPines);
        
        Producer[] productoresCamaras = new Producer[11];
        Producer[] productoresPantallas = new Producer[11];
        Producer[] productoresBotones = new Producer[11];
        Producer[] productoresPines = new Producer[11];
        
        int numeroProductoresBotones = 2;
        int numeroProductoresCamaras = 2;
        int numeroProductoresPantallas = 5;
        int numeroProductoresPines = 5;
        
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
    }
    
}
