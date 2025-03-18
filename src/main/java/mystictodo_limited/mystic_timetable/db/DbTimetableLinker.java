/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.db;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.RowFilter.Entry;
import mystictodo_limited.mystic_timetable.dbInterface.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Jamario_Downer
 */
public class DbTimetableLinker extends DbConnectionManager implements DbService<DbTimetableLinker> {
 //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
 public DbTimetableLinker(){
     //log.info("Class: DbTimetableLinker. Action: Default Constructor Triggered.");
     //Connection con = Connection();
     
     super(DbTimetableLinker.class);
     CreateLog("info", "Default Constructor Triggered.", null);
 }
    
 //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     
 //private Connection con; 
 
 private int timetableLinkerId;
 private int eventId;
 private int userAndUserAndFolderId;

 private ArrayList<DbTimetableLinker> timetableLinkerList;
 
 //private static final Logger log = LogManager.getLogger(DbTimetableLinker.class);
 //Getter/Setter >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 //TimetableLinker
 public int getTimetableLinkerId(){
     return timetableLinkerId;
 }
 public void setTimetableLinkerId(int timetableLinkerId){
     this.timetableLinkerId = timetableLinkerId;
 }
 
 //EventId
 public int getEventId(){
     return eventId;
 }
 public void setEventId(int eventId){
     this.eventId = eventId;
 }
 
