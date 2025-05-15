/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package mystictodo_limited.mystic_timetable.JunitTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jamario_Downer
 */
public class JunitCRUDORMTestIT {
    
    public JunitCRUDORMTestIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of testCRUDORM method, of class JunitCRUDORMTest.
     */
    @Test
    public void testTestCRUDORM() {
        System.out.println("testCRUDORM");
        JunitCRUDORMTest instance = new JunitCRUDORMTest();
        instance.testCRUDORM();
    }
    
}
