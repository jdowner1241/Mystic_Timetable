/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mystictodo_limited.mystic_timetable;
import java.io.IOException;
import java.net.UnknownHostException;
import mystictodo_limited.mystic_timetable.db.DbFolderPerUser;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import mystictodo_limited.mystic_timetable.UI.*;
import mystictodo_limited.mystic_timetable.db.DbConnectionManager;
import mystictodo_limited.mystic_timetable.db.DbUsers;
import mystictodo_limited.mystic_timetable.db.DbFolder;
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
        
        //Start App +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        JTimetableLoginPage startApp = new JTimetableLoginPage();
        startApp.setVisible(true);
        
        
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
        
        
        

        
    }
}