 //UserAndFolderId
 public int getUserAndUserAndFolderId(){
     return userAndUserAndFolderId;
 }
 public void setUserAndUserAndFolderId(int userAndUserAndFolderId){
     this.userAndUserAndFolderId = userAndUserAndFolderId;
 }
 
 
 //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
 //Insert Entry 
    public void InsertEntry(int eventId, int userAndFolderId) throws SQLException{
        //log.info("Class: DbTimetableLinker. Action: Insert Entry Operation Triggered.");
        CreateLog("info", "Insert Entry Operation Triggered.", null);
        
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            int _eventId = eventId;
            int _userAndFolderId = userAndFolderId;
            
            boolean isValid = true;
            
           if (isValid == true) 
           {
               //insert to database
                String insertSQL = "INSERT INTO timetablelinker "
                        + "(EventId, UserAndFolderId) "
                        + "VALUE (?,?)";
                PreparedStatement ps = con.prepareStatement(insertSQL);
                ps.setInt(1, _eventId);
                ps.setInt(2, _userAndFolderId);   
            
                int rowsInserted = ps.executeUpdate();
            
                if(rowsInserted > 0) {
                    //System.out.println("Class: DbTimetableLinker. Action: Entry Inserted.");
                    //log.info("Class: DbTimetableLinker. Action: Entry Inserted.");
                    //con.close(); //Close Connection
                   //log.info("Connection closed");
                   
                   CreateLog("info", "Entry Inserted.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                }
           } else 
           {
             
             //System.out.println("Class: DbTimetableLinker. Action: Validation Failed. Entry Not Added.");
            // log.error("Class: DbTimetableLinker. Action: Validation Failed. Entry Not Added.");
             //con.close(); //Close Connection
             //log.info("Connection closed");
               
              // Data not saved due to validation 
               CreateLog("error", "Validation Failed. Entry Not Added.", null);  
               con.close(); //Close Connection
               CreateLog("info", "Connection closed.", null); 
           }
        }
        catch(SQLException e) {
            //System.out.println("Class: DbTimetableLinker. Action: Connection Failed. Entry Not Added");
            //log.error("Class: DbTimetableLinker. Action: Connection Failed. Entry Not Added");
            //log.error("\nDetail Error: " + e);
            
            CreateLog("error", "Connection Failed. Entry Not Added.", e);
        }
    }
    
    //Update Entry
    public void UpdateEntrybyId(int id, int eventId, int userAndFolderId) throws SQLException{
        //log.info("Class: DbTimetableLinker. Action: Update Entry by Id operation triggered. ");
        CreateLog("info", "Update Entry by Id operation triggered.", null);
        
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            int _eventId = eventId;
            int _userAndFolderId = userAndFolderId;
  
            boolean isValid = true;
            
            if (isValid == true)
            {
                // update the entry
                String updateSQL = "UPDATE timetablelinker "
                        + "SET EventId = ? UserAndFolderId = ? "
                        + "WHERE TimetableLinkerId = ?";
                PreparedStatement psUpdate  = con.prepareStatement(updateSQL);
                psUpdate.setInt(1, _eventId);
                psUpdate.setInt(2, _userAndFolderId);
                psUpdate.setInt(4, id);
                int rowsUpdated = psUpdate.executeUpdate();
                
                if(rowsUpdated > 0) {
                    //System.out.println("Class: DbTimetableLinker. Action: Entry Updated.");
                    //log.info("Class: DbTimetableLinker. Action: Entry Updated.");
                    //con.close(); //Close Connection
                   // log.info("Connection closed");
                   
                   CreateLog("info", "Entry Updated.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                }
            }else
            {
                 
                //System.out.println("Class: DbTimetableLinker. Action: Validation Failed. Entry Not Updated.");
                //log.error("Class: DbTimetableLinker. Action: Validation Failed. Entry Not Updated.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
               
               // Data not saved due to validation
               CreateLog("error", "Validation Failed. Entry Not Updated.", null);  
               con.close(); //Close Connectionr
               CreateLog("info", "Connection closed.", null); 
            }  
        }
        catch(SQLException e) {
            //System.out.println("Class: DbTimetableLinker. Action: Connection Failed. Entry not updated.");
            //log.error("Class: DbTimetableLinker. Action: Connection Failed. Entry not updated.");
            //log.error("\nDetail Error: " + e);
            
            CreateLog("error", "Connection Failed. Entry Not updated.", e);
        }
    }
    
    // Delete Entry
        @Override
        public void DeleteEntryById(int id) throws SQLException {
            //log.info("Class: DbTimetableLinker. Action: Delete Entry by Id operation trigger.");
            CreateLog("info", "Delete Entry by Id operation trigger.", null);
            
            try{
                 //database connection 
                Connection con = Connection();
                
                //Delete entry from database 
                String deleteSQL = "DELETE FROM timetablelinker WHERE TimetableLinkerId = ?";
                PreparedStatement psDelete = con.prepareStatement(deleteSQL);
                psDelete.setInt(1, id);
                int rowsDeleted = psDelete.executeUpdate();
                
                if(rowsDeleted > 0){
                    //System.out.println("Class: DbTimetableLinker. Action: Entry Deleted.");
                    //log.info("Class: DbTimetableLinker. Action: Entry Deleted.");
                    // con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                   CreateLog("info", "Entry Deleted.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                }
            }
            catch (SQLException e){
                //System.out.println("Class: DbTimetableLinker. Action: Connection Failed. Entry not Deleted. ");
                //log.error("Class: DbTimetableLinker. Action: Connection Failed. Entry not Deleted. ");   
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. Entry not Deleted or Found.", e);
            }
        }
        
     //Return Entry by TimetableLinkerId
        @Override
        public DbTimetableLinker GetEntrybyId(int id) throws SQLException {
            //log.info("Class: DbTimetableLinker. Action: Return Entry by Id operation triggered. ");
            CreateLog("info", "Return Entry by Id operation triggered.", null);
            
            //Create new class instance
            DbTimetableLinker dataStore = new DbTimetableLinker();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use id to locate entry
                String selectSQL = "SELECT TimetableLinkerId, EventId, UserAndFolderId "
                        + "FROM timetablelinker"
                        + "WHERE TimetableLinkerId = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setInt(1, id);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setTimetableLinkerId(rset.getInt("TimetableLinkerId"));
                    dataStore.setEventId(rset.getInt("EventId")); 
                    dataStore.setUserAndUserAndFolderId(rset.getInt("UserAndFolderId")); 
                    
                    //System.out.println("Class: DbTimetableLinker. Action: Entry returned.");
                    //log.info("Class: DbTimetableLinker. Action: Entry returned.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                   CreateLog("info", "Entry returned.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                    
                }else {
                    //System.out.println("Class: DbTimetableLinker. Action: Entry not found.");
                    //log.error("Class: DbTimetableLinker. Action: Entry not found.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("error", "Validation Failed. Entry Not found.", null);  
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null); 
                }
            }
            catch (SQLException e) {
                //System.out.println("Class: DbTimetableLinker. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbTimetableLinker. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
              CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);  
            }
            
