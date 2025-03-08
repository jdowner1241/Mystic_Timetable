/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mytictodo_limited.mystic_timetable.db;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.RowFilter.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Jamario_Downer
 */
public class DbLoginInfo extends DbConnectionManager {
 //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
 public DbLoginInfo(){
     log.info("Class: DbLoginInfo. Action: Default Constructor Triggered.");
     //Connection con = Connection();
 }
    
 //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     
 //private Connection con; 
 
 private int loginInfoId;
 private int userId;
 private int failedLoginCount;
 private LocalDateTime lastLogin;
 private ArrayList<DbLoginInfo> loginInfoList;
 
 private static final Logger log = LogManager.getLogger(DbLoginInfo.class);
 //Getter/Setter >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 //LoginInfoId
 public int getLoginInfoId(){
     return loginInfoId;
 }
 public void setLoginInfoId(int loginInfoId){
     this.loginInfoId = loginInfoId;
 }
 
 //UserId
 public int getUserId(){
     return userId;
 }
 public void setUserId(int userId){
     this.userId = userId;
 }
 
 //FailedLoginCount
 public int getFailedLoginCount(){
     return failedLoginCount;
 }
 public void setFailedLoginCount(int failedLoginCount){
     this.failedLoginCount = failedLoginCount;
 }
 
 
 // LastLogin
 public LocalDateTime getLastLogin(){
     return lastLogin;
 }
 public void setLastLogin(String _lastLogin){
     DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
     lastLogin = LocalDateTime.parse(_lastLogin, formatter);
 }
 
