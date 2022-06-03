/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Classes;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.parser.ParseException;


/**
 *
 * @author rober
 */
public class Main {

    // SALARIOS DIARIOS, MULTIPLIQUE POR 10 PORQUE ME DIERON LASTIMA 
    public static final int SALARIO_PRODUCTOR_CAMARAS = 50;
    public static final int SALARIO_PRODUCTOR_BOTONES = 40;
    public static final int SALARIO_PRODUCTOR_PANTALLAS = 30;
    public static final int SALARIO_PRODUCTOR_PINES = 50;
    public static final int SALARIO_ENSAMBLADOR = 60;
    public static final int SALARIO_JEFE = 70;
    public static final int SALARIO_GERENTE = 180;
    
    public static int salario_total_linea_camaras = 0;
    public static int salario_total_linea_botones = 0;
    public static int salario_total_linea_pantallas = 0;
    public static int salario_total_linea_pines = 0;
    public static int salario_total_linea_ensamblaje = 0;
    public static int salario_total_jefe = 0;
    public static int salario_total_gerente = 0;
    public static int salario_total_planta = 0;
    
    
    public static InterfazGraficaPlanta interfazGrafica = new InterfazGraficaPlanta();
    
    public static int[] readJson;
    
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
    

    public static int segundosEnDia;
    public static int msecDia = segundosEnDia * 1000;

    public static int tiempoProduccionCamara;
    public static int tiempoProduccionBoton;
    public static int tiempoProduccionPantalla;
    public static int tiempoProduccionPines;
    public static int tiempoProduccionTelefono;

    public static Counter counter;

    public static CameraProducer[] productoresCamaras = new CameraProducer[11];
    public static ScreenProducer[] productoresPantallas = new ScreenProducer[11];
    public static ButtonProducer[] productoresBotones = new ButtonProducer[11];
    public static PinProducer[] productoresPines = new PinProducer[11];
    public static Assembler[] ensambladores = new Assembler[11];
    public static Boss jefe = new Boss(counter);
    public static Manager gerente = new Manager(counter, jefe, 30);

    public static int numeroProductoresBotones;
    public static int numeroProductoresCamaras;
    public static int numeroProductoresPantallas;
    public static int numeroProductoresPines;
    public static int numeroEnsambladores;
    
    public static int totalTrabajadores;

    public static int startingDay = 1;
    public static int resetCounter;
    
    public static boolean executing = false;
    public static boolean paused = false;
    
//    Camara, botones, pines, pantallas
    public static int[] specsPro = new int[] {4,3,1,2};
    public static int[] specs10III = new int[] {2,2,1,1};
    
    public static final int COSTO_PRO = 1050;
    public static final int COSTO_10_III = 600;
    
