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
public class DbFolder extends DbConnectionManager implements DbService<DbFolder> {
 //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
 public DbFolder(){
     //log.info("Class: DbFolder. Action: Default Constructor Triggered.");
     //Connection con = Connection();
     
     super(DbFolder.class);
     CreateLog("info", "Default Constructor Triggered.", null);  
 }
    
 //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     
 //private Connection con; 
 
 private int folderId;
 private String folderName;
 private ArrayList<DbFolder> folderList;
 
 //private static final Logger log = LogManager.getLogger(DbFolder.class);
 //Getter/Setter >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 //FolderId
 public int getFolderId(){
     return folderId;
 }
 public void setFolderId(int folderId){
     this.folderId = folderId;
 }
 
 //FolderName
 public String getFolderName(){
     return folderName;
 }
 public void setFolderName(String folderName){
     this.folderName = folderName;
 }
 
 //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
 //Insert Entry 
    public void InsertEntry(String folderName) throws SQLException{
        //log.info("Class: DbFolder. Action: Insert Entry Operation Triggered.");
        CreateLog("info", "Insert Entry Operation Triggered.", null);
        
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            String _folderName = folderName;
            
            boolean isValid = true;
            
           if (isValid == true) 
           {
               //insert to database
                String insertSQL = "INSERT INTO folder "
                        + "(FolderName) "
                        + "VALUE (?)";
                PreparedStatement ps = con.prepareStatement(insertSQL);
                ps.setString(1, _folderName);      
            
                int rowsInserted = ps.executeUpdate();
            
                if(rowsInserted > 0) {
                    //System.out.println("Class: DbFolder. Action: Entry Inserted.");
                    //log.info("Class: DbFolder. Action: Entry Inserted.");
                    //con.close();//Close Connection
                   //log.info("Connection closed");
                   
                   CreateLog("info", "Entry Inserted.", null);
                   con.close(); //Close Connection
                   CreateLog("info", "Connection closed.", null);
                }
           } else 
           {
             // Data not saved due to validation
             //System.out.println("Class: DbFolder. Action: Validation Failed. Entry Not Added.");
             //log.error("Class: DbFolder. Action: Validation Failed. Entry Not Added.");
             //con.close(); //Close Connection
             //log.info("Connection closed");
               
               // Data not saved due to validation
                CreateLog("error", "Validation Failed. Entry Not Added.", null);  
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null); 
           }
        }
        catch(SQLException e) {
            //System.out.println("Class: DbFolder. Action: Connection Failed. Entry Not Added");
            //log.error("Class: DbFolder. Action: Connection Failed. Entry Not Added");
            //log.error("\nDetail Error: " + e);
            
            CreateLog("error", "Connection Failed. Entry Not Added.", e);
        }
    }
    
    //Update Entry
    public void UpdateEntrybyId(int id) throws SQLException{
        //log.info("Class: DbFolder. Action: Update Entry by Id operation triggered. ");
        CreateLog("info", "Update Entry by Id operation triggered.", null);  
        
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            String _folderName = folderName;
  
            boolean isValid = true;
            
            if (isValid == true)
            {
                // update the entry
                String updateSQL = "UPDATE folder "
                        + "SET FolderName = ?"
                        + "WHERE FolderId = ?";
                PreparedStatement psUpdate  = con.prepareStatement(updateSQL);
                psUpdate.setString(1, _folderName);
                psUpdate.setInt(2, id);
                int rowsUpdated = psUpdate.executeUpdate();
                
                if(rowsUpdated > 0) {
                    //System.out.println("Class: DbFolder. Action: Entry Updated.");
                    //log.info("Class: DbFolder. Action: Entry Updated.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("info", "Entry Updated.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }
            }else
            {
                 // Data not saved due to validation
                //System.out.println("Class: DbFolder. Action: Validation Failed. Entry Not Updated.");
                //log.error("Class: DbFolder. Action: Validation Failed. Entry Not Updated.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
                
                // Data not saved due to validation
                CreateLog("error", "Validation Failed. Entry Not Updated.", null);  
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);   
            }  
        }
        catch(SQLException e) {
            //System.out.println("Class: DbFolder. Action: Connection Failed. Entry not updated.");
            //log.error("Class: DbFolder. Action: Connection Failed. Entry not updated.");
            //log.error("\nDetail Error: " + e);
            
            CreateLog("error", "Connection Failed. Entry not updated.", e);
        }
    }
    
    // Delete Entry
        @Override
        public void DeleteEntryById(int id) throws SQLException {
            //log.info("Class: DbFolder. Action: Delete Entry by Id operation trigger.");
            CreateLog("info", "Delete Entry by Id operation trigger.", null);  
            
            try{
                 //database connection 
                Connection con = Connection();
                
                //Delete entry from database 
                String deleteSQL = "DELETE FROM folder WHERE FolderId = ?";
                PreparedStatement psDelete = con.prepareStatement(deleteSQL);
                psDelete.setInt(1, id);
                int rowsDeleted = psDelete.executeUpdate();
                
                if(rowsDeleted > 0){
                    //System.out.println("Class: DbFolder. Action: Entry Deleted.");
                    //log.info("Class: DbFolder. Action: Entry Deleted.");
                    // con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("info", "Entry Deleted.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }
            }
            catch (SQLException e){
                //System.out.println("Class: DbFolder. Action: Connection Failed. Entry not Deleted. ");
                //log.error("Class: DbFolder. Action: Connection Failed. Entry not Deleted. ");   
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. Entry not Deleted or Found.", e);
            }
        }
        
     //Return Entry by Id
        @Override
        public DbFolder GetEntrybyId(int id) throws SQLException {
            //log.info("Class: DbFolder. Action: Return Entry by Id operation triggered. ");
            CreateLog("info", "Return Entry by Id operation triggered.", null); 
            
            //Create new class instance
            DbFolder dataStore = new DbFolder();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use id to locate entry
                String selectSQL = "SELECT FolderId, FolderName"
                        + "FROM folder"
                        + "WHERE FolderId = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setInt(1, id);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setFolderId(rset.getInt("FolderId"));
                    dataStore.setFolderName(rset.getString("FolderName"));
             
                    //System.out.println("Class: DbFolder. Action: Entry returned.");
                    //log.info("Class: DbFolder. Action: Entry returned.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("info", "Entry returned.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }else {
                    //System.out.println("Class: DbFolder. Action: Entry not found.");
                    //log.error("Class: DbFolder. Action: Entry not found.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("error", "Validation Failed. Entry not found.", null);  
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);   
                }
            }
            catch (SQLException e) {
                //System.out.println("Class: DbFolder. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbFolder. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return dataStore;
        }   
        
        //Return Entry by FolderName
        public DbFolder GetEntrybyFolderId(String folderName) throws SQLException {
            //log.info("Class: DbFolder. Action: Return Entry by FolderName operation triggered. ");
            CreateLog("info", "Return Entry by FolderName operation triggered.", null);  
            
            //Create new class instance
            DbFolder dataStore = new DbFolder();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use name to locate entry
                String selectSQL = "SELECT FolderId, FolderName"
                        + "FROM folder"
                        + "WHERE FolderName = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setString(1, folderName);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setFolderId(rset.getInt("FolderId"));
                    dataStore.setFolderName(rset.getString("FolderName"));

                    //System.out.println("Class: DbFolder. Action: Entry returned.");
                    //log.info("Class: DbFolder. Action: Entry returned.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("info", "Entry returned.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }else {
                    //System.out.println("Class: DbFolder. Action: Entry not found.");
                    //log.error("Class: DbFolder. Action: Entry not found.");
                    //con.close(); //Close Connection
                    //log.info("Connection closed");
                    
                    CreateLog("error", "Validation Failed. Entry not found.", null);  
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);   
                }
            }
            catch (SQLException e) {
                //System.out.println("Class: DbFolder. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbFolder. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return dataStore;
        }   
        
    //Return All folder
        @Override
        public ArrayList<DbFolder> GetAllEntries() throws SQLException {
            //log.info("Class: DbFolder. Action: Return all Entry operation triggered. ");
            CreateLog("info", "Return all Entry operation triggered.", null); 
            
            //Create new class instance
            DbFolder dataStore = new DbFolder();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Return all entries
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM folder";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
                    //get info from db to a variable 
                    dataStore.setFolderId(rset.getInt("FolderId"));
                    dataStore.setFolderName(rset.getString("FolderName"));
    
                    // Save entries 
                    folderList.add(dataStore);
                }
                
                //System.out.println("Class: DbFolder. Action: EntryList returned.");
                //log.info("Class: DbFolder. Action: EntryList returned.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
                
                CreateLog("info", "EntryList returned.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }
            catch (SQLException e) {
                //System.out.println("Class: DbFolder. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbFolder. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return folderList;
        }

    //Print all Entries
        @Override
        public void ViewAllEntryPrint() throws SQLException {
            //log.info("Class: DbFolder. Action: Print all Entry usign Console operation triggered. ");
            CreateLog("info", "Print all Entry using Console operation triggered.", null); 
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Variables
                int _folderId = 0;
                String _folderName = null;
                
                // View all entries using the console
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM folder";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
          
                    //get info from db to a variable 
                    _folderId = rset.getInt("FolderId");
                    _folderName = rset.getString("FolderName");
     
                    //print info 
                    System.out.println("\n\nFolder Entry Id: " + _folderId);
                    System.out.println("FolderName : " + _folderName );
                    System.out.println("+++++++++++++++++++++"); 
                }
                
                //log.info("Class: DbFolder. Action: EntryList printed using console.");
                //con.close(); //Close Connection
                //log.info("Connection closed");
                
                CreateLog("info", "EntryList printed using console.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }
            catch (SQLException e) {
                //System.out.println("Class: DbFolder. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("Class: DbFolder. Action: Connection Failed. No Entry loaded or Found.");
                //log.error("\nDetail Error: " + e);
                
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
        }
        
        
}
