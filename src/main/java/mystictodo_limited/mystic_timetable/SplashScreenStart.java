/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import mystictodo_limited.mystic_timetable.UI.JTimetableLoginPage;

/**
 *
 * @author Jamario_Downer
 */
public class SplashScreenStart {
    
    public static void main(String[] args){
        //Start splash Screen +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        java.awt.SplashScreen splash = java.awt.SplashScreen.getSplashScreen();
        if(splash != null){
            Graphics2D g = splash.createGraphics();
            if(g != null){
                //Load the image
                Image image1 = Toolkit.getDefaultToolkit().getImage("src/main/resources/Assets/Images/Splash1.png");
                g.drawImage(image1, 0, 0, splash.getSize().width, splash.getSize().height, null);
                

                //g.drawString("Loading...", 20, 20);
                splash.update();
            }
        }
        
        
        // Simulate loading time
        try {
            Thread.sleep(5000); // 5 seconds
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        
        // Launch the main application window
        SwingUtilities.invokeLater(() -> {
            JTimetableLoginPage startApp = new JTimetableLoginPage();
            startApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            startApp.setVisible(true);
        });
        
    
    }
}
