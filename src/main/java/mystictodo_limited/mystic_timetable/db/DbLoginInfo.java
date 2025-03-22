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
public class DbLoginInfo extends DbConnectionManager implements DbService<DbLoginInfo> {
 //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
 public DbLoginInfo(){
     //log.info("Class: DbLoginInfo. Action: Default Constructor Triggered.");
     //Connection con = Connection();
     
     super(DbLoginInfo.class);
     CreateLog("info", "Default Constructor Triggered.", null);  
 }
    
 //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     
 //private Connection con; 
 
 private int loginInfoId;
 private int userId;
 private boolean loginResult;
 private int successLoginCount;
 private int failedLoginCount;
 private LocalDateTime lastLogin;
 private ArrayList<DbLoginInfo> loginInfoList;
 
 //private static final Logger log = LogManager.getLogger(DbLoginInfo.class);
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
 
 //Login Results
    public boolean getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(boolean loginResult) {
        this.loginResult = loginResult;
    }
 
 //SuccessLoginCount
     public int getSuccessLoginCount() {
        return successLoginCount;
    }

    public void setSuccessLoginCount(int successLoginCount) {
        this.successLoginCount = successLoginCount;
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
    public boolean InsertEntry(int userId, boolean loginResult, int successLoginCount, int failedLoginCount ) throws SQLException{
        //log.info("Class: DbLoginInfo. Action: Insert Entry Operation Triggered.");
        CreateLog("info", "Insert Entry Operation Triggered.", null);  
        boolean isValid = true;
                    
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            int _userId = userId;
            boolean _loginResult = loginResult;
            int _successLoginCount = successLoginCount;
            int _failedLoginCount = failedLoginCount;    
            LocalDateTime _setLastLogin = LocalDateTime.now();
            String _lastLogin = _setLastLogin.toString();
            

            
           if (isValid == true) 
           {
               //insert to database
                String insertSQL = "INSERT INTO logininfo "
                        + "(UserId, LoginResult, SuccessLoginCount, FailedLoginCount, LastLogin) "
                        + "VALUE (?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(insertSQL);
                ps.setInt(1, _userId);
                ps.setBoolean(2, _loginResult);
                ps.setInt(3, _successLoginCount);
                ps.setInt(4, _failedLoginCount);
                ps.setString(5, _lastLogin);         
            
                int rowsInserted = ps.executeUpdate();
            
                if(rowsInserted > 0) {
                    //System.out.println("Class: DbLoginInfo. Action: Entry Inserted.");
                    //log.info("Class: DbLoginInfo. Action: Entry Inserted.");
                    //con.close(); //Close Connection
                   //log.info("Connection closed");
                   
                   CreateLog("info", "Entry Inserted.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                }
           } else 
           {
             //System.out.println("Class: DbLoginInfo. Action: Validation Failed. Entry Not Added.");
             //log.error("Class: DbLoginInfo. Action: Validation Failed. Entry Not Added.");
             //con.close(); //Close Connection
             //log.info("Connection closed");
             
             // Data not saved due to validation
             CreateLog("error", "Validation Failed. Entry Not Added.", null);  
             con.close(); //Close Connection
             CreateLog("info", "Connection closed.", null);   
           }
        }
        catch(SQLException e) {
            //System.out.println("\nClass: DbLoginInfo. Action: Connection Failed. Entry Not Added");
            //log.error("Class: DbLoginInfo. Action: Connection Failed. Entry Not Added");
            //log.error("\nDetail Error: " + e);
            
            CreateLog("error", "Connection Failed. Entry Not Added.", e);
        }
        return isValid;
    }
    
    //Update Entry
    public void UpdateEntrybyId(int id, int userId, boolean loginResult, int successLoginCount, int failedLoginCount) throws SQLException{
        //log.info("Class: DbLoginInfo. Action: Update Entry by Id operation triggered. ");
        CreateLog("info", "Update Entry by Id operation triggered.", null);  
        
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            int _userId = userId;
            boolean _loginResult = loginResult;
            int _successLoginCount = successLoginCount;
            int _failedLoginCount = failedLoginCount;    
  
            boolean isValid = true;
            
            if (isValid == true)
            {
                // update the entry
                String updateSQL = "UPDATE logininfo "
                        + "SET UserId = ? LoginResult = ? SuccessLoginCount = ? FailedLoginCount = ? Password = ?"
                        + "WHERE LoginInfoId = ?";
                PreparedStatement psUpdate  = con.prepareStatement(updateSQL);
                psUpdate.setInt(1, _userId);
                psUpdate.setBoolean(2, _loginResult);
                psUpdate.setInt(3, _successLoginCount);
                psUpdate.setInt(4, _failedLoginCount);
                psUpdate.setInt(5, id);
                int rowsUpdated = psUpdate.executeUpdate();
                
                if(rowsUpdated > 0) {
                    //System.out.println("Class: DbLoginInfo. Action: Entry Updated.");
                    //log.info("Class: DbLoginInfo. Action: Entry Updated.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                   CreateLog("info", "Entry Updated.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                }
            }else
            {
                //System.out.println("Class: DbLoginInfo. Action: Validation Failed. Entry Not Updated.");
                //log.error("Class: DbLoginInfo. Action: Validation Failed. Entry Not Updated.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
                
                // Data not saved due to validation
                CreateLog("error", "Validation Failed. Entry Not Updated.", null);  
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);   
            }  
        }
        catch(SQLException e) {
            //System.out.println("Class: DbLoginInfo. Action: Connection Failed. Entry not updated.");
            //log.error("Class: DbLoginInfo. Action: Connection Failed. Entry not updated.");
            //log.error("\nDetail Error: " + e);
            
            CreateLog("error", "Connection Failed. Entry not updated.", e);
        }
    }
    
    // Delete Entry
        @Override
        public void DeleteEntryById(int id) throws SQLException {
            //log.info("Class: DbLoginInfo. Action: Delete Entry by Id operation trigger.");
            CreateLog("info", "Delete Entry by Id operation trigger.", null);  
            
            try{
                 //database connection 
                Connection con = Connection();
                
                //Delete entry from database 
                String deleteSQL = "DELETE FROM logininfo WHERE LoginInfoId = ?";
                PreparedStatement psDelete = con.prepareStatement(deleteSQL);
                psDelete.setInt(1, id);
                int rowsDeleted = psDelete.executeUpdate();
                
                if(rowsDeleted > 0){
                    //System.out.println("Class: DbLoginInfo. Action: Entry Deleted.");
                    //log.info("Class: DbLoginInfo. Action: Entry Deleted.");
                    // con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                   CreateLog("info", "Entry Deleted.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                }
            }
            catch (SQLException e){
                //System.out.println("Class: DbLoginInfo. Action: Connection Failed. Entry not Deleted. ");
                //log.error("Class: DbLoginInfo. Action: Connection Failed. Entry not Deleted. ");   
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. Entry not Deleted or Found.", e);
            }
        }
        
     //Return Entry by Id
        @Override
        public DbLoginInfo GetEntrybyId(int id) throws SQLException {
            //log.info("Class: DbLoginInfo. Action: Return Entry by Id operation triggered. ");
            CreateLog("info", "Return Entry by Id operation triggered.", null);  
            
            //Create new class instance
            DbLoginInfo dataStore = new DbLoginInfo();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use id to locate entry
                String selectSQL = "SELECT LoginInfoId, UserId, LoginResult, SuccessLoginCount,  FailedLoginCount, LastLogin "
                        + "FROM logininfo"
                        + "WHERE LoginInfoId = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setInt(1, id);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setLoginInfoId(rset.getInt("LoginInfoId"));
                    dataStore.setUserId(rset.getInt("UserId"));
                    dataStore.setLoginResult(rset.getBoolean("LoginResult"));
                    dataStore.setSuccessLoginCount(rset.getInt("SuccessLoginCount"));
                    dataStore.setFailedLoginCount(rset.getInt("FailedLoginCount")); 
                    dataStore.setLastLogin(rset.getString("LastLogin")); 
                    
                    //System.out.println("Class: DbLoginInfo. Action: Entry returned.");
                    //log.info("Class: DbLoginInfo. Action: Entry returned.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                   CreateLog("info", "Entry returned.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                   
                }else {
                    //System.out.println("Class: DbLoginInfo. Action: Entry not found.");
                    //log.error("Class: DbLoginInfo. Action: Entry not found.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("error", "Validation Failed. Entry not found.", null);  
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);   
                }
            }
            catch (SQLException e) {
                //System.out.println("Class: DbLoginInfo. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbLoginInfo. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return dataStore;
        }   
        
