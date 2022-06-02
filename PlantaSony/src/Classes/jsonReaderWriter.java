/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author ponsa
 */
public class jsonReaderWriter {
    
    public static void write(){
            //First Employee
        JSONObject employeeDetails = new JSONObject();
        employeeDetails.put("firstName", "Lokesh");
        employeeDetails.put("lastName", "Gupta");
        employeeDetails.put("website", "howtodoinjava");
         
        JSONObject employeeObject = new JSONObject(); 
        employeeObject.put("employee", employeeDetails);
         
        //Second Employee
        JSONObject employeeDetails2 = new JSONObject();
        employeeDetails2.put("firstName", "Brian");
        employeeDetails2.put("lastName", "Schultz");
        employeeDetails2.put("website", "example.com");
         
        JSONObject employeeObject2 = new JSONObject(); 
        employeeObject2.put("employee", employeeDetails2);
         
        //Add employees to list
        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeObject);
        employeeList.add(employeeObject2);
        //Write JSON file
        try (FileWriter file = new FileWriter("corridas.json", true)) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(employeeList.toJSONString()); 
            file.write(System.getProperty("line.separator"));
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    
    public static void readExternal(String file) throws org.json.simple.parser.ParseException{
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(file))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray runCharact = (JSONArray) obj;
             
            runCharact.forEach( corrida -> parseRunCharactObject( (JSONObject) corrida ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    
    private static void parseRunCharactObject(JSONObject corrida) 
    {
        JSONObject corridaObject = (JSONObject) corrida.get("corrida");
         
        int secsInDay = parseInt((String)(corridaObject.get("secsInDay")));    
        System.out.println(secsInDay);
        Main.readJson[0] = secsInDay;
         
        int daysBetweenDispatchs = parseInt((String) corridaObject.get("daysBetweenDispatchs"));  
        System.out.println(daysBetweenDispatchs);
        Main.readJson[1] = daysBetweenDispatchs;
        
        JSONObject cantidadMaximaObject = (JSONObject) corridaObject.get("cantidadMaxima");
        
        int cantidadMaximaPines = parseInt((String) cantidadMaximaObject.get("cantidadMaximaPines"));
        System.out.println(cantidadMaximaPines);
        Main.readJson[2] = cantidadMaximaPines;
        
        int cantidadMaximaPantallas = parseInt((String) cantidadMaximaObject.get("cantidadMaximaPantallas"));
        System.out.println(cantidadMaximaPantallas);
        Main.readJson[3] = cantidadMaximaPantallas;
        
        int cantidadMaximaCamaras = parseInt((String) cantidadMaximaObject.get("cantidadMaximaCamaras"));
        System.out.println(cantidadMaximaCamaras);
        Main.readJson[4] = cantidadMaximaCamaras;
        
        int cantidadMaximaBotones = parseInt((String) cantidadMaximaObject.get("cantidadMaximaBotones"));
        System.out.println(cantidadMaximaBotones);
        Main.readJson[5] = cantidadMaximaBotones;
        
        JSONObject cantidadInicialProductoresObject = (JSONObject) corridaObject.get("cantidadInicialProductores");
        
        int cantidadProductoresPines = parseInt((String) cantidadInicialProductoresObject.get("cantidadProductoresPines"));
        System.out.println(cantidadProductoresPines);
        Main.readJson[6] = cantidadProductoresPines;
        
        int cantidadProductoresPantallas = parseInt((String) cantidadInicialProductoresObject.get("cantidadProductoresPantallas"));
        System.out.println(cantidadProductoresPantallas);
        Main.readJson[7] = cantidadProductoresPantallas;
        
        int cantidadProductoresCamaras = parseInt((String) cantidadInicialProductoresObject.get("cantidadProductoresCamaras"));
        System.out.println(cantidadProductoresCamaras);
        Main.readJson[8] = cantidadProductoresCamaras;
        
        int cantidadProductoresBotones = parseInt((String) cantidadInicialProductoresObject.get("cantidadProductoresBotones"));
        System.out.println(cantidadProductoresBotones);
        Main.readJson[9] = cantidadProductoresBotones;
        
        int cantidadEnsambladores = parseInt((String) corridaObject.get("cantidadEnsambladores"));  
        System.out.println(cantidadEnsambladores);
        Main.readJson[10] = cantidadEnsambladores;
        
        
    }
        
        public static void read(String file) throws org.json.simple.parser.ParseException{
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(file))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray run = (JSONArray) obj;
            System.out.println(run);
             
            run.forEach( corrida -> parseCorridaObject( (JSONObject) corrida ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        
        private static void parseCorridaObject(JSONObject corrida) 
    {
        JSONObject corridaObject = (JSONObject) corrida.get("corrida");
         
        int runNumber = parseInt((String)(corridaObject.get("runNumber")));    
        System.out.println(runNumber);
         
        String productionPlant = (String) corridaObject.get("productionPlant");  
        System.out.println(productionPlant);
         
        boolean employeeDistribution = parseBoolean((String) corridaObject.get("employeeDistribution"));    
        System.out.println(employeeDistribution);
    }
}
