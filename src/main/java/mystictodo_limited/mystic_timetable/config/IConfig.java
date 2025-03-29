/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mystictodo_limited.mystic_timetable.config;

/**
 *
 * @author Jamario_Downer
 */
public interface IConfig {
    
    public interface IConfigBase{
     
        static void saveSettings(String key, String value){};
        static String loadSettings(String key, String defaultValue){
            return defaultValue;
        };
    }
    
    public interface IConfigNetwork extends IConfigBase {
        
        static void savePort(int port){};
        static void loadPort(){};
    }

    public interface IConfigGraphic extends IConfigBase {

    }
    
    public interface IConfigLogs extends IConfigBase {
    
    }
}