        //Return Entry by UserId
        public DbLoginInfo GetEntrybyUserId(String userId) throws SQLException {
            //log.info("Class: DbLoginInfo. Action: Return Entry by UserId operation triggered. ");
            CreateLog("info", "Return Entry by UserId operation triggered.", null);  
            
            //Create new class instance
            DbLoginInfo dataStore = new DbLoginInfo();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use name to locate entry
                String selectSQL = "SELECT LoginInfoId, UserId, LoginResult, SuccessLoginCount,  FailedLoginCount, LastLogin "
                        + "FROM logininfo"
                        + "WHERE UserId = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setString(1, userId);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setLoginInfoId(rset.getInt("LoginInfoId"));
                    dataStore.setUserId(rset.getInt("UserId"));
                    dataStore.setLoginResult(rset.getBoolean("LoginResult"));
                    dataStore.setSuccessLoginCount(rset.getInt("SuccessLoginCount"));
                    dataStore.setFailedLoginCount(rset.getInt("FailedLoginCount")); 
                    dataStore.setLastLogin(rset.getString("LastLogin"));        
            
                    //System.out.println("Class: DbLoginInfo. Action: Entry returned.");
                    //log.info("Class: DbLoginInfo. Action: Entry returned.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                   CreateLog("info", "Entry returned.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                   
                }else {
                    //System.out.println("Class: DbLoginInfo. Action: Entry not found.");
                    //log.error("Class: DbLoginInfo. Action: Entry not found.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("error", "Validation Failed. Entry not found.", null);  
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);     
                }
            }
            catch (SQLException e) {
                //System.out.println("Class: DbLoginInfo. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbLoginInfo. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return dataStore;
        }   
        
    //Return All Users
        @Override
        public ArrayList<DbLoginInfo> GetAllEntries() throws SQLException {
            //log.info("Class: DbLoginInfo. Action: Return all Entry operation triggered. ");
            CreateLog("info", "Return all Entry operation triggered.", null); 
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Return all entries
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM logininfo";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
                    //Create new class instance
                    DbLoginInfo dataStore = new DbLoginInfo();
                    
                    //get info from db to a variable 
                    dataStore.setLoginInfoId(rset.getInt("LoginInfoId"));
                    dataStore.setLoginInfoId(rset.getInt("UserId"));
                    dataStore.setLoginResult(rset.getBoolean("LoginResult"));
                    dataStore.setSuccessLoginCount(rset.getInt("SuccessLoginCount"));
                    dataStore.setFailedLoginCount(rset.getInt("FailedLoginCount")); 
                    dataStore.setLastLogin(rset.getString("LastLogin"));
    
                     // Save entry elements to datastore
                    loginInfoList.add(dataStore);
                }
                
                //System.out.println("Class: DbLoginInfo. Action: EntryList returned.");
                //log.info("Class: DbLoginInfo. Action: EntryList returned.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
                
                CreateLog("info", "EntryList returned.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }
            catch (SQLException e) {
                //System.out.println("Class: DbLoginInfo. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbLoginInfo. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return loginInfoList;
        }

    //Print all Entries
        @Override
        public void ViewAllEntryPrint() throws SQLException {
            //log.info("Class: DbLoginInfo. Action: Print all Entry usign Console operation triggered. ");
            CreateLog("info", "Print all Entry using Console operation triggered.", null); 
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Variables
                int _loginInfoId = 0;
                int _userId = 0;
                boolean _loginResult = true;
                int _successLoginCount = 0;
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
                     _loginResult = rset.getBoolean("LoginResult");
                     _successLoginCount = rset.getInt("SuccessLoginCount");
                    _failedLoginCount = rset.getInt("FailedLoginCount");
                    _lastLogin = rset.getString("LastLogin");
    
                    //print info 
                    System.out.println("\n\nLogInfo Entry Id: " + _loginInfoId);
                    System.out.println("UserId : " + _userId );
                    System.out.println("LoginResult : " + _loginResult );
                    System.out.println("SuccessLoginCount : " + _successLoginCount );
                    System.out.println("FailedLoginCount : " + _failedLoginCount);
                    System.out.println("LastLogin : " + _lastLogin); 
                    System.out.println("+++++++++++++++++++++"); 
                }
                
                //log.info("Class: DbLoginInfo. Action: EntryList printed using console.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
                
                CreateLog("info", "EntryList printed using console.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }
            catch (SQLException e) {
                //System.out.println("Class: DbLoginInfo. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbLoginInfo. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
        }
}
