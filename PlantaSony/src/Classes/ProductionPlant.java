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
public class ProductionPlant {
//    private ProductionLine screenProduction;
//    private ProductionLine buttonProduction;
//    private ProductionLine pinProduction;
//    private ProductionLine cameraProduction;
//    private ProductionLine phoneAssembly;
    
    private Boss boss;
    private Manager manager;
    
//    private int dayTime;
    private int countdown;
    private int totalExpense;
    private int totalEarning;
    
    private Semaphore countdownSem;
    private Semaphore revenueSem;
    
    private boolean unlimitedStock;
    
//    TODO DE AQUI EN ADELANTE ES PARA PRUEBAS POR AHORA
    
    public ProductionLine screenProduction = new ProductionLine(3, 10, 2, "Screen");
    public ProductionLine buttonProduction = new ProductionLine(3, 10, 2, "Button");
    public ProductionLine pinProduction = new ProductionLine(3, 10, 2, "Pin");
    public ProductionLine cameraProduction = new ProductionLine(3, 10, 2, "Camera");
    public ProductionLine phoneAssembly;
    
    public static volatile int dayTime = 1000;
    
    public static volatile int countDown = 30;
    public static volatile int screenCount = 0;
    public static volatile int buttonCount = 0;
    public static volatile int pinCount = 0;
    public static volatile int cameraCount = 0;
    
    public static Semaphore screenSemaphore = new Semaphore(15);
    public static Semaphore buttonSemaphore = new Semaphore(15);
    public static Semaphore pinSemaphore = new Semaphore(15);
    public static Semaphore cameraSemaphore = new Semaphore(15);
    
}
