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
public class DbFolderPerUser extends DbConnectionManager implements DbService<DbFolderPerUser> {
 //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
 public DbFolderPerUser(){
     //log.info("Class: DbFolderPerUser. Action: Default Constructor Triggered.");
     //Connection con = Connection();
     
     super(DbFolderPerUser.class);
     CreateLog("info", "Default Constructor Triggered.", null);  
 }
    
 //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     
 //private Connection con; 
 
 private int folderPerUserId;
 private int userId;
 private int folderId;
 private int folderNumberPerUser;
 private ArrayList<DbFolderPerUser> folderPerUserList;
 
 //private static final Logger log = LogManager.getLogger(DbFolderPerUser.class);
 //Getter/Setter >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 //GetFolderUserId
 public int getFolderPerUserId(){
     return folderPerUserId;
 }
 public void setFolderPerUserId(int folderPerUserId){
     this.folderPerUserId = folderPerUserId;
 }
 
 //UserId
 public int getUserId(){
     return userId;
 }
 public void setUserId(int userId){
     this.userId = userId;
 }
 
 //FolderId
 public int getFolderId(){
     return folderId;
 }
 public void setFolderId(int folderId){
     this.folderId = folderId;
 }
 
 //FolderNumberPerUser
 public int getFolderNumberPerUser(){
     return folderNumberPerUser;
 }
 public void setFolderNumberPerUser(int folderNumberPerUser){
     this.folderNumberPerUser = folderNumberPerUser;
 }
 
 //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
 //Insert Entry 
    public void InsertEntry(int userId, int folderId, int folderNumberPerUser) throws SQLException{
        //log.info("Class: DbFolderPerUser. Action: Insert Entry Operation Triggered.");
        CreateLog("info", "Insert Entry Operation Triggered.", null);  
        
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            int _userId = userId;
            int _folderId = folderId;
            int _folderNumberPerUser = folderNumberPerUser;
            
            boolean isValid = true;
            
           if (isValid == true) 
           {
               //insert to database
                String insertSQL = "INSERT INTO folderperuser "
                        + "(UserId, FolderId, FolderNumberPerUser) "
                        + "VALUE (?,?,?)";
                PreparedStatement ps = con.prepareStatement(insertSQL);
                ps.setInt(1, _userId);
                ps.setInt(2, _folderId);
                ps.setInt(3, _folderNumberPerUser);       
            
                int rowsInserted = ps.executeUpdate();
            
                if(rowsInserted > 0) {
                    //System.out.println("Class: DbFolderPerUser. Action: Entry Inserted.");
                    //log.info("Class: DbFolderPerUser. Action: Entry Inserted.");
                    //con.close();//Close Connection
                   //log.info("Connection closed");
                   
                   CreateLog("info", "Entry Inserted.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                }
           } else 
           {
             // Data not saved due to validation
             //System.out.println("Class: DbFolderPerUser. Action: Validation Failed. Entry Not Added.");
             //log.error("Class: DbFolderPerUser. Action: Validation Failed. Entry Not Added.");
             //con.close(); //Close Connection
             //log.info("Connection closed");
               
               // Data not saved due to validation
                CreateLog("error", "Validation Failed. Entry Not Added.", null);  
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);  
           }
        }
        catch(SQLException e) {
            //System.out.println("Class: DbFolderPerUser. Action: Connection Failed. Entry Not Added");
            //log.error("Class: DbFolderPerUser. Action: Connection Failed. Entry Not Added");
            //log.error("\nDetail Error: " + e);
            
            CreateLog("error", "Connection Failed. Entry Not Added.", e);
        }
    }
    