    public static int[] phoneSpecs;
    public static int phonePrice;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        interfazGrafica.setVisible(true);     
    }
    
    public static void getInputFromInterface(){
        if (checkValidInputs() && checkPlant()){
            executing = true;
            
            setSpeed();
            setWorkTime();
            setInfinity();
            initializeThreads();
            interfazGrafica.setNumeroProductoresBotones(numeroProductoresBotones);
            interfazGrafica.setNumeroProductoresCamaras(numeroProductoresCamaras);
            interfazGrafica.setNumeroProductoresPantallas(numeroProductoresPantallas);
            interfazGrafica.setNumeroProductoresPines(numeroProductoresPines);
            interfazGrafica.setNumeroEnsambladores(numeroEnsambladores);

            interfazGrafica.setCurrentDay(startingDay);
            interfazGrafica.setCountdown(counter.daysRemaining);
            interfazGrafica.setBossSalary(jefe.salary);
            startAllThreads(); 
            
        }
    }
    public static void restartExec(){
        paused = false;
        executing = true;
        setSpeed();
        setWorkTime();
        setInfinity();
        startAllThreads(); 
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
    
    public static void setInfinity(){
        if (interfazGrafica.checkInfinity()){
            maxCamaras = 999;
            maxPantallas = 999;
            maxBotones = 999;
            maxPines = 999;
        } else {
            maxCamaras = 20;
            maxPantallas = 40;
            maxBotones = 45;
            maxPines = 15;
        }
        camaras = new CameraProductionLine(maxCamaras, 0);
        pantallas = new ScreenProductionLine(maxPantallas, 0);
        botones = new ButtonProductionLine(maxBotones, 0);
        pines = new PinProductionLine(maxPines, 0);
        assemblyLine = new AssemblyLine(999, 0);
    }
    
    public static boolean checkPlant(){
        if ("".equals(selectedPlant)){
            JOptionPane.showMessageDialog(null, "Por favor seleccione una planta");
            return false;
        }
        setPlantSpecs(interfazGrafica.getSelectedPlant());
        
        return true;
    }
    
    public static void setPlantSpecs(String selectedPlant){
        if ("Xperia Pro-I".equals(selectedPlant)){
            phoneSpecs = specsPro;
            phonePrice = COSTO_PRO;
            
        } else if ("Xperia 10 III".equals(selectedPlant)){
            phoneSpecs = specs10III;
            phonePrice = COSTO_10_III;
        }
    }
    
    public static void setSpeed(){
        msecDia = interfazGrafica.getSegundosPorDia() * 1000;
    }
    
    public static void setWorkTime(){
        tiempoProduccionCamara = msecDia * 3;
        tiempoProduccionBoton = msecDia / 2;
        tiempoProduccionPantalla = msecDia / 2;
        tiempoProduccionPines = msecDia * 3;
        tiempoProduccionTelefono = msecDia * 3;
        System.out.println(tiempoProduccionTelefono);
        System.out.println(tiempoProduccionCamara);
        System.out.println("----------------------");
    }
    
    public static void initializeThreads(){
    //      Inicializo el numero maximo de threads que puedo tener en cada arreglo
        for (int i = 0; i < 11; i++){
            productoresBotones[i] = new ButtonProducer(botones, tiempoProduccionBoton);
            productoresCamaras[i] = new CameraProducer(camaras, tiempoProduccionCamara);
            productoresPantallas[i] = new ScreenProducer(pantallas, tiempoProduccionPantalla);
            productoresPines[i] = new PinProducer(pines, tiempoProduccionPines);
            ensambladores[i] = new Assembler(assemblyLine, tiempoProduccionTelefono, phoneSpecs, camaras, botones, pines, pantallas);
        }
        counter = new Counter(interfazGrafica.getCountdown());
        jefe = new Boss(counter);
        gerente = new Manager(counter, jefe, 30);
    
    }
    
    public static void startAllThreads(){     
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
        
        //Para guardar data cada vez que termine el programa
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run(){
                jsonReaderWriter.write();
                System.out.println("Exiting");
            }
        });
    }

    public void setFromJson(int[] params){
//        Le paso como parametro un arreglo con los valores
//0 - secs x dia
//1 - dias entre despacho
//2 - almacen ilimitado
//3 - productores camaras
//4 - productores botones
//5 - productores pines
//6 - productores pantallas
//7 - ensambladores        

        if (params[0] < 1){
            JOptionPane.showMessageDialog(null, "El valor especificado en el archivo es invalido\nSe utilizo el default");
            interfazGrafica.setDayLengthSetter(1);
        } else {
            interfazGrafica.setDayLengthSetter(params[0]);
        }
        
        if (params[1] < 6){
            JOptionPane.showMessageDialog(null, "En menos de 6 dias no produces ni un telefono\nSe utilizo el default");
            interfazGrafica.setCountdown(30);
        } else {
            interfazGrafica.setCountdown(params[1]);
        }
        
        if (!interfazGrafica.setInfinity(params[2])){
            JOptionPane.showMessageDialog(null, "Input de stock infinito invalido\nNo se activo stock infinito");
        }
        
        int pCam = params[3];
        int pBut = params[4];
        int pPin = params[5];
        int pScr = params[6];
        int ass = params[7];
        boolean testTotal = pCam + pBut + pPin + pScr + ass > 15;
        boolean testNull = pCam < 1 || pBut < 1 || pPin < 1 || pScr < 1 || ass < 1;
        if (testTotal || testNull){
            JOptionPane.showMessageDialog(null, "La suma de trabajadores no puede ser mas de 15\nNo pueden haber cero productores en una linea\nSe utilizaron valores default");
            interfazGrafica.setNumeroProductoresCamaras(3);
            interfazGrafica.setNumeroProductoresBotones(3);
            interfazGrafica.setNumeroProductoresPines(3);
            interfazGrafica.setNumeroProductoresPantallas(3);
            interfazGrafica.setNumeroEnsambladores(3);
        } else {
            interfazGrafica.setNumeroProductoresCamaras(pCam);
            interfazGrafica.setNumeroProductoresBotones(pBut);
            interfazGrafica.setNumeroProductoresPines(pPin);
            interfazGrafica.setNumeroProductoresPantallas(pScr);
            interfazGrafica.setNumeroEnsambladores(ass);
        }
    }
    
    public void writeToJSON(){
        int[] params = new int[8];
        params[0] = interfazGrafica.getSegundosPorDia();
//        params[1] = interfazGrafica.getCountdown() ??????????
        params[2] = interfazGrafica.checkInfinity() ? 1 : 0;
        params[3] = interfazGrafica.getNumeroProductoresCamaras();
        params[4] = interfazGrafica.getNumeroProductoresBotones();
        params[5] = interfazGrafica.getNumeroProductoresPines();
        params[6] = interfazGrafica.getNumeroProductoresPantallas();
        params[7] = interfazGrafica.getNumeroEnsambladores();
    }
    
    
