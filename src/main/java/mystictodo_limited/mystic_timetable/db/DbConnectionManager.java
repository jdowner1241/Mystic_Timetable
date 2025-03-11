/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.db;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import javax.swing.RowFilter.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 * @author Jamario_Downer
 */
public class DbConnectionManager {
    //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
    public DbConnectionManager (){
        log.info("Default Constructor Triggered.");
        mapList = new ArrayList<>();
    }
    
    public DbConnectionManager (Class<?> clazz){
        log.info("Parameterized Constructor Triggered.");
        this.otherLogger = LogManager.getLogger(clazz);
        otherClassName = clazz.getName();
    }
    
 //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
    private String otherClassName;
    private Map<String, Object> singleMap;
    private List<Map <Integer, Map<String, Object>>> mapList;
    
    //private Map<String, V> columnList;
    //private ArrayList<T> TableList;

    private static final Logger log = LogManager.getLogger(DbConnectionManager.class);
    protected Logger otherLogger;
 //Getter/Setter >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
    
    // Getter and Setter for singleMap
    public Map<String, Object> getSingleMap(){
        return singleMap;
    }
    public void setSingleMap(String keyName, Object obj) {
        if(singleMap == null) {
            singleMap = new HashMap<>();
        }
        singleMap.put(keyName , obj);
    }
    
    // Getter and Setter for mapList
    public List<Map<Integer, Map<String, Object>>> getMapList() {
        return mapList;
    }
    public void addMapToList(int entryId, Map<String, Object> singleEntry){
        if(entryId >= mapList.size()){
            for (int i = mapList.size(); i <= entryId; i++){
                mapList.add(new HashMap<>());
            }
        }
        mapList.get(entryId).put(entryId, singleEntry);
    }
    
    
    
 //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
   
    // Return Connection 
    public static Connection Connection() {
        log.info("Connection method triggered.");
        Connection con = null;
        try{
            // connection info
            final String url = "jdbc:mysql://localhost:3306/mystic_timetable?zeroDateTimeBehavior=CONVERT_TO_NULL";
            final String user="root";
            final String password="Blade1241$";
            
           // Load the MySQL JDBC Driver
           Class.forName("com.mysql.cj.jdbc.Driver");
           log.info("Connection Driver loaded.");
           
           //Establish a connection
           con = DriverManager.getConnection(url, user, password);
           log.info("Connection Established.");
        }
        catch (ClassNotFoundException e){
           System.out.println("JDBC Not Found");
           
        }
        
        catch (SQLException e){
            System.out.println("Connection Failed");
            log.error("Connection Failed.");
            log.debug("SQLException Error : " + e);
        }
    
        log.info("Connection returned to variable con.");
        return con;
    }
    
//    public <T, V> void setTableContents (Map<String, V>[] columnList, ArrayList TableList  )
//    { 
//            
//           //for (int i = 0; i < columnList.length; i++){}
//   };
    
    // Create Log
     public void CreateLog (String level, String message, SQLException e) {
        //Logger log = LogManager.getLogger(DbUsers.class);
     
         String className = otherClassName;
         
        //Create Log String 
        String logString = "Class: " + className + "Action: " + message; 
         
         //Use Switch Statement to Save the log
         if(e != null) {
            switch(level){
                case "debug" :
                    otherLogger.debug(logString);
                    otherLogger.debug("\nDetail Debug: " + e);
                    System.out.println(logString);
                    break;
                case "info" :
                    otherLogger.info(logString);
                    otherLogger.info("\nDetail Info: " + e);
                    System.out.println(logString);
                    break;
                case "warn" :
                    otherLogger.warn(logString);
                    otherLogger.warn("\nDetail Warn: " + e);
                    System.out.println(logString);
                    break;
                case "error" :
                    otherLogger.error(logString);
                    otherLogger.error("\nDetail Error: " + e);
                    System.out.println(logString);
                    break;
                case "fatal" :
                    otherLogger.fatal(logString);
                    otherLogger.fatal("\nDetail Fatal: " + e);
                    System.out.println(logString);
                    break; 
            }
         }else{
         switch(level){
                case "debug" :
                    otherLogger.debug(logString);
                    System.out.println(logString);
                    break;
                case "info" :
                    otherLogger.info(logString);
                    System.out.println(logString);
                    break;
                case "warn" :
                    otherLogger.warn(logString);
                    System.out.println(logString);
                    break;
                case "error" :
                    otherLogger.error(logString);
                    System.out.println(logString);
                    break;
                case "fatal" :
                    otherLogger.fatal(logString);
                    System.out.println(logString);
                    break; 
            }
         }
     }
    
    public static void ShowLog() {
    
    }
}
