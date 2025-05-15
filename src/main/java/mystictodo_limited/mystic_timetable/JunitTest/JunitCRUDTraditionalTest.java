/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.JunitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import mystictodo_limited.mystic_timetable.db.DbConnectionManager;
import mystictodo_limited.mystic_timetable.db.DbFolder;
import mystictodo_limited.mystic_timetable.db.DbUsers;
import mystictodo_limited.mystic_timetable.hibernate.HDayOfTheWeek;
import mystictodo_limited.mystic_timetable.hibernate.HDayOfTheWeekDAOImpl;
import mystictodo_limited.mystic_timetable.hibernate.HFolder;
import mystictodo_limited.mystic_timetable.hibernate.HFolderDAOImpl;
import mystictodo_limited.mystic_timetable.hibernate.HFolderPerUser;
import mystictodo_limited.mystic_timetable.hibernate.HFolderPerUserDAOImpl;
import mystictodo_limited.mystic_timetable.hibernate.HFrequencyType;
import mystictodo_limited.mystic_timetable.hibernate.HFrequencyTypeDAOImpl;
import mystictodo_limited.mystic_timetable.hibernate.HLogEntry;
import mystictodo_limited.mystic_timetable.hibernate.HLogEntryDAOImpl;
import mystictodo_limited.mystic_timetable.hibernate.HUsers;
import mystictodo_limited.mystic_timetable.hibernate.HUsersDAOImpl;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 *
 * @author Jamario_Downer
 */
public class JunitCRUDTraditionalTest {
    //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public JunitCRUDTraditionalTest() {
        logger = new DbConnectionManager(JunitCRUDTraditionalTest.class);

        dbFolder = new DbFolder();
        dbUsers = new DbUsers();
    }

    //Feilds >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  
      private DbConnectionManager logger;
      private DbUsers dbUsers;
      private DbFolder dbFolder;

    //Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
 
    @Test
    public void testCRUDTraditional() {
        createObjects();
        TestSave();
        TestUpdate();
        TestDelete();
    }

    @Test
    private void TestDelete() {
        
        int userID = dbUsers.getUserId();
        int folderID = dbFolder.getFolderId();
    
        
        try {
            DbUsers deletedUser;
            DbFolder deletedFolder;
            dbUsers.DeleteEntryById(userID);
            dbFolder.DeleteEntryById(folderID);
            
            deletedUser = dbUsers.GetEntrybyId(userID);
            if (deletedUser.getUserId() == 0) {
                deletedUser = null;
            }
            deletedFolder = dbFolder.GetEntrybyId(folderID);
            if (deletedFolder.getFolderId() == 0) {
                deletedFolder = null;
            }

            assertNull(deletedUser);
            assertNull(deletedFolder);

        } catch (SQLException e) {
           logger.CreateLog("error", "Error occurred when deleting object", e);
        }
    }

    @Test
    private void TestUpdate() {
        String userName = "Updatedtestuser";
        String folderName = "UpdatedTestFolder";

        try {
            DbUsers priorUser = dbUsers.GetEntrybyId(dbUsers.getUserId());
            DbFolder priorFolder = dbFolder.GetEntrybyId(dbFolder.getFolderId());


            dbUsers.UpdateEntrybyId(priorUser.getUserId(), userName, priorUser.getEmailAddress(), priorUser.getPassword());
            dbFolder.UpdateEntrybyId(priorFolder.getFolderId(), folderName);
            assertNotEquals(priorUser, dbUsers);
            assertNotEquals(priorFolder, dbFolder);

        } catch (SQLException e) {
           logger.CreateLog("error", "Error occurred when updating object", e);
        }
    }

    @Test
    private void TestSave() {
        String userName = "testuser";
        String password = "testpassword";
        String email = "test@gmail.com";
        String folderName = "Test Folder";

        try {
            Boolean result = dbUsers.InsertEntry(userName, email, password);
            assertEquals(true, result);

            DbUsers retrievedUser = dbUsers.GetEntrybyUserName(userName);
            assertNotNull(retrievedUser);
            DbFolder retrievedFolder = dbFolder.GetEntrybyFolderName(folderName);
            assertNotNull(retrievedFolder);

        } catch (SQLException e) {
           logger.CreateLog("error", "Error occurred when saving object", e);
        }
    }

    private void createObjects() {
        String userName = "testuser";
        String password = "testpassword";
        String email = "test@gmail.com";
        String folderName = "Test Folder";
     
    }


    
}