// Estas no funcionan como quiero, quizas las saco y ya
    
    public static void terminateExec(){
//        Esto se esta pausando, pero el timer no se frena ????
        jefe.timer.cancel();
        gerente.timer.cancel();
        
        for (int i = 0; i < 11; i++){
            productoresCamaras[i].stopRun();
            productoresBotones[i].stopRun();
            productoresPantallas[i].stopRun(); 
            productoresPines[i].stopRun(); 
            ensambladores[i].stopRun();
        }
        executing = false;
        paused = true;
        initializeThreads();
        try {
            counter.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        counter = null;
    }
    
    public static void updateSalaries(){
        
        salario_total_gerente += SALARIO_GERENTE;
        salario_total_jefe += SALARIO_JEFE;
        salario_total_linea_botones += SALARIO_PRODUCTOR_BOTONES * numeroProductoresBotones;
        salario_total_linea_camaras += SALARIO_PRODUCTOR_CAMARAS * numeroProductoresCamaras;
        salario_total_linea_ensamblaje += SALARIO_ENSAMBLADOR * numeroEnsambladores;
        salario_total_linea_pantallas += SALARIO_PRODUCTOR_PANTALLAS * numeroProductoresPantallas;
        salario_total_linea_pines += SALARIO_PRODUCTOR_PINES * numeroProductoresPines;
        salario_total_planta = salario_total_gerente + salario_total_jefe + salario_total_linea_botones + salario_total_linea_camaras + salario_total_linea_ensamblaje + salario_total_linea_pantallas + salario_total_linea_pines;
        
        interfazGrafica.setManagerSalary(salario_total_gerente);
        interfazGrafica.setBossSalary(salario_total_jefe);
        interfazGrafica.setTotalSalary(salario_total_planta);
        interfazGrafica.setAssemblySalary(salario_total_linea_ensamblaje);
        interfazGrafica.setButtonSalary(salario_total_linea_botones);
        interfazGrafica.setScreenSalary(salario_total_linea_pantallas);
        interfazGrafica.setCameraSalary(salario_total_linea_camaras);
        interfazGrafica.setPinSalary(salario_total_linea_pines);
    }
    
}
