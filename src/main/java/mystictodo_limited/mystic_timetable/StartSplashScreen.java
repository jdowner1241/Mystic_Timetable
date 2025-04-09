/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable;

import javax.swing.SwingUtilities;
import mystictodo_limited.mystic_timetable.UI.SplashScreenUI;
import mystictodo_limited.mystic_timetable.db.DbConnectionManager;

/**
 *
 * @author Jamario_Downer
 */
public class StartSplashScreen implements Runnable {

    //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
    public StartSplashScreen (){
        logger = new DbConnectionManager(StartSplashScreen.class);
        this.splashUI = new SplashScreenUI(null, true);
    }
    
    //fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
    private DbConnectionManager logger;
    int MaxSteps = 1000;
    SplashScreenUI splashUI;
    
    //Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
    
    //Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
    
    @Override
    public void run() {

      try{
          SwingUtilities.invokeLater(() -> splashUI.setVisible(true));

          
          for (int i = 0; i <= MaxSteps; i++){
              float value = (float) i/MaxSteps * 100;
              int loadingPercentage = Math.round(value);
              
              SwingUtilities.invokeLater(() -> {
                    splashUI.UpdateLoadingbar(loadingPercentage, loadingPercentage);
              });
                     
              Thread.sleep(5); // Simulate splash screen loading time
          }
          
          SwingUtilities.invokeLater(() -> splashUI.dispose());
         //splashUI.dispose();
      
      } catch (InterruptedException e){
          logger.CreateLog("error", "Splash Screen done. ", e);
      }
    }
}
