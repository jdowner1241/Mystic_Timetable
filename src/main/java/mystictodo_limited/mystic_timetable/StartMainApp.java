/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import mystictodo_limited.mystic_timetable.UI.JTimetableLoginPage;
import mystictodo_limited.mystic_timetable.db.DbConnectionManager;
import mystictodo_limited.mystic_timetable.tools.FileOpenSave;

/**
 *
 * @author Jamario_Downer
 */
public class StartMainApp implements Runnable {

    //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
    public StartMainApp (){
        logger = new DbConnectionManager(StartMainApp.class);
    }
    
    //fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
    private DbConnectionManager logger;
    int MaxSteps = 1000;
    
    //Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
    
    //Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
    
    @Override
    public void run() {
        try {
              Thread.sleep(1);
              
              
              //SwingUtilities.invokeLater(() -> {
              //  new JTimetableLoginPage().setVisible(true);
              //});
            
            JTimetableLoginPage startApp = new JTimetableLoginPage();
            startApp.setVisible(true);
            logger.CreateLog("info", "Main program running.", null);
            
        } catch (InterruptedException ex) {
            logger.CreateLog("error", "Main program failed to run.", ex);
        }
    }
}
