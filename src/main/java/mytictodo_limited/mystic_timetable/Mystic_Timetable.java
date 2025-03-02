/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mytictodo_limited.mystic_timetable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import mytictodo_limited.mystic_timetable.db.dbConnectionManager;

/**
 *
 * @author Jamario_Downer
 */
public class Mystic_Timetable {

    public static void main(String[] args) throws SQLException {
        dbConnectionManager users = new dbConnectionManager();
        //dbConnectionManager.ViewAllUserEntryPrint();
        
        List<Map<Integer, Map<String, Object>>> result = users.ViewAllUserEntryReturn();
        
        for (Map<Integer, Map<String, Object>> map : result) {
            for (Map.Entry<Integer, Map<String, Object>> entry : map.entrySet()) {
                System.out.println("Entry ID: " + entry.getKey());
                for (Map.Entry<String, Object> innerEntry : entry.getValue().entrySet()) {
                    System.out.println(innerEntry.getKey() + ": " + innerEntry.getValue());
                }
                System.out.println();
            }
        }
    }
}
