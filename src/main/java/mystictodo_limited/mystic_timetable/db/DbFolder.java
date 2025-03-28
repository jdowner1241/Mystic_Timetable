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
     super(DbFolder.class);
     CreateLog("info", "Default Constructor Triggered.", null);  
 }
    
 //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     
 //private Connection con; 
 
 private int folderId;
 private String folderName;
 private ArrayList<DbFolder> folderList;

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
                   //Insert Entry and close connection 
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
    public void UpdateEntrybyId(int id) throws SQLException{
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
                    // Perform Insert if not empty
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
                String deleteSQL = "DELETE FROM folder WHERE FolderId = ?";
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
        public DbFolder GetEntrybyId(int id) throws SQLException {
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
        
        //Return Entry by FolderName
        public DbFolder GetEntrybyFolderId(String folderName) throws SQLException {       
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
        
    //Return All folder
        @Override
        public ArrayList<DbFolder> GetAllEntries() throws SQLException {         
            CreateLog("info", "Return all Entry operation triggered.", null); 
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Return all entries
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM folder";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
                    //Create new class instance
                    DbFolder dataStore = new DbFolder();
                    
                    //get info from db to a variable 
                    dataStore.setFolderId(rset.getInt("FolderId"));
                    dataStore.setFolderName(rset.getString("FolderName"));
    
                    // Save entry elements to datastore
                    folderList.add(dataStore);
                }
                
                CreateLog("info", "EntryList returned.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }
            catch (SQLException e) {
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return folderList;
        }

    //Print all Entries
        @Override
        public void ViewAllEntryPrint() throws SQLException {  
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
                
                CreateLog("info", "EntryList printed using console.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }
            catch (SQLException e) {       
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
        }
        
        
}
