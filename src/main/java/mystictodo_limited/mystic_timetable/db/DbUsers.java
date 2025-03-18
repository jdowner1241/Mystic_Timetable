/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.db;
import mystictodo_limited.mystic_timetable.dbInterface.*;
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
public class DbUsers extends DbConnectionManager implements DbService<DbUsers> {
 //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
 public DbUsers(){
     super(DbUsers.class);
     //log.info("Class: DbUsers. Action: Default Constructor Triggered.");
     CreateLog("info", "Default Constructor Triggered.", null);

 }
    
 //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     
 //private Connection con; 
 
 private int userId;
 private String userName;
 private String emailAddress;
 private String password;
 private LocalDateTime registrationDate;
 private ArrayList<DbUsers> userList;
 
 //private static final Logger log = LogManager.getLogger(DbUsers.class);
 //Getter/Setter >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 //UserId
 public int getUserId(){
     return userId;
 }
 public void setUserId(int userId){
     this.userId = userId;
 }
 
 //UserName
 public String getUserName(){
     return userName;
 }
 public void setUserName(String userName){
     this.userName = userName;
 }
 
 //EmailAddress
 public String getEmailAddress(){
     return emailAddress;
 }
 public void setEmailAddress(String emailAddress){
     this.emailAddress = emailAddress;
 }
 
 //Password
 public String getPassword(){
     return password;
 }
 public void setPassword(String password){
     this.password = password;
 }
 
 // RegistrationDate
 public LocalDateTime getRegistrationDate(){
     return registrationDate;
 }
 public void setRegistrationDate(String _registrationDate){
     DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
     registrationDate = LocalDateTime.parse(_registrationDate, formatter);
 }
 
