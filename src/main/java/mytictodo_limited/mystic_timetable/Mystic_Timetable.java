/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mytictodo_limited.mystic_timetable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import mytictodo_limited.mystic_timetable.db.*;
import mytictodo_limited.mystic_timetable.db.DbConnectionManager;
import mytictodo_limited.mystic_timetable.db.DbUsers;
import mytictodo_limited.mystic_timetable.db.DbFolder;

/**
 *
 * @author Jamario_Downer
 */
public class Mystic_Timetable {

    public static void main(String[] args) throws SQLException {
        DbConnectionManager users = new DbConnectionManager();
        //dbConnectionManager.ViewAllUserEntryPrint();
        DbUsers user1 = new DbUsers();
        //user1.UserInsertEntry("Bill", "bill@gmail.com", "password");
        user1.UserDeleteEntryById(9);
        user1.UserViewAllEntryPrint();
        
       // DbFolder folder1 = new DbFolder();
       // folder1.FolderViewAllEntryPrint();
        
        
    }
}
