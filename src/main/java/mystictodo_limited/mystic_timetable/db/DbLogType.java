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
public class DbLogType extends DbConnectionManager implements DbService<DbLogType> {
 //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
 public DbLogType(){
     super(DbLogType.class);
     CreateLog("info", "Default Constructor Triggered.", null);  
 }
    
 //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     

 private int logTypeId;
 private String name;
 private String description;
 private ArrayList<DbLogType> logTypeList;
 
 //Getter/Setter >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 //LogTypeId
 public int getLogTypeId(){
     return logTypeId;
 }
 public void setLogTypeId(int logTypeId){
     this.logTypeId = logTypeId;
 }
 
 //Name
 public String getName(){
     return name;
 }
 public void setName(String name){
     this.name = name;
 }
 
 //Description
 public String getDescription(){
     return description;
 }
 public void setDescription(String description){
     this.description = description;
 }
 
 
 //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
 //Insert Entry 
    public void InsertEntry(String name, String description) throws SQLException{
        CreateLog("info", "Insert Entry Operation Triggered.", null);  
        
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            String _name = name;
            String _description = description;
            
            boolean isValid = true;
            
           if (isValid == true) 
           {
               //insert to database
                String insertSQL = "INSERT INTO logtype "
                        + "(Name, Description) "
                        + "VALUE (?,?)";
                PreparedStatement ps = con.prepareStatement(insertSQL);
                ps.setString(1, _name);
                ps.setString(2, _description);   
            
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
    public void UpdateEntrybyId(int id, String name, String description) throws SQLException{
        CreateLog("info", "Update Entry by Id operation triggered.", null);  
        
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            String _name = name;
            String _description = description;
  
            boolean isValid = true;
            
            if (isValid == true)
            {
                // update the entry
                String updateSQL = "UPDATE logtype "
                        + "SET Name = ? Description = ?"
                        + "WHERE LogTypeId = ?";
                PreparedStatement psUpdate  = con.prepareStatement(updateSQL);
                psUpdate.setString(1, _name);
                psUpdate.setString(2, _description);
                psUpdate.setInt(4, id);
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
                String deleteSQL = "DELETE FROM logtype WHERE LogTypeId = ?";
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
        public DbLogType GetEntrybyId(int id) throws SQLException {
            CreateLog("info", "Return Entry by Id operation triggered.", null);  
            
            //Create new class instance
            DbLogType dataStore = new DbLogType();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use id to locate entry
                String selectSQL = "SELECT LogTypeId, Name, Description "
                        + "FROM logtype"
                        + "WHERE LogTypeId = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setInt(1, id);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setLogTypeId(rset.getInt("LogTypeId"));
                    dataStore.setName(rset.getString("Name"));
                    dataStore.setDescription(rset.getString("Description")); 
                    
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
        
        //Return Entry by Name
        public DbLogType GetEntrybyName(String name) throws SQLException {
           CreateLog("info", "Return Entry by LogType Name operation triggered.", null);  
            
            //Create new class instance
            DbLogType dataStore = new DbLogType();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use name to locate entry
                String selectSQL = "SELECT LogTypeId, Name, Description "
                        + "FROM logtype"
                        + "WHERE Name = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setString(1, name);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setLogTypeId(rset.getInt("LogTypeId"));
                    dataStore.setName(rset.getString("Name"));
                    dataStore.setDescription(rset.getString("Description"));    
            
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
        public ArrayList<DbLogType> GetAllEntries() throws SQLException {
            CreateLog("info", "Return all Entry operation triggered.", null); 
            
            
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Return all entries
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM logtype";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
                    //Create new class instance
                    DbLogType dataStore = new DbLogType();
                    
                    //get info from db to a variable 
                    dataStore.setLogTypeId(rset.getInt("LogTypeId"));
                    dataStore.setName(rset.getString("Name"));
                    dataStore.setDescription(rset.getString("Description")); 
    
                     // Save entry elements to datastore
                    logTypeList.add(dataStore);
                }
                
                CreateLog("info", "EntryList returned.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null);
            }
            catch (SQLException e) {
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return logTypeList;
        }

    //Print all Entries
        @Override
        public void ViewAllEntryPrint() throws SQLException {
            CreateLog("info", "Print all Entry using Console operation triggered.", null); 
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Variables
                int _logTypeId = 0;
                String _name = null;
                String _description = null;
                
                
                // View all entries using the console
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM logtype";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
          
                    //get info from db to a variable 
                    _logTypeId = rset.getInt("LogTypeId");
                    _name = rset.getString("Name");
                    _description = rset.getString("Description");
    
                    //print info 
                    System.out.println("\n\nLogType Id: " + _logTypeId);
                    System.out.println("Name : " + _name );
                    System.out.println("Description : " + _description);
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
