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
import mystictodo_limited.mystic_timetable.dbInterface.DbService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Jamario_Downer
 */
public class DbLogEntry extends DbConnectionManager implements DbService<DbLogEntry> {
 //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
 public DbLogEntry(){
     super(DbLogEntry.class);
     CreateLog("info", "Default Constructor Triggered.", null);  
 }
    
 //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     
 //private Connection con; 
 
 private int logId;
 private int userId;
 private int logTypeId;
 private String logLevel;
 private String message;
 private LocalDateTime timeStamp;
 private ArrayList<DbLogEntry> logEntryList;
 
 //Getter/Setter >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 //LogId
 public int getLogId(){
     return logId;
 }
 public void setLogId(int logId){
     this.logId = logId;
 }
 
 //UserId
 public int getUserId(){
     return userId;
 }
 public void setUserId(int userId){
     this.userId = userId;
 }
 
 //LoginTypeId
 public int getLogTypeId(){
     return logTypeId;
 }
 public void setLogTypeId(int logTypeId){
     this.logTypeId = logTypeId;
 }
 
 //LogLevel
 public String getLogLevel(){
     return logLevel;
 }
 public void setLogLevel(String logLevel){
     this.logLevel = logLevel;
 }
 
 //Message
 public String getMessage(){
     return message;
 }
 public void setMessage(String message){
     this.message = message;
 }
 
 
 // TimeStamp
 public LocalDateTime getTimeStamp(){
     return timeStamp;
 }
 public void setTimeStamp(String _timeStamp){
     DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
     timeStamp = LocalDateTime.parse(_timeStamp, formatter);
 }
 
 //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
 //Insert Entry 
    public void InsertEntry(int userId, int logTypeId, String logLevel, String message) throws SQLException{
        CreateLog("info", "Insert Entry Operation Triggered.", null);  
        
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            int _userId = userId;
            int _logTypeId = logTypeId;
            String _logLevel = logLevel;
            String _message = message;
            LocalDateTime _setTimeStamp = LocalDateTime.now();
            String _timeStamp = _setTimeStamp.toString();
            
            boolean isValid = true;
            
           if (isValid == true) 
           {
               //insert to database
                String insertSQL = "INSERT INTO logentry "
                        + "(UserId, LogTypeId, LogLevel, Message, TimeStamp) "
                        + "VALUE (?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(insertSQL);
                ps.setInt(1, _userId);
                ps.setInt(2, _logTypeId);
                ps.setString(3, _logLevel);
                ps.setString(4, _message);
                ps.setString(5, _timeStamp);         
            
                int rowsInserted = ps.executeUpdate();
            
                if(rowsInserted > 0) {
                    // Perform Action if not empty
                   CreateLog("info", "Entry Inserted.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                }
           } else 
           {
               // Data not saved due to validation
                CreateLog("error", "Validation Failed. Entry Not Added.", null);  
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);  
           }
        }
        catch(SQLException e) {
            CreateLog("error", "Connection Failed. Entry Not Added.", e);
        }
    }
    
    //Update Entry
    public void UpdateEntrybyId(int id, int userId, int logTypeId, String logLevel, String message) throws SQLException{
        CreateLog("info", "Update Entry by Id operation triggered.", null);  
        
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            int _userId = userId;
            int _logTypeId = logTypeId;
            String _logLevel = logLevel;
            String _message = message;
  
            boolean isValid = true;
            
            if (isValid == true)
            {
                // update the entry
                String updateSQL = "UPDATE logentry "
                        + "SET UserId = ? LogTypeId = ? LogLevel = ? Message = ?"
                        + "WHERE LogId = ?";
                PreparedStatement psUpdate  = con.prepareStatement(updateSQL);
                psUpdate.setInt(1, _userId);
                psUpdate.setInt(2, _logTypeId);
                psUpdate.setString(3, _logLevel);
                psUpdate.setString(4, _message);
                psUpdate.setInt(5, id);
                int rowsUpdated = psUpdate.executeUpdate();
                
                if(rowsUpdated > 0) {
                    // Perform Action if not empty
                    CreateLog("info", "Entry Updated.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }
            }else
            {
                // Data not saved due to validation
                CreateLog("error", "Validation Failed. Entry Not Updated.", null);  
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);   
            }  
        }
        catch(SQLException e) {
            CreateLog("error", "Connection Failed. Entry not updated.", e);
        }
    }
    
