/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import mystictodo_limited.mystic_timetable.config.IConfig.IConfigGraphic;
import mystictodo_limited.mystic_timetable.config.IConfig.IConfigLogs;
import mystictodo_limited.mystic_timetable.config.IConfig.IConfigNetwork;

/**
 *
 * @author Jamario_Downer
 */
public class ConfigManager implements IConfigNetwork, IConfigGraphic, IConfigLogs{
    
//Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 public ConfigManager(){
    
     this.configGraphic = new ConfigGraphic();
     this.configLogs = new ConfigLogs();
     this.configNetwork = new ConfigNetwork();
}   
    
    
//Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 private static final String CONFIG_File = "config.properties";
 private static Properties properties = new Properties();
 private ConfigGraphic configGraphic;
 private ConfigLogs configLogs;
 private ConfigNetwork configNetwork;


//Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    

//Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
 static {
     try (FileInputStream inputStream = new FileInputStream(CONFIG_File)) {
     
         properties.load(inputStream);
         
     } catch (FileNotFoundException ex) {
         Logger.getLogger(ConfigManager.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
         Logger.getLogger(ConfigManager.class.getName()).log(Level.SEVERE, null, ex);
     }
   }
 
 public static void saveSetting(String key, String value) {
     properties.setProperty(key, value);
     try (FileOutputStream outputStream = new FileOutputStream(CONFIG_File))
     {
        properties.store(outputStream, value);
     } catch (IOException e) {
         e.printStackTrace();
     }
 } 
 
 public static String loadSetting(String key, String defaultValue) {
     return properties.getProperty(key, defaultValue);
 }
 
  public static void savePort(int port) {
        saveSetting("port", String.valueOf(port));
    }
    
    public static int loadPort() {
        return Integer.parseInt(loadSetting("port", "6366"));
    }
 
 
}//End class
