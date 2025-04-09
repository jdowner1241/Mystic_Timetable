/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Jamario_Downer
 */
public class SplashScreenPanel extends JPanel implements ActionListener {
     private Image image1;
        private Image image2;
        private Timer timer;
        private int x1, y1, x2, y2;
        private boolean moveRight;
        
        public SplashScreenPanel() {
            image1 = new ImageIcon("src/main/resources/Assets/Images/Splash1.png").getImage();
            image2 = new ImageIcon("src/main/resources/Assets/Images/Splash2.png").getImage();
            
            x1 = 0;
            y1 = 0;
            x2 = 200;
            x2 = 200;
            moveRight =true;
            timer = new Timer(20, this);
            timer.start();
        
        }
        
//        @Override
//        protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//            Graphics2D g2d = (Graphics2D) g;
//        if (image1 != null && image2 != null) {
//            g2d.drawImage(image1, x1, y1, this);
//            g2d.drawImage(image2, x2, y2, this);
//        }
//        }
        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if (moveRight) {
                x1 += 2;
                y1 += 2;
                if (x1 > getWidth() - image1.getWidth(this) || x2 < 0) {
                    moveRight = false;
                } else {
                    x1 -= 2;
                    x2 += 2;
                    
                    if (x1 < 0 || x2 > getWidth() - image2.getWidth(this)){
                        moveRight = true;
                    
                    }
                }
                repaint();
            }
            
        }
}