    // Delete Entry
        @Override
        public void DeleteEntryById(int id) throws SQLException {
            CreateLog("info", "Delete Entry by Id operation trigger.", null);  
            
            try{
                 //database connection 
                Connection con = Connection();
                
                //Delete entry from database 
                String deleteSQL = "DELETE FROM logentry WHERE LogId = ?";
                PreparedStatement psDelete = con.prepareStatement(deleteSQL);
                psDelete.setInt(1, id);
                int rowsDeleted = psDelete.executeUpdate();
                
                if(rowsDeleted > 0){
                    // Perform Action if not empty
                    CreateLog("info", "Entry Deleted.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }
            }
            catch (SQLException e){
                CreateLog("error", "Connection Failed. Entry not Deleted or Found.", e);
            }
        }
        
     //Return Entry by Id
        @Override
        public DbLogEntry GetEntrybyId(int id) throws SQLException {
            CreateLog("info", "Return Entry by Id operation triggered.", null);  
            
            //Create new class instance
            DbLogEntry dataStore = new DbLogEntry();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use id to locate entry
                String selectSQL = "SELECT LogId, UserId, LogTypeId, LogLevel, Message, TimeStamp "
                        + "FROM logentry"
                        + "WHERE LogId = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setInt(1, id);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setLogId (rset.getInt("LogId"));
                    dataStore.setUserId(rset.getInt("UserId"));
                    dataStore.setLogTypeId (rset.getInt("LogTypeId")); 
                    dataStore.setLogLevel(rset.getString("LogLevel"));
                    dataStore.setMessage(rset.getString("Message"));
                    dataStore.setTimeStamp(rset.getString("TimeStamp")); 
                    
                    // Perform Action if not empty
                    CreateLog("info", "Entry returned.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }else {
                    CreateLog("error", "Validation Failed. Entry not found.", null);  
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);   
                }
            }
            catch (SQLException e) {
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return dataStore;
        }   
        
        //Return Entry by UserId
        public DbLogEntry GetEntrybyUserId(String userId) throws SQLException {
            CreateLog("info", "Return Entry by UserId operation triggered.", null);  
            
            //Create new class instance
            DbLogEntry dataStore = new DbLogEntry();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use name to locate entry
                String selectSQL = "SELECT LogId, UserId, LogTypeId, LogLevel, Message, TimeStamp "
                        + "FROM logentry"
                        + "WHERE UserId = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setString(1, userId);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setLogId (rset.getInt("LogId"));
                    dataStore.setUserId(rset.getInt("UserId"));
                    dataStore.setLogTypeId(rset.getInt("LogTypeId")); 
                    dataStore.setLogLevel(rset.getString("LogLevel"));
                    dataStore.setMessage(rset.getString("Message"));
                    dataStore.setTimeStamp(rset.getString("TimeStamp"));        
            
                    // Perform Action if not empty
                    CreateLog("info", "Entry returned.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }else {
                    
                    CreateLog("error", "Validation Failed. Entry not found.", null);  
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);    
                }
            }
            catch (SQLException e) {
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return dataStore;
        }   
        
    //Return All Users
        @Override
        public ArrayList<DbLogEntry> GetAllEntries() throws SQLException {
            CreateLog("info", "Return all Entry operation triggered.", null); 
            
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Return all entries
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM logentry";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
                    //Create new class instance
                    DbLogEntry dataStore = new DbLogEntry();
                    
                    //get info from db to a variable 
                    dataStore.setLogId (rset.getInt("LogId"));
                    dataStore.setUserId(rset.getInt("UserId"));
                    dataStore.setLogTypeId(rset.getInt("LogTypeId")); 
                    dataStore.setLogLevel(rset.getString("LogLevel"));
                    dataStore.setMessage(rset.getString("Message"));
                    dataStore.setTimeStamp(rset.getString("TimeStamp"));
    
                    // Save entry elements to datastore
                    logEntryList.add(dataStore);
                }
                
                CreateLog("info", "EntryList returned.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }
            catch (SQLException e) {
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return logEntryList;
        }

    //Print all Entries
        @Override
        public void ViewAllEntryPrint() throws SQLException {
            CreateLog("info", "Print all Entry using Console operation triggered.", null); 
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Variables
                int _logId = 0;
                int _userId = 0;
                int _logTypeId = 0;
                String _logLevel = null;
                String _message = null;
                String _timeStamp = null;
                
                
                // View all entries using the console
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM logentry";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
          
                    //get info from db to a variable 
                    _logId = rset.getInt("LogId");
                    _userId = rset.getInt("UserId");
                    _logTypeId = rset.getInt("LogTypeId");
                    _logLevel  = rset.getString("LogLevel");
                    _message = rset.getString("Message");
                    _timeStamp = rset.getString("TimeStamp");
    
                    //print info 
                    System.out.println("\n\nLogEntry Id: " + _logId);
                    System.out.println("UserId : " + _userId );
                    System.out.println("LogTypeId : " + _logTypeId);
                    System.out.println("LogLevel : " + _logLevel);
                    System.out.println("Message : " + _message);
                    System.out.println("TimeStamp : " + _timeStamp); 
                    System.out.println("+++++++++++++++++++++"); 
                }
                
                CreateLog("info", "EntryList printed using console.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }
            catch (SQLException e) {
               CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
        }
}
