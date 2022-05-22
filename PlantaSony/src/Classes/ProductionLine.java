/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author rober
 */
public class ProductionLine {
    private Producer[] producers = new Producer[12];
    private int maxStock;
    private int producerCount;
    
    ProductionLine(int producerCount, int producerWage, int speed, String type){
        for (int i = 0; i < producerCount; i++){
            this.producers[i] = new Producer(producerWage, speed, type);
            this.producers[i].start();
        }
    }
}
