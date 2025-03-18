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
     //log.info("Class: DbLogEntry. Action: Default Constructor Triggered.");
     //Connection con = Connection();
     
     super(DbLogEntry.class);
     CreateLog("info", "Default Constructor Triggered.", null);  
 }
    
 //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     
 //private Connection con; 
 
 private int logId;
 private int userId;
 private int loginTypeId;
 private String logLevel;
 private String message;
 private LocalDateTime timeStamp;
 private ArrayList<DbLogEntry> logEntryList;
 
 //private static final Logger log = LogManager.getLogger(DbLogEntry.class);
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
 public int getLoginTypeId(){
     return loginTypeId;
 }
 public void setLoginTypeId(int loginTypeId){
     this.loginTypeId = loginTypeId;
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
    public void InsertEntry(int userId, int loginTypeId, String logLevel, String message) throws SQLException{
        //log.info("Class: DbLogEntry. Action: Insert Entry Operation Triggered.");
        CreateLog("info", "Insert Entry Operation Triggered.", null);  
        
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            int _userId = userId;
            int _loginTypeId = loginTypeId;
            String _logLevel = logLevel;
            String _message = message;
            LocalDateTime _setTimeStamp = LocalDateTime.now();
            String _timeStamp = _setTimeStamp.toString();
            
            boolean isValid = true;
            
           if (isValid == true) 
           {
               //insert to database
                String insertSQL = "INSERT INTO logentry "
                        + "(UserId, LoginTypeId, LogLevel, Message, TimeStamp) "
                        + "VALUE (?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(insertSQL);
                ps.setInt(1, _userId);
                ps.setInt(2, _loginTypeId);
                ps.setString(3, _logLevel);
                ps.setString(4, _message);
                ps.setString(5, _timeStamp);         
            
                int rowsInserted = ps.executeUpdate();
            
                if(rowsInserted > 0) {
                    //System.out.println("Class: DbLogEntry. Action: Entry Inserted.");
                    //log.info("Class: DbLogEntry. Action: Entry Inserted.");
                   // con.close();//Close Connection
                   //log.info("Connection closed");
                   
                   CreateLog("info", "Entry Inserted.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                }
           } else 
           {
             // Data not saved due to validation
             //System.out.println("Class: DbLogEntry. Action: Validation Failed. Entry Not Added.");
             //log.error("Class: DbLogEntry. Action: Validation Failed. Entry Not Added.");
             //con.close(); //Close Connection
             //log.info("Connection closed");
               
               // Data not saved due to validation
                CreateLog("error", "Validation Failed. Entry Not Added.", null);  
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);  
           }
        }
        catch(SQLException e) {
            //System.out.println("Class: DbLogEntry. Action: Connection Failed. Entry Not Added");
            //log.error("Class: DbLogEntry. Action: Connection Failed. Entry Not Added");
            //log.error("\nDetail Error: " + e);
            
            CreateLog("error", "Connection Failed. Entry Not Added.", e);
        }
    }
    
    //Update Entry
    public void UpdateEntrybyId(int id, int userId, int loginTypeId, String logLevel, String message) throws SQLException{
        //log.info("Class: DbLogEntry. Action: Update Entry by Id operation triggered. ");
        CreateLog("info", "Update Entry by Id operation triggered.", null);  
        
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            int _userId = userId;
            int _loginTypeId = loginTypeId;
            String _logLevel = logLevel;
            String _message = message;
  
            boolean isValid = true;
            
            if (isValid == true)
            {
                // update the entry
                String updateSQL = "UPDATE logentry "
                        + "SET UserId = ? LoginTypeId = ? LogLevel = ? Message = ?"
                        + "WHERE LogId = ?";
                PreparedStatement psUpdate  = con.prepareStatement(updateSQL);
                psUpdate.setInt(1, _userId);
                psUpdate.setInt(2, _loginTypeId);
                psUpdate.setString(3, _logLevel);
                psUpdate.setString(4, _message);
                psUpdate.setInt(5, id);
                int rowsUpdated = psUpdate.executeUpdate();
                
                if(rowsUpdated > 0) {
                    //System.out.println("Class: DbLogEntry. Action: Entry Updated.");
                    //log.info("Class: DbLogEntry. Action: Entry Updated.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("info", "Entry Updated.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }
            }else
            {
                 // Data not saved due to validation
                //System.out.println("Class: DbLogEntry. Action: Validation Failed. Entry Not Updated.");
                //log.error("Class: DbLogEntry. Action: Validation Failed. Entry Not Updated.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
                
                // Data not saved due to validation
                CreateLog("error", "Validation Failed. Entry Not Updated.", null);  
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);   
            }  
        }
        catch(SQLException e) {
            //System.out.println("Class: DbLogEntry. Action: Connection Failed. Entry not updated.");
            //log.error("Class: DbLogEntry. Action: Connection Failed. Entry not updated.");
            //log.error("\nDetail Error: " + e);
            
            CreateLog("error", "Connection Failed. Entry not updated.", e);
        }
    }
    
    // Delete Entry
        @Override
        public void DeleteEntryById(int id) throws SQLException {
            //log.info("Class: DbLogEntry. Action: Delete Entry by Id operation trigger.");
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
                    //System.out.println("Class: DbLogEntry. Action: Entry Deleted.");
                    //log.info("Class: DbLogEntry. Action: Entry Deleted.");
                    // con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("info", "Entry Deleted.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }
            }
            catch (SQLException e){
                //System.out.println("Class: DbLogEntry. Action: Connection Failed. Entry not Deleted. ");
                //log.error("Class: DbLogEntry. Action: Connection Failed. Entry not Deleted. ");   
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. Entry not Deleted or Found.", e);
            }
        }
        
     //Return Entry by Id
        @Override
        public DbLogEntry GetEntrybyId(int id) throws SQLException {
            //log.info("Class: DbLogEntry. Action: Return Entry by Id operation triggered. ");
            CreateLog("info", "Return Entry by Id operation triggered.", null);  
            
            //Create new class instance
            DbLogEntry dataStore = new DbLogEntry();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use id to locate entry
                String selectSQL = "SELECT LogId, UserId, LoginTypeId, LogLevel, Message, TimeStamp "
                        + "FROM logentry"
                        + "WHERE LogId = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setInt(1, id);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setLogId (rset.getInt("LogId"));
                    dataStore.setUserId(rset.getInt("UserId"));
                    dataStore.setLoginTypeId (rset.getInt("LoginTypeId")); 
                    dataStore.setLogLevel(rset.getString("LogLevel"));
                    dataStore.setMessage(rset.getString("Message"));
                    dataStore.setTimeStamp(rset.getString("TimeStamp")); 
                    
                    //System.out.println("Class: DbLogEntry. Action: Entry returned.");
                    //log.info("Class: DbLogEntry. Action: Entry returned.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("info", "Entry returned.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }else {
                    //System.out.println("Class: DbLogEntry. Action: Entry not found.");
                   // log.error("Class: DbLogEntry. Action: Entry not found.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("error", "Validation Failed. Entry not found.", null);  
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);   
                }
            }
            catch (SQLException e) {
                //System.out.println("Class: DbLogEntry. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbLogEntry. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return dataStore;
        }   
        
        //Return Entry by UserId
        public DbLogEntry GetEntrybyUserId(String userId) throws SQLException {
            //log.info("Class: DbLogEntry. Action: Return Entry by UserId operation triggered. ");
            CreateLog("info", "Return Entry by UserId operation triggered.", null);  
            
            //Create new class instance
            DbLogEntry dataStore = new DbLogEntry();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use name to locate entry
                String selectSQL = "SELECT LogId, UserId, LoginTypeId, LogLevel, Message, TimeStamp "
                        + "FROM logentry"
                        + "WHERE UserId = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setString(1, userId);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setLogId (rset.getInt("LogId"));
                    dataStore.setUserId(rset.getInt("UserId"));
                    dataStore.setLoginTypeId(rset.getInt("LoginTypeId")); 
                    dataStore.setLogLevel(rset.getString("LogLevel"));
                    dataStore.setMessage(rset.getString("Message"));
                    dataStore.setTimeStamp(rset.getString("TimeStamp"));        
            
                    //System.out.println("Class: DbLogEntry. Action: Entry returned.");
                    //log.info("Class: DbLogEntry. Action: Entry returned.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("info", "Entry returned.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }else {
                    //System.out.println("Class: DbLogEntry. Action: Entry not found.");
                    //log.error("Class: DbLogEntry. Action: Entry not found.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("error", "Validation Failed. Entry not found.", null);  
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);    
                }
            }
            catch (SQLException e) {
                //System.out.println("Class: DbLogEntry. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbLogEntry. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return dataStore;
        }   
        
    //Return All Users
        @Override
        public ArrayList<DbLogEntry> GetAllEntries() throws SQLException {
            //log.info("Class: DbLogEntry. Action: Return all Entry operation triggered. ");
            CreateLog("info", "Return all Entry operation triggered.", null); 
            
            //Create new class instance
            DbLogEntry dataStore = new DbLogEntry();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Return all entries
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM logentry";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
                    //get info from db to a variable 
                    dataStore.setLogId (rset.getInt("LogId"));
                    dataStore.setUserId(rset.getInt("UserId"));
                    dataStore.setLoginTypeId(rset.getInt("LoginTypeId")); 
                    dataStore.setLogLevel(rset.getString("LogLevel"));
                    dataStore.setMessage(rset.getString("Message"));
                    dataStore.setTimeStamp(rset.getString("TimeStamp"));
    
                    // Save entries 
                    logEntryList.add(dataStore);
                }
                
                //System.out.println("Class: DbLogEntry. Action: EntryList returned.");
                //log.info("Class: DbLogEntry. Action: EntryList returned.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
                
                CreateLog("info", "EntryList returned.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }
            catch (SQLException e) {
                //System.out.println("Class: DbLogEntry. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbLogEntry. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return logEntryList;
        }

    //Print all Entries
        @Override
        public void ViewAllEntryPrint() throws SQLException {
            //log.info("Class: DbLogEntry. Action: Print all Entry usign Console operation triggered. ");
            CreateLog("info", "Print all Entry using Console operation triggered.", null); 
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Variables
                int _logId = 0;
                int _userId = 0;
                int _loginTypeId = 0;
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
                    _loginTypeId = rset.getInt("LoginTypeId");
                    _logLevel  = rset.getString("LogLevel");
                    _message = rset.getString("Message");
                    _timeStamp = rset.getString("TimeStamp");
    
                    //print info 
                    System.out.println("\n\nLogEntry Id: " + _logId);
                    System.out.println("UserId : " + _userId );
                    System.out.println("LoginTypeId : " + _loginTypeId);
                    System.out.println("LogLevel : " + _logLevel);
                    System.out.println("Message : " + _message);
                    System.out.println("TimeStamp : " + _timeStamp); 
                    System.out.println("+++++++++++++++++++++"); 
                }
                
                //log.info("Class: DbLogEntry. Action: EntryList printed using console.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
                
                CreateLog("info", "EntryList printed using console.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }
            catch (SQLException e) {
                //System.out.println("Class: DbLogEntry. Action: Connection Failed. No Entry loaded or Found.");
               // log.error("Class: DbLogEntry. Action: Connection Failed. No Entry loaded or Found.");
               // log.error("\nDetail Error: " + e);
               
               CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
        }
}
