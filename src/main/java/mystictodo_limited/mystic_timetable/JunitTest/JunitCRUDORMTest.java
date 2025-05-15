/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.JunitTest;

import java.sql.SQLException;
import java.util.Date;

import mystictodo_limited.mystic_timetable.UI.JTimetableMain;
import mystictodo_limited.mystic_timetable.db.DbConnectionManager;
import mystictodo_limited.mystic_timetable.db.DbFolder;
import mystictodo_limited.mystic_timetable.db.DbUsers;
import mystictodo_limited.mystic_timetable.hibernate.*;
import  org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 * @author Jamario_Downer
 */
public class JunitCRUDORMTest {

     //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
    public JunitCRUDORMTest() {
        logger = new DbConnectionManager(JunitCRUDORMTest.class);

        // create a new instance of the class DAOImpl
        hDayOfTheWeekDAO = new HDayOfTheWeekDAOImpl();
        hFolderDAO = new HFolderDAOImpl();
        hUsersDAO = new HUsersDAOImpl();
        
        // create a new instance of the class
        hDayOfTheWeek = new HDayOfTheWeek();
        hFolder = new HFolder();
        hUsers = new HUsers();

    }

    //Feilds >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
      private DbConnectionManager logger;

        // create a new instance of the class DAOImpl
        HDayOfTheWeekDAOImpl hDayOfTheWeekDAO;
        HFolderDAOImpl hFolderDAO;
        HUsersDAOImpl hUsersDAO;
        
        // create a new instance of the class
        HDayOfTheWeek hDayOfTheWeek;
        HFolder hFolder;
        HUsers hUsers;

        HDayOfTheWeek retrievedDayOfTheWeek;
        HFolder retrievedFolder;
        HUsers retrievedUsers;

        HDayOfTheWeek updatedDayOfTheWeek;
        HFolder updatedFolder;
        HUsers updatedUsers;
    //Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
    @Test   
    public void testCRUDORM() { 
        createObjects();
        TestSave();
        TestUpdate();
        TestDelete();
    }

    @Test 
    private void TestDelete() {
        // delete the objects
        hDayOfTheWeekDAO.delete(hDayOfTheWeek);
        hFolderDAO.delete(hFolder);
        hUsersDAO.delete(hUsers);

        // test if the objects were deleted
        HDayOfTheWeek deletedDayOfTheWeek = hDayOfTheWeekDAO.findById(hDayOfTheWeek.getDayId());
        HFolder deletedFolder = hFolderDAO.findById(hFolder.getFolderId());
        HUsers deletedUsers = hUsersDAO.findById(hUsers.getUserId());

        // test if the objects were deleted
        assertEquals(null, deletedDayOfTheWeek);
        assertEquals(null, deletedFolder);
        assertEquals(null, deletedUsers);
    }

    @Test
    private void TestUpdate() {
        // Update the objects
        hDayOfTheWeek.setDayName("Updated Test");
        hFolder.setFolderName("Updated Test"); 
        hUsers.setUserName("Updated Test");
    

        // Update to database 
        hDayOfTheWeekDAO.update(hDayOfTheWeek);
        hFolderDAO.update(hFolder);
        hUsersDAO.update(hUsers);

        //test if the objects were updated
        updatedDayOfTheWeek = hDayOfTheWeekDAO.findById(hDayOfTheWeek.getDayId());
        updatedFolder = hFolderDAO.findById(hFolder.getFolderId());
        updatedUsers = hUsersDAO.findById(hUsers.getUserId());
        
        // test if the objects were retrieved
        assertNotNull(updatedDayOfTheWeek);
        assertNotNull(updatedFolder);
        assertNotNull(updatedUsers);


        // test if the objects were updated
        assertNotEquals(retrievedDayOfTheWeek, updatedDayOfTheWeek);
        assertNotEquals(retrievedFolder, updatedFolder);
        assertNotEquals(retrievedUsers, updatedUsers);
    }

    @Test
    private void TestSave() {
        // test if the objects were saved
        try {
            // save the objects
            hDayOfTheWeekDAO.save(hDayOfTheWeek);
            hFolderDAO.save(hFolder);
            hUsersDAO.save(hUsers);

            retrievedDayOfTheWeek = hDayOfTheWeekDAO.findById(hDayOfTheWeek.getDayId());
            retrievedFolder = hFolderDAO.findById(hFolder.getFolderId());
            retrievedUsers = hUsersDAO.findById(hUsers.getUserId());

            // test if the objects were retrieved
            assertNotNull(retrievedDayOfTheWeek);
            assertNotNull(retrievedFolder);
            assertNotNull(retrievedUsers);
        } catch (Exception e) {
            logger.CreateLog("error", "Failed to retrive saved objects", e);
        }
    }

    @Test
    private void createObjects(){
        // create objects
        hDayOfTheWeek.setDayName("Test");
        hDayOfTheWeek.setDayId(8);

        hFolder.setFolderName("Test");

        hUsers.setUserName("Test");
        hUsers.setEmailAddress("test@gmail.com");
        hUsers.setPassword("testtest");
        Date date = new Date();
        java.time.LocalDateTime localDateTime = java.time.LocalDateTime.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDateTime.toLocalDate());
        date = sqlDate;
        hUsers.setRegistrationDate(date);

    }
    
}
