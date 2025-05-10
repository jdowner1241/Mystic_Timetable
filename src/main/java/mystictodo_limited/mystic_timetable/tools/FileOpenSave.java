/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import mystictodo_limited.mystic_timetable.db.DbConnectionManager;

/**
 *
 * @author Jamario_Downer
 */
public class FileOpenSave {
    
    //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public FileOpenSave(){
       logger = new DbConnectionManager(FileOpenSave.class);
    }
    
    //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private DbConnectionManager logger;
    
    
    //Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    
    //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
     
   //Method to save an object to a file usig JFileChooser (serialization method)
   public void saveToFile(Object object) throws IOException {
   
      JFileChooser fileChooser = new JFileChooser();
      int userSelection = fileChooser.showSaveDialog(null);
      
      if (userSelection == JFileChooser.APPROVE_OPTION){
          File fileToSave = fileChooser.getSelectedFile();
          
          FileOutputStream file = new FileOutputStream(fileToSave);
          ObjectOutputStream out = new ObjectOutputStream(file);
          
          out.writeObject(object);
          out.close();
          file.close();
          
          JOptionPane.showMessageDialog(null, "File saved!", "information", JOptionPane.INFORMATION_MESSAGE );
          logger.CreateLog("info", "Object has been serialized and saved to " 
                  + fileToSave.getAbsolutePath(), null);
          
      }
   
   }//End of saveToFile method

   
   //Method to save an object to a file using direct method
   public void saveToFileDirect(File file, String destinationPath) throws IOException {
        InputStream fileInputStream = null;
        OutputStream fileOutputStream = null;
       
        //saves received file
       try {
            //Initialize input stream      
            fileInputStream = new FileInputStream(file);
            
            //choose a location base on weather a destination was mentioned
            if (destinationPath == null || destinationPath.isBlank()){
                fileOutputStream = new FileOutputStream("received_" + file.getName());
            }else {
                fileOutputStream = new FileOutputStream(destinationPath);  
            }

            byte[] buffer = new byte[1024]; 
            int bytesRead;
            
            //reads the file and saves once fully read.
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }//End whle loop
            JOptionPane.showMessageDialog(null, "File saved!", "information", JOptionPane.INFORMATION_MESSAGE );
        } catch (IOException e) {
            logger.CreateLog("error", "Failed to save File. ", e);
        } finally {
           //Close resources 
           if(fileInputStream != null){
               fileInputStream.close();
           }
           if (fileOutputStream != null){
               fileOutputStream.close();
           }
       }
       
    }//End saveFile
   
   
   //Method to open an object from a file using JFileChooser
   public Object openFromFile() throws IOException, ClassNotFoundException {
       JFileChooser fileChooser = new JFileChooser();
       
       int userSelection = fileChooser.showOpenDialog(null);
       
       if (userSelection == JFileChooser.APPROVE_OPTION) {
           File fileToOpen = fileChooser.getSelectedFile(); // only accept one file for now, will add multi file selection in the future
           
           FileInputStream file = new FileInputStream(fileToOpen);
           ObjectInputStream in = new ObjectInputStream(file);
           
           Object object = in.readObject();
           in.close();
           file.close();
           
           logger.CreateLog("info", "Object has been deserialized from " 
                   + fileToOpen.getAbsolutePath(), null);
           return object;
       }
       return null;
   }//End of openFromFile method

}// End class