            return dataStore;
        }   
        
        //Return Entry by EventId
        public DbTimetableLinker GetEntrybyEventId(int eventId) throws SQLException {
            //log.info("Class: DbTimetableLinker. Action: Return Entry by EventId operation triggered. ");
            CreateLog("info", "Return Entry by EventId operation triggered.", null);
            
            //Create new class instance
            DbTimetableLinker dataStore = new DbTimetableLinker();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use name to locate entry
                String selectSQL = "SELECT TimetableLinkerId, EventId, UserAndFolderId "
                        + "FROM timetablelinker"
                        + "WHERE EventId = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setInt(1, eventId);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setTimetableLinkerId(rset.getInt("EventId"));
                    dataStore.setEventId(rset.getInt("EventId"));
                    dataStore.setUserAndUserAndFolderId(rset.getInt("UserAndFolderId")); 
            
                    //System.out.println("Class: DbTimetableLinker. Action: Entry returned.");
                    //log.info("Class: DbTimetableLinker. Action: Entry returned.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                   CreateLog("info", "Entry returned.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                }else {
                    //System.out.println("Class: DbTimetableLinker. Action: Entry not found.");
                    //log.error("Class: DbTimetableLinker. Action: Entry not found.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("error", "Validation Failed. Entry Not found.", null);  
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null); 
                }
            }
            catch (SQLException e) {
                //System.out.println("Class: DbTimetableLinker. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbTimetableLinker. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
               CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);   
            }
            
            return dataStore;
        }   
        
    //Return All Users
        @Override
        public ArrayList<DbTimetableLinker> GetAllEntries() throws SQLException {
            //log.info("Class: DbTimetableLinker. Action: Return all Entry operation triggered. ");
            CreateLog("info", "Return all Entry operation triggered.", null);
            
            //Create new class instance
            DbTimetableLinker dataStore = new DbTimetableLinker();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Return all entries
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM timetablelinker";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
                    //get info from db to a variable 
                    dataStore.setTimetableLinkerId(rset.getInt("TimetableLinkerId"));
                    dataStore.setEventId(rset.getInt("EventId"));
                    dataStore.setUserAndUserAndFolderId(rset.getInt("UserAndFolderId")); 
    
                    // Save entries 
                    timetableLinkerList.add(dataStore);
                }
                
                //System.out.println("Class: DbTimetableLinker. Action: EntryList returned.");
                //log.info("Class: DbTimetableLinker. Action: EntryList returned.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
                
                CreateLog("info", "EntryList returned.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }
            catch (SQLException e) {
                //System.out.println("Class: DbTimetableLinker. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbTimetableLinker. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);   
            }
            
            return timetableLinkerList;
        }

    //Print all Entries
        @Override
        public void ViewAllEntryPrint() throws SQLException {
            //log.info("Class: DbTimetableLinker. Action: Print all Entry usign Console operation triggered. ");
            CreateLog("info", "Print all Entry using Console operation triggered.", null);
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Variables
                int _timetableLinkerId = 0;
                int _eventId = 0;
                int _userAndFolderId = 0;
                
                
                // View all entries using the console
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM timetablelinker";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
          
                    //get info from db to a variable 
                    _timetableLinkerId = rset.getInt("TimetableLinkerId");
                    _eventId = rset.getInt("EventId");
                    _userAndFolderId = rset.getInt("UserAndFolderId");
    
                    //print info 
                    System.out.println("\n\nTimetableLinker Entry Id: " + _timetableLinkerId);
                    System.out.println("EventId : " + _eventId );
                    System.out.println("UserAndFolderId : " + _userAndFolderId);
                    System.out.println("+++++++++++++++++++++"); 
                }
                
                //log.info("Class: DbTimetableLinker. Action: EntryList printed using console.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
                
                CreateLog("info", "EntryList printed using console.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }
            catch (SQLException e) {
                //System.out.println("Class: DbTimetableLinker. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbTimetableLinker. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
               CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);    
            }
        }
}