 //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
 //Insert Entry 
    public void UserInsertEntry(int userId, int failedLoginCount ) throws SQLException{
        log.info("Class: DbLoginInfo. Action: Insert Entry Operation Triggered.");
        
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            int _userId = userId;
            int _failedLoginCount = failedLoginCount;
            LocalDateTime _setLastLogin = LocalDateTime.now();
            String _lastLogin = _setLastLogin.toString();
            
            boolean isValid = true;
            
           if (isValid == true) 
           {
               //insert to database
                String insertSQL = "INSERT INTO logininfo "
                        + "(UserId, FailedLoginCount, Password, LastLogin) "
                        + "VALUE (?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(insertSQL);
                ps.setInt(1, _userId);
                ps.setInt(2, _failedLoginCount);
                ps.setString(4, _lastLogin);         
            
                int rowsInserted = ps.executeUpdate();
            
                if(rowsInserted > 0) {
                    System.out.println("Class: DbLoginInfo. Action: Entry Inserted.");
                    log.info("Class: DbLoginInfo. Action: Entry Inserted.");
                
                    //Close Connection
                    con.close();
                   log.info("Connection closed");
                }
           } else 
           {
             // Data not saved due to validation
             System.out.println("Class: DbLoginInfo. Action: Validation Failed. Entry Not Added.");
             log.error("Class: DbLoginInfo. Action: Validation Failed. Entry Not Added.");
             con.close(); //Close Connection
             log.info("Connection closed");
           }
        }
        catch(SQLException e) {
            System.out.println("\nClass: DbLoginInfo. Action: Connection Failed. Entry Not Added");
            log.error("Class: DbLoginInfo. Action: Connection Failed. Entry Not Added");
            log.error("\nDetail Error: " + e);
        }
    }
    
    //Update Entry
    public void UserUpdateEntrybyId(int id, int userId, int failedLoginCount) throws SQLException{
        log.info("Class: DbLoginInfo. Action: Update Entry by Id operation triggered. ");
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            int _userId = userId;
            int _failedLoginCount = failedLoginCount;
  
            boolean isValid = true;
            
            if (isValid == true)
            {
                // update the entry
                String updateSQL = "UPDATE logininfo "
                        + "SET UserId = ? FailedLoginCount = ? Password = ?"
                        + "WHERE LoginInfoId = ?";
                PreparedStatement psUpdate  = con.prepareStatement(updateSQL);
                psUpdate.setInt(1, _userId);
                psUpdate.setInt(2, _failedLoginCount);
                psUpdate.setInt(4, id);
                int rowsUpdated = psUpdate.executeUpdate();
                
                if(rowsUpdated > 0) {
                    System.out.println("Class: DbLoginInfo. Action: Entry Updated.");
                    log.info("Class: DbLoginInfo. Action: Entry Updated.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }else
            {
                 // Data not saved due to validation
                System.out.println("Class: DbLoginInfo. Action: Validation Failed. Entry Not Updated.");
                log.error("Class: DbLoginInfo. Action: Validation Failed. Entry Not Updated.");
                con.close(); //Close Connection
                log.info("Connection closed");
            }  
        }
        catch(SQLException e) {
            System.out.println("Class: DbLoginInfo. Action: Connection Failed. Entry not updated.");
            log.error("Class: DbLoginInfo. Action: Connection Failed. Entry not updated.");
            log.error("\nDetail Error: " + e);
        }
    }
    
    // Delete Entry
        public void UserDeleteEntryById(int id) throws SQLException {
            log.info("Class: DbLoginInfo. Action: Delete Entry by Id operation trigger.");
            try{
                 //database connection 
                Connection con = Connection();
                
                //Delete entry from database 
                String deleteSQL = "DELETE FROM logininfo WHERE LoginInfoId = ?";
                PreparedStatement psDelete = con.prepareStatement(deleteSQL);
                psDelete.setInt(1, id);
                int rowsDeleted = psDelete.executeUpdate();
                
                if(rowsDeleted > 0){
                    System.out.println("Class: DbLoginInfo. Action: Entry Deleted.");
                    log.info("Class: DbLoginInfo. Action: Entry Deleted.");
                     con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }
            catch (SQLException e){
                System.out.println("Class: DbLoginInfo. Action: Connection Failed. Entry not Deleted. ");
                log.error("Class: DbLoginInfo. Action: Connection Failed. Entry not Deleted. ");   
                log.error("\nDetail Error: " + e);
            }
        }
        
     //Return Entry by Id
        public DbLoginInfo UserGetEntrybyId(int id) throws SQLException {
            log.info("Class: DbLoginInfo. Action: Return Entry by Id operation triggered. ");
            
            //Create new class instance
            DbLoginInfo dataStore = new DbLoginInfo();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use id to locate entry
                String selectSQL = "SELECT LoginInfoId, UserId, FailedLoginCount, LastLogin "
                        + "FROM logininfo"
                        + "WHERE LoginInfoId = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setInt(1, id);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setLoginInfoId(rset.getInt("LoginInfoId"));
                    dataStore.setLoginInfoId(rset.getInt("UserId"));
                    
                    dataStore.setFailedLoginCount(rset.getInt("FailedLoginCount")); 
                    dataStore.setLastLogin(rset.getString("LastLogin")); 
                    
                    System.out.println("Class: DbLoginInfo. Action: Entry returned.");
                    log.info("Class: DbLoginInfo. Action: Entry returned.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }else {
                    System.out.println("Class: DbLoginInfo. Action: Entry not found.");
                    log.error("Class: DbLoginInfo. Action: Entry not found.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }
            catch (SQLException e) {
                System.out.println("Class: DbLoginInfo. Action: Connection Failed. No Entry loaded or Found.");
                log.error("Class: DbLoginInfo. Action: Connection Failed. No Entry loaded or Found.");
                log.error("\nDetail Error: " + e);
            }
            
            return dataStore;
        }   
        
        //Return Entry by UserId
        public DbLoginInfo UserGetEntrybyUserId(String userId) throws SQLException {
            log.info("Class: DbLoginInfo. Action: Return Entry by UserId operation triggered. ");
            
            //Create new class instance
            DbLoginInfo dataStore = new DbLoginInfo();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use name to locate entry
                String selectSQL = "SELECT LoginInfoId, UserId, FailedLoginCount, LastLogin "
                        + "FROM logininfo"
                        + "WHERE UserId = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setString(1, userId);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setLoginInfoId(rset.getInt("LoginInfoId"));
                    dataStore.setLoginInfoId(rset.getInt("UserId"));
                    dataStore.setFailedLoginCount(rset.getInt("FailedLoginCount")); 
                    dataStore.setLastLogin(rset.getString("LastLogin"));        
            
                    System.out.println("Class: DbLoginInfo. Action: Entry returned.");
                    log.info("Class: DbLoginInfo. Action: Entry returned.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }else {
                    System.out.println("Class: DbLoginInfo. Action: Entry not found.");
                    log.error("Class: DbLoginInfo. Action: Entry not found.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }
            catch (SQLException e) {
                System.out.println("Class: DbLoginInfo. Action: Connection Failed. No Entry loaded or Found.");
                log.error("Class: DbLoginInfo. Action: Connection Failed. No Entry loaded or Found.");
                log.error("\nDetail Error: " + e);
            }
            
            return dataStore;
        }   
        
    //Return All Users
        public ArrayList<DbLoginInfo> UserGetAllEntries() throws SQLException {
            log.info("Class: DbLoginInfo. Action: Return all Entry operation triggered. ");
            
            //Create new class instance
            DbLoginInfo dataStore = new DbLoginInfo();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Return all entries
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM logininfo";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
                    //get info from db to a variable 
                    dataStore.setLoginInfoId(rset.getInt("LoginInfoId"));
                    dataStore.setLoginInfoId(rset.getInt("UserId"));
                    dataStore.setFailedLoginCount(rset.getInt("FailedLoginCount")); 
                    dataStore.setLastLogin(rset.getString("LastLogin"));
    
                    // Save entries 
                    loginInfoList.add(dataStore);
                }
                
                System.out.println("Class: DbLoginInfo. Action: EntryList returned.");
                log.info("Class: DbLoginInfo. Action: EntryList returned.");
                con.close(); //Close Connection
                log.info("Connection closed");
            }
            catch (SQLException e) {
                System.out.println("Class: DbLoginInfo. Action: Connection Failed. No Entry loaded or Found.");
                log.error("Class: DbLoginInfo. Action: Connection Failed. No Entry loaded or Found.");
                log.error("\nDetail Error: " + e);
            }
            
            return loginInfoList;
        }

    //Print all Entries
        public void UserViewAllEntryPrint() throws SQLException {
            log.info("Class: DbLoginInfo. Action: Print all Entry usign Console operation triggered. ");
            try {
                 //database connection 
                Connection con = Connection();
                
                // Variables
                int _loginInfoId = 0;
                int _userId = 0;
                int _failedLoginCount = 0;
                String _lastLogin = null;
                
                
                // View all entries using the console
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM logininfo";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
          
                    //get info from db to a variable 
                    _loginInfoId = rset.getInt("LoginInfoId");
                    _userId = rset.getInt("UserId");
                    _failedLoginCount = rset.getInt("FailedLoginCount");
                    _lastLogin = rset.getString("LastLogin");
    
                    //print info 
                    System.out.println("\n\nLogInfo Entry Id: " + _loginInfoId);
                    System.out.println("UserId : " + _userId );
                    System.out.println("FailedLoginCount : " + _failedLoginCount);
                    System.out.println("LastLogin : " + _lastLogin); 
                    System.out.println("+++++++++++++++++++++"); 
                }
                
                log.info("Class: DbLoginInfo. Action: EntryList printed using console.");
                con.close(); //Close Connection
                log.info("Connection closed");
            }
            catch (SQLException e) {
                System.out.println("Class: DbLoginInfo. Action: Connection Failed. No Entry loaded or Found.");
                log.error("Class: DbLoginInfo. Action: Connection Failed. No Entry loaded or Found.");
                log.error("\nDetail Error: " + e);
            }
        }
}
