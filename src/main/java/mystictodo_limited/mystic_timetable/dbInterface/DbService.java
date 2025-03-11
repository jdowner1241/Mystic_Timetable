/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mystictodo_limited.mystic_timetable.dbInterface;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jamario_Downer
 */
//public interface DbService<U, T> {
//    
//    void DeleteEntryById(T id) throws SQLException; 
//    
//    U GetEntrybyId(T id) throws SQLException;
//    
//    ArrayList<U> GetAllEntries() throws SQLException;
//    
//    void ViewAllEntryPrint() throws SQLException;
//    
//}

public interface DbService<T> {
    
    void DeleteEntryById(int id) throws SQLException; 
    
    T GetEntrybyId(int id) throws SQLException;
    
    ArrayList<T> GetAllEntries() throws SQLException;
    
    void ViewAllEntryPrint() throws SQLException;
    
}