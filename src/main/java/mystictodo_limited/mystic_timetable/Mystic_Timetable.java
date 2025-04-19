/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mystictodo_limited.mystic_timetable;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.SplashScreen;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.UnknownHostException;
import mystictodo_limited.mystic_timetable.db.DbFolderPerUser;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import mystictodo_limited.mystic_timetable.UI.*;
import mystictodo_limited.mystic_timetable.config.ConfigStart;
import mystictodo_limited.mystic_timetable.db.DbConnectionManager;
import mystictodo_limited.mystic_timetable.db.DbUsers;
import mystictodo_limited.mystic_timetable.db.DbFolder;
import mystictodo_limited.mystic_timetable.hibernate.*;
import mystictodo_limited.mystic_timetable.hibernate.HUsers;
import mystictodo_limited.mystic_timetable.hibernate.HibernateUtil;
import mystictodo_limited.mystic_timetable.util.APIServer;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Jamario_Downer
 */
public class Mystic_Timetable {

    public static void main(String[] args) throws SQLException {
        //Start splash Screen +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // Create Threads
        Thread splashThread = new Thread(new StartSplashScreen());
        splashThread.start();
   
        // wait for both threads to comlete
        try{
           splashThread.join();
           //mainAppThread.join();
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
        
        //Start main app after the splash screen
        Thread mainAppThread = new Thread(new StartMainApp());
        mainAppThread.start();
        
        //Wait for the main app to complete
        try{
           mainAppThread.join();
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
        
        // Start the main app after the splash screen
//        SwingUtilities.invokeLater(() -> {
//            new JTimetableLoginPage().setVisible(true);
//        });
        
        
    
        //Start App directly +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //JTimetableLoginPage startApp = new JTimetableLoginPage();
        //startApp.setVisible(true);
        
        //SplashScreenStart spash = new SplashScreenStart();
        
        //LoadSettings
        //ConfigStart settings = new ConfigStart();
        
        //test ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //DbConnectionManager users = new DbConnectionManager();
        //dbConnectionManager.ViewAllUserEntryPrint();
       // DbUsers user1 = new DbUsers();
        //ArrayList<DbUsers> userList = user1.GetAllEntries();
        
        
        //test hibernate ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//        HUsers user = new HUsers();
//        user.setUserName("Rob12");
//        user.setEmailAddress("rob12@gmail.com");
//        user.setPassword("password");
//        user.setRegistrationDate(new Date());
//        
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(user);
//        transaction.commit();
//        session.close();
//        HibernateUtil.shutdown();
        
        //test hibernate2 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //Save Entiry
//        HFolderDAOImpl folderDao = new HFolderDAOImpl();
//        HFolder folder = new HFolder();
//        HUsersDAOImpl UserDAO = new HUsersDAOImpl();
//        HUsers users = new HUsers();
//       
//        folder.setFolderName("School");
//        folderDao.save(folder);
//        System.out.println("Folder saved");
//        
//        // find entry by id
//        HFolder foundFolder = folderDao.findById(folder.getFolderId());
//        // foundFolder = folderDao.findById(1);
//        HUsers foundUser = UserDAO.findById(1);
//        System.out.println("Found Folder Id : " + foundFolder);
//        
//        // update entry
//        foundFolder.setFolderName("SchoolTimetable");
//        folderDao.update(foundFolder);
//        
//        // delete entry
//        folderDao.delete(foundFolder);
        
        //get all related table info
        //foundFolder.setFolderPerUserList();
//        List<HFolderPerUser> folderPerUserList = foundFolder.getFolderPerUserList();
//        for (HFolderPerUser folderPerUser : folderPerUserList){
//            System.out.println( folderPerUser.getFolderPerUserId());
//           
//        }
//        
//        System.out.println("Nextlist :");
//        List<HFolderPerUser> userList = foundUser.getFolderPerUserList();
//        for (HFolderPerUser folderPerUser : userList){
//            System.out.println( folderPerUser.getFolderPerUserId());
//           
//        }
        
        
        
    }
}
