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
    private ProductionLine screenProduction;
    private ProductionLine buttonProduction;
    private ProductionLine pinProduction;
    private ProductionLine cameraProduction;
    private ProductionLine phoneAssembly;
    
    private Boss boss;
    private Manager manager;
    
    private int dayTime;
    private int countdown;
    private int totalExpense;
    private int totalEarning;
    
    private Semaphore countdownSem;
    private Semaphore revenueSem;
    
    private boolean unlimitedStock;
}