 //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
 //Insert Entry 
    public void InsertEntry(String userName, String emailAddress, String password) throws SQLException{
       //log.info("Class: DbUsers. Action: Insert Entry Operation Triggered.");
       CreateLog("info", "Insert Entry Operation Triggered.", null);
        
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            String _userName = userName;
            String _emailAddress = emailAddress;
            String _password = password;
            LocalDateTime _setRegistrationDate = LocalDateTime.now();
            String _registrationDate = _setRegistrationDate.toString();
            
            boolean isValid = true;
            
           if (isValid == true) 
           {
               //insert to database
                String insertSQL = "INSERT INTO users "
                        + "(UserName, EmailAddress, Password, RegistrationDate) "
                        + "VALUE (?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(insertSQL);
                ps.setString(1, _userName);
                ps.setString(2, _emailAddress);
                ps.setString(3, _password);
                ps.setString(4, _registrationDate);         
            
                int rowsInserted = ps.executeUpdate();
            
                if(rowsInserted > 0) {
                    //System.out.println("Class: DbUsers. Action: Entry Inserted.");
                    //log.info("Class: DbUsers. Action: Entry Inserted.");
                    //con.close(); //Close Connection
                   //log.info("Connection closed");
                   
                   CreateLog("info", "Entry Inserted.", null);
                   CreateLog("info", "Entry also added to the DbFolderPerUser table.", null);  // When new user is created. Default folder automatically added to DbFolderPerUser table. 
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                }
           } else 
           {
             // Data not saved due to validation
             //System.out.println("Class: DbUsers. Action: Validation Failed. Entry Not Added.");
             //log.error("Class: DbUsers. Action: Validation Failed. Entry Not Added.");
             //con.close(); //Close Connection
             //log.info("Connection closed");
               
             // Data not saved due to validation  
             CreateLog("error", "Validation Failed. Entry Not Added.", null);  
             con.close(); //Close Connection
             CreateLog("info", "Connection closed.", null);
           }
        }
        catch(SQLException e) {
            //System.out.println("\nClass: DbUsers. Action: Connection Failed. Entry Not Added");
            //log.error("Class: DbUsers. Action: Connection Failed. Entry Not Added");
            //log.error("\nDetail Error: " + e);
            
            CreateLog("error", "Connection Failed. Entry Not Added.", e);
        }
    }
    
    //Update Entry
    public void UpdateEntrybyId(int id, String userName, String emailAddress, String password) throws SQLException{
        //log.info("Class: DbUsers. Action: Update Entry by Id operation triggered. ");
        CreateLog("info", "Update Entry by Id operation triggered.", null);
        
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            String _userName = userName;
            String _emailAddress = emailAddress;
            String _password = password;
  
            boolean isValid = true;
            
            if (isValid == true)
            {
                // update the entry
                String updateSQL = "UPDATE users "
                        + "SET UserName = ? EmailAddress = ? Password = ?"
                        + "WHERE UserId = ?";
                PreparedStatement psUpdate  = con.prepareStatement(updateSQL);
                psUpdate.setString(1, _userName);
                psUpdate.setString(2, _emailAddress);
                psUpdate.setString(3, _password);
                psUpdate.setInt(4, id);
                int rowsUpdated = psUpdate.executeUpdate();
                
                if(rowsUpdated > 0) {
                    //System.out.println("Class: DbUsers. Action: Entry Updated.");
                    //log.info("Class: DbUsers. Action: Entry Updated.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                   CreateLog("info", "Entry Updated.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                }
            }else
            {
                 // Data not saved due to validation
                //System.out.println("Class: DbUsers. Action: Validation Failed. Entry Not Updated.");
                //log.error("Class: DbUsers. Action: Validation Failed. Entry Not Updated.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
                
                CreateLog("error", "Validation Failed. Entry Not Updated.", null);  
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }  
        }
        catch(SQLException e) {
            //System.out.println("Class: DbUsers. Action: Connection Failed. Entry not updated.");
            //log.error("Class: DbUsers. Action: Connection Failed. Entry not updated.");
            //log.error("\nDetail Error: " + e);
            
            CreateLog("error", "Connection Failed. Entry Not updated.", e);
        }
    }
    
    // Delete Entry
        @Override
        public void DeleteEntryById(int id) throws SQLException {
            //log.info("Class: DbUsers. Action: Delete Entry by Id operation trigger.");
            CreateLog("info", "Delete Entry by Id operation trigger.", null);
            
            try{
                 //database connection 
                Connection con = Connection();
                
                //Delete entry from database 
                String deleteSQL = "DELETE FROM users WHERE UserId = ?";
                PreparedStatement psDelete = con.prepareStatement(deleteSQL);
                psDelete.setInt(1, id);
                int rowsDeleted = psDelete.executeUpdate();
                
                if(rowsDeleted > 0){
                    //System.out.println("Class: DbUsers. Action: Entry Deleted.");
                    //log.info("Class: DbUsers. Action: Entry Deleted.");
                    // con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                   CreateLog("info", "Entry Deleted.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                    
                }
            }
            catch (SQLException e){
                //System.out.println("Class: DbUsers. Action: Connection Failed. Entry not Deleted. ");
                //log.error("Class: DbUsers. Action: Connection Failed. Entry not Deleted. ");   
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. Entry not Deleted or Found.", e);
            }
        }
        
     //Return Entry by Id
        @Override
        public DbUsers GetEntrybyId(int id) throws SQLException {
            //log.info("Class: DbUsers. Action: Return Entry by Id operation triggered. ");
            CreateLog("info", "Return Entry by Id operation triggered.", null);
  
            //Create new class instance
            DbUsers dataStore = new DbUsers();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use id to locate entry
                String selectSQL = "SELECT UserId, UserName, EmailAddress, Password, RegistrationDate "
                        + "FROM users"
                        + "WHERE UserId = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setInt(1, id);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setUserId(rset.getInt("UserId"));
                    dataStore.setUserName(rset.getString("UserName"));
                    dataStore.setEmailAddress(rset.getString("EmailAddress")); 
                    dataStore.setPassword(rset.getString("Password"));
                    dataStore.setRegistrationDate(rset.getString("RegistrationDate")); 
                    
                    //System.out.println("Class: DbUsers. Action: Entry returned.");
                    //log.info("Class: DbUsers. Action: Entry returned.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                   CreateLog("info", "Entry returned.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                    
                }else {
                    //System.out.println("Class: DbUsers. Action: Entry not found.");
                    //log.error("Class: DbUsers. Action: Entry not found.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                   CreateLog("error", "Entry not found.", null);  
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }
            }
            catch (SQLException e) {
                //System.out.println("Class: DbUsers. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbUsers. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return dataStore;
        }   
        
        //Return Entry by UserName
        public DbUsers GetEntrybyUserName(String userName) throws SQLException {
            //log.info("Class: DbUsers. Action: Return Entry by UserName operation triggered. ");
            CreateLog("info", "Return Entry by UserName operation triggered.", null);
            
            //Create new class instance
            DbUsers dataStore = new DbUsers();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use name to locate entry
                String selectSQL = "SELECT UserId, UserName, EmailAddress, Password, RegistrationDate "
                        + "FROM users"
                        + "WHERE UserName = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setString(1, userName);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setUserId(rset.getInt("UserId"));
                    dataStore.setUserName(rset.getString("UserName"));
                    dataStore.setEmailAddress(rset.getString("EmailAddress")); 
                    dataStore.setPassword(rset.getString("Password"));
                    dataStore.setRegistrationDate(rset.getString("RegistrationDate"));        
            
                    //System.out.println("Class: DbUsers. Action: Entry returned.");
                    //log.info("Class: DbUsers. Action: Entry returned.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                   CreateLog("info", "Entry returned.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                    
                }else {
                    //System.out.println("Class: DbUsers. Action: Entry not found.");
                    //log.error("Class: DbUsers. Action: Entry not found.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                   CreateLog("error", "Entry not found.", null);  
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null); 
                }
            }
            catch (SQLException e) {
                //System.out.println("Class: DbUsers. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbUsers. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return dataStore;
        }   
        
    //Return All Users
        @Override
        public ArrayList<DbUsers> GetAllEntries() throws SQLException {
            //log.info("Class: DbUsers. Action: Return all Entry operation triggered. ");
            CreateLog("info", "Return all Entry operation triggered.", null);
            
            //Create new class instance
            DbUsers dataStore = new DbUsers();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Return all entries
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM users";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
                    //get info from db to a variable 
                    dataStore.setUserId(rset.getInt("UserId"));
                    dataStore.setUserName(rset.getString("UserName"));
                    dataStore.setEmailAddress(rset.getString("EmailAddress")); 
                    dataStore.setPassword(rset.getString("Password"));
                    dataStore.setRegistrationDate(rset.getString("RegistrationDate"));
    
                    // Save entries 
                    userList.add(dataStore);
                }
                
                //System.out.println("Class: DbUsers. Action: EntryList returned.");
                //log.info("Class: DbUsers. Action: EntryList returned.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
                
                CreateLog("info", "EntryList returned.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }
            catch (SQLException e) {
                //System.out.println("Class: DbUsers. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbUsers. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return userList;
        }

    //Print all Entries
        @Override
        public void ViewAllEntryPrint() throws SQLException {
            //log.info("Class: DbUsers. Action: Print all Entry usign Console operation triggered. ");
            CreateLog("info", "Print all Entry using Console operation triggered.", null);
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Variables
                int _userId = 0;
                String _userName = null;
                String _emailAddress = null;
                String _password = null;
                String _registrationDate = null;
                
                
                // View all entries using the console
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM users";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
          
                    //get info from db to a variable 
                    _userId = rset.getInt("UserId");
                    _userName = rset.getString("UserName");
                    _emailAddress = rset.getString("EmailAddress");
                    _password  = rset.getString("Password");
                    _registrationDate = rset.getString("RegistrationDate");
    
                    //print info 
                    System.out.println("\n\nUser Entry Id: " + _userId);
                    System.out.println("UserName : " + _userName );
                    System.out.println("EmailAddress : " + _emailAddress);
                    System.out.println("Password : " + _password);
                    System.out.println("RegistrationDate : " + _registrationDate); 
                    System.out.println("+++++++++++++++++++++"); 
                }
                
                //log.info("Class: DbUsers. Action: EntryList printed using console.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
                
                CreateLog("info", "EntryList printed using console.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }
            catch (SQLException e) {
                //System.out.println("Class: DbUsers. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbUsers. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
        }
        
}
