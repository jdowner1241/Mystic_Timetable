/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.JunitTest;

import java.sql.SQLException;
import java.util.Date;

import javax.annotation.processing.SupportedAnnotationTypes;

import mystictodo_limited.mystic_timetable.UI.JTimetableMain;
import mystictodo_limited.mystic_timetable.db.DbConnectionManager;
import mystictodo_limited.mystic_timetable.db.DbFolder;
import mystictodo_limited.mystic_timetable.db.DbUsers;
import mystictodo_limited.mystic_timetable.hibernate.*;
import  org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import org.junit.platform.*;
//import org.junit.platform.suite.api.Suite;

/**
 *
 * @author Jamario_Downer
 */
public class JunitAllTest {
    
     //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
    public JunitAllTest() {
        logger = new DbConnectionManager(JunitAllTest.class);

    }

    //Feilds >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
      private DbConnectionManager logger;
    //Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//    @Suite 
//    @SelectClasses({
//        JunitCRUDTraditionalTest.class,
//        JunitCRUDORMTest.class
//    })
      
      

    public class JunitAllTestSuite {
        // This class remains empty. It is used only as a holder for the above annotations
        // Unable to complete Suite above due to Junit dependency issue. No dependecy found for "Suite" 
    }


}
