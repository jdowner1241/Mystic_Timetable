/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import mystictodo_limited.mystic_timetable.db.DbConnectionManager;

/**
 *
 * @author Jamario_Downer
 */
public class SerializeFile {
    
    //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public SerializeFile(){
        
        logger = new DbConnectionManager(SerializeFile.class);
        this.fileOpenSave = new FileOpenSave();
    }
    
    //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private DbConnectionManager logger;
    private FileOpenSave fileOpenSave;
    
    //Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    //Serialization : Object to bytes
    public <T extends Serializable> void serializeObj(T object ){
        try {
            //use the FileOpenSave class to save the file
            fileOpenSave.saveToFile(object);
            
            
        } catch (IOException ex){
            logger.CreateLog("error", "IOException caught.", ex);
        }
    
    }//End SerializeObj class
    
    //Deserialization : bytes to object
    public <T extends Serializable> T deserializeObj() {
        T object = null;
        
        try {
           //user the FileOpenSave class to open a file
           object = (T) fileOpenSave.openFromFile();
           
        } catch (IOException ex){
            logger.CreateLog("error", "IOException is caught. ", ex);
        } catch (ClassNotFoundException ex){
            logger.CreateLog("error", "ClassNotFoundException is caught. ", ex);
        }
        
        return object;
        
    }//End deserializeObj method
    
}//End class
