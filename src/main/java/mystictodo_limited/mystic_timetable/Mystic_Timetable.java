/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mystictodo_limited.mystic_timetable;
import mystictodo_limited.mystic_timetable.db.DbFolderPerUser;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mystictodo_limited.mystic_timetable.UI.*;
import mystictodo_limited.mystic_timetable.db.DbConnectionManager;
import mystictodo_limited.mystic_timetable.db.DbUsers;
import mystictodo_limited.mystic_timetable.db.DbFolder;

/**
 *
 * @author Jamario_Downer
 */
public class Mystic_Timetable {

    public static void main(String[] args) throws SQLException {
        //DbConnectionManager users = new DbConnectionManager();
        //dbConnectionManager.ViewAllUserEntryPrint();
       // DbUsers user1 = new DbUsers();
        //ArrayList<DbUsers> userList = user1.GetAllEntries();

       // for ( var user : userList){
       //     System.out.println(user);
       // }
        
        //user1.ViewAllEntryPrint();
        //user1.UserInsertEntry("Bill1", "bill1@gmail.com", "password");
        //user1.UserDeleteEntryById(9);
        //user1.UserViewAllEntryPrint();
 
       // DbFolder folder1 = new DbFolder();
        //folder1.ViewAllEntryPrint();
       // DbFolder folder1 = new DbFolder();
       // folder1.FolderViewAllEntryPrint();
        
       //DbFolderPerUser pu = new DbFolderPerUser();
       //pu.ViewAllEntryPrint();
       //var list = pu.GetAllEntries();
       
       JTimetableLoginPage startApp = new JTimetableLoginPage();
       startApp.setVisible(true);
      
        
    }
}