    //Update Entry
    public void UpdateEntrybyId(int id, int userId, int folderId, int folderNumberPerUser) throws SQLException{
        //log.info("Class: DbFolderPerUser. Action: Update Entry by Id operation triggered. ");
        CreateLog("info", "Update Entry by Id operation triggered.", null);  
        
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            int _userId = userId;
            int _folderId = folderId;
            int _folderNumberPerUser = folderNumberPerUser;
  
            boolean isValid = true;
            
            if (isValid == true)
            {
                // update the entry
                String updateSQL = "UPDATE folderperuser "
                        + "SET UserId = ? FolderId = ? FolderNumberPerUser = ?"
                        + "WHERE FolderPerUserId = ?";
                PreparedStatement psUpdate  = con.prepareStatement(updateSQL);
                psUpdate.setInt(1, _userId);
                psUpdate.setInt(2, _folderId);
                psUpdate.setInt(3, _folderNumberPerUser);
                psUpdate.setInt(4, id);
                int rowsUpdated = psUpdate.executeUpdate();
                
                if(rowsUpdated > 0) {
                    //System.out.println("Class: DbFolderPerUser. Action: Entry Updated.");
                    //log.info("Class: DbFolderPerUser. Action: Entry Updated.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("info", "Entry Updated.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }
            }else
            {
                 // Data not saved due to validation
               // System.out.println("Class: DbFolderPerUser. Action: Validation Failed. Entry Not Updated.");
                //log.error("Class: DbFolderPerUser. Action: Validation Failed. Entry Not Updated.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
                
                // Data not saved due to validation
                CreateLog("error", "Validation Failed. Entry Not Updated.", null);  
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);   
            }  
        }
        catch(SQLException e) {
            //System.out.println("Class: DbFolderPerUser. Action: Connection Failed. Entry not updated.");
            //log.error("Class: DbFolderPerUser. Action: Connection Failed. Entry not updated.");
            //log.error("\nDetail Error: " + e);
            
            CreateLog("error", "Connection Failed. Entry not updated.", e);
        }
    }
    
    // Delete Entry
        @Override
        public void DeleteEntryById(int id) throws SQLException {
            //log.info("Class: DbFolderPerUser. Action: Delete Entry by Id operation trigger.");
            CreateLog("info", "Delete Entry by Id operation trigger.", null);  
            
            try{
                 //database connection 
                Connection con = Connection();
                
                //Delete entry from database 
                String deleteSQL = "DELETE FROM folderperuser WHERE FolderPerUserId = ?";
                PreparedStatement psDelete = con.prepareStatement(deleteSQL);
                psDelete.setInt(1, id);
                int rowsDeleted = psDelete.executeUpdate();
                
                if(rowsDeleted > 0){
                    //System.out.println("Class: DbFolderPerUser. Action: Entry Deleted.");
                    //log.info("Class: DbFolderPerUser. Action: Entry Deleted.");
                    // con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("info", "Entry Deleted.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }
            }
            catch (SQLException e){
                //System.out.println("Class: DbFolderPerUser. Action: Connection Failed. Entry not Deleted. ");
                //log.error("Class: DbFolderPerUser. Action: Connection Failed. Entry not Deleted. ");   
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. Entry not Deleted or Found.", e);
            }
        }
        
     //Return Entry by FolderPerUserId
        @Override
        public DbFolderPerUser GetEntrybyId(int id) throws SQLException {
            //log.info("Class: DbFolderPerUser. Action: Return Entry by Id operation triggered. ");
            CreateLog("info", "Return Entry by Id operation triggered.", null); 
            
            //Create new class instance
            DbFolderPerUser dataStore = new DbFolderPerUser();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use id to locate entry
                String selectSQL = "SELECT FolderPerUserId, UserId, FolderId, FolderNumberPerUser "
                        + "FROM folderperuser"
                        + "WHERE FolderPerUserId = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setInt(1, id);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setFolderPerUserId(rset.getInt("FolderPerUserId"));
                    dataStore.setUserId(rset.getInt("UserId")); 
                    dataStore.setFolderId(rset.getInt("FolderId")); 
                    dataStore.setFolderNumberPerUser(rset.getInt("FolderNumberPerUser"));
                    
                    //System.out.println("Class: DbFolderPerUser. Action: Entry returned.");
                   // log.info("Class: DbFolderPerUser. Action: Entry returned.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("info", "Entry returned.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }else {
                    //System.out.println("Class: DbFolderPerUser. Action: Entry not found.");
                    //log.error("Class: DbFolderPerUser. Action: Entry not found.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("error", "Validation Failed. Entry not found.", null);  
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);   
                }
            }
            catch (SQLException e) {
                //System.out.println("Class: DbFolderPerUser. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbFolderPerUser. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return dataStore;
        }   
        
        //Return Entry by UserId
        public DbFolderPerUser GetEntrybyUserId(int userId) throws SQLException {
            //log.info("Class: DbFolderPerUser. Action: Return Entry by UserId operation triggered. ");
            CreateLog("info", "Return Entry by UserId operation triggered.", null);  
            
            //Create new class instance
            DbFolderPerUser dataStore = new DbFolderPerUser();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use name to locate entry
                String selectSQL = "SELECT FolderPerUserId, UserId, FolderId, FolderNumberPerUser "
                        + "FROM folderperuser"
                        + "WHERE UserId = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setInt(1, userId);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setFolderPerUserId(rset.getInt("UserId"));
                    dataStore.setUserId(rset.getInt("UserId"));
                    dataStore.setFolderId(rset.getInt("FolderId")); 
                    dataStore.setFolderNumberPerUser(rset.getInt("FolderNumberPerUser"));  
            
                    //System.out.println("Class: DbFolderPerUser. Action: Entry returned.");
                    //log.info("Class: DbFolderPerUser. Action: Entry returned.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("info", "Entry returned.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }else {
                    //System.out.println("Class: DbFolderPerUser. Action: Entry not found.");
                   // log.error("Class: DbFolderPerUser. Action: Entry not found.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("error", "Validation Failed. Entry not found.", null);  
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);   
                }
            }
            catch (SQLException e) {
                //System.out.println("Class: DbFolderPerUser. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbFolderPerUser. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return dataStore;
        }   
        
    //Return All Users
        @Override
        public ArrayList<DbFolderPerUser> GetAllEntries() throws SQLException {
            //log.info("Class: DbFolderPerUser. Action: Return all Entry operation triggered. ");
            CreateLog("info", "Return all Entry operation triggered.", null); 
            
            
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Return all entries
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM folderperuser";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
                    //Create new class instance
                    DbFolderPerUser dataStore = new DbFolderPerUser();
                    
                    //get info from db to a variable 
                    dataStore.setFolderPerUserId(rset.getInt("FolderPerUserId"));
                    dataStore.setUserId(rset.getInt("UserId"));
                    dataStore.setFolderId(rset.getInt("FolderId")); 
                    dataStore.setFolderNumberPerUser(rset.getInt("FolderNumberPerUser"));
    
                     // Save entry elements to datastore
                    folderPerUserList.add(dataStore);
                }
                
                //System.out.println("Class: DbFolderPerUser. Action: EntryList returned.");
                //log.info("Class: DbFolderPerUser. Action: EntryList returned.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
                
                CreateLog("info", "EntryList returned.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }
            catch (SQLException e) {
                //System.out.println("Class: DbFolderPerUser. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbFolderPerUser. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return folderPerUserList;
        }

    //Print all Entries
        @Override
        public void ViewAllEntryPrint() throws SQLException {
            //log.info("Class: DbFolderPerUser. Action: Print all Entry usign Console operation triggered. ");
            CreateLog("info", "Print all Entry using Console operation triggered.", null); 
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Variables
                int _folderPerUserId = 0;
                int _userId = 0;
                int _folderId = 0;
                int _folderNumberPerUser = 0;
                
                
                // View all entries using the console
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM folderperuser";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
          
                    //get info from db to a variable 
                    _folderPerUserId = rset.getInt("FolderPerUserId");
                    _userId = rset.getInt("UserId");
                    _folderId = rset.getInt("FolderId");
                    _folderNumberPerUser  = rset.getInt("FolderNumberPerUser");
    
                    //print info 
                    System.out.println("\n\nFolderPerUser Entry Id: " + _folderPerUserId);
                    System.out.println("UserId : " + _userId );
                    System.out.println("FolderId : " + _folderId);
                    System.out.println("FolderNumberPerUser : " + _folderNumberPerUser);
                    System.out.println("+++++++++++++++++++++"); 
                }
                
                //log.info("Class: DbFolderPerUser. Action: EntryList printed using console.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
                
                CreateLog("info", "EntryList printed using console.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }
            catch (SQLException e) {
                //System.out.println("Class: DbFolderPerUser. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbFolderPerUser. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
        }
}
