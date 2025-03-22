/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Jamario_Downer
 */
public class validCheck {
    
    //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
    
       
    //verify if text field is empty or blank
    public static boolean checkTextField(JTextField tF){
        boolean isvalid = true;
        
        //Test UserName
        if(tF == null || tF.getText().isBlank()){
            isvalid = false;
            
            tF.grabFocus();
        }
        
        return isvalid;
    }
    
     //verify if email field is empty or blank
    public static boolean checkEmailField(JTextField eF){
        boolean isvalid = true;
        if(eF == null || eF.getText().isBlank()){
            isvalid = false;
            
            eF.grabFocus();
        }else{
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(eF.getText());
            if(!matcher.matches()){
                isvalid = false;
                
                eF.grabFocus();
            }
        }
        
        return isvalid;
    }
    
     //verify if Password field is empty or blank
    public static boolean checkPasswordField(JPasswordField pF){
        boolean isvalid = true;
        
        if(pF == null){
           isvalid = false;
           
           pF.grabFocus();
        }else{
            char[] passwordChar = pF.getPassword();
            String password = new String(passwordChar);
            
            if(password.isBlank()){
                isvalid = false;
                
                pF.grabFocus();
            } 
        }
        
        return isvalid;
    }
    
}
