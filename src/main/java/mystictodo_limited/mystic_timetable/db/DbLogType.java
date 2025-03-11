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
     log.info("Class: DbLogType. Action: Default Constructor Triggered.");
     //Connection con = Connection();
 }
    
 //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     
 //private Connection con; 
 
 private int logTypeId;
 private String name;
 private String Description;
 private ArrayList<DbLogType> logTypeList;
 
 private static final Logger log = LogManager.getLogger(DbLogType.class);
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
     return Description;
 }
 public void setDescription(String Description){
     this.Description = Description;
 }
 
 
 //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
 //Insert Entry 
    public void InsertEntry(String name, String description) throws SQLException{
        log.info("Class: DbLogType. Action: Insert Entry Operation Triggered.");
        
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
                    System.out.println("Class: DbLogType. Action: Entry Inserted.");
                    log.info("Class: DbLogType. Action: Entry Inserted.");
                
                    //Close Connection
                    con.close();
                   log.info("Connection closed");
                }
           } else 
           {
             // Data not saved due to validation
             System.out.println("Class: DbLogType. Action: Validation Failed. Entry Not Added.");
             log.error("Class: DbLogType. Action: Validation Failed. Entry Not Added.");
             con.close(); //Close Connection
             log.info("Connection closed");
           }
        }
        catch(SQLException e) {
            System.out.println("\nClass: DbLogType. Action: Connection Failed. Entry Not Added");
            log.error("Class: DbLogType. Action: Connection Failed. Entry Not Added");
            log.error("\nDetail Error: " + e);
        }
    }
    
    //Update Entry
    public void UpdateEntrybyId(int id, String name, String description) throws SQLException{
        log.info("Class: DbLogType. Action: Update Entry by Id operation triggered. ");
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
                    System.out.println("Class: DbLogType. Action: Entry Updated.");
                    log.info("Class: DbLogType. Action: Entry Updated.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }else
            {
                 // Data not saved due to validation
                System.out.println("Class: DbLogType. Action: Validation Failed. Entry Not Updated.");
                log.error("Class: DbLogType. Action: Validation Failed. Entry Not Updated.");
                con.close(); //Close Connection
                log.info("Connection closed");
            }  
        }
        catch(SQLException e) {
            System.out.println("Class: DbLogType. Action: Connection Failed. Entry not updated.");
            log.error("Class: DbLogType. Action: Connection Failed. Entry not updated.");
            log.error("\nDetail Error: " + e);
        }
    }
    
    // Delete Entry
        @Override
        public void DeleteEntryById(int id) throws SQLException {
            log.info("Class: DbLogType. Action: Delete Entry by Id operation trigger.");
            try{
                 //database connection 
                Connection con = Connection();
                
                //Delete entry from database 
                String deleteSQL = "DELETE FROM logtype WHERE LogTypeId = ?";
                PreparedStatement psDelete = con.prepareStatement(deleteSQL);
                psDelete.setInt(1, id);
                int rowsDeleted = psDelete.executeUpdate();
                
                if(rowsDeleted > 0){
                    System.out.println("Class: DbLogType. Action: Entry Deleted.");
                    log.info("Class: DbLogType. Action: Entry Deleted.");
                     con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }
            catch (SQLException e){
                System.out.println("Class: DbLogType. Action: Connection Failed. Entry not Deleted. ");
                log.error("Class: DbLogType. Action: Connection Failed. Entry not Deleted. ");   
                log.error("\nDetail Error: " + e);
            }
        }
        
     //Return Entry by Id
        @Override
        public DbLogType GetEntrybyId(int id) throws SQLException {
            log.info("Class: DbLogType. Action: Return Entry by Id operation triggered. ");
            
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
                    
                    System.out.println("Class: DbLogType. Action: Entry returned.");
                    log.info("Class: DbLogType. Action: Entry returned.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }else {
                    System.out.println("Class: DbLogType. Action: Entry not found.");
                    log.error("Class: DbLogType. Action: Entry not found.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }
            catch (SQLException e) {
                System.out.println("Class: DbLogType. Action: Connection Failed. No Entry loaded or Found.");
                log.error("Class: DbLogType. Action: Connection Failed. No Entry loaded or Found.");
                log.error("\nDetail Error: " + e);
            }
            
            return dataStore;
        }   
        
        //Return Entry by Name
        public DbLogType GetEntrybyName(String name) throws SQLException {
            log.info("Class: DbLogType. Action: Return Entry by Name operation triggered. ");
            
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
            
                    System.out.println("Class: DbLogType. Action: Entry returned.");
                    log.info("Class: DbLogType. Action: Entry returned.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }else {
                    System.out.println("Class: DbLogType. Action: Entry not found.");
                    log.error("Class: DbLogType. Action: Entry not found.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }
            catch (SQLException e) {
                System.out.println("Class: DbLogType. Action: Connection Failed. No Entry loaded or Found.");
                log.error("Class: DbLogType. Action: Connection Failed. No Entry loaded or Found.");
                log.error("\nDetail Error: " + e);
            }
            
            return dataStore;
        }   
        
    //Return All Users
        @Override
        public ArrayList<DbLogType> GetAllEntries() throws SQLException {
            log.info("Class: DbLogType. Action: Return all Entry operation triggered. ");
            
            //Create new class instance
            DbLogType dataStore = new DbLogType();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Return all entries
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM logtype";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
                    //get info from db to a variable 
                    dataStore.setLogTypeId(rset.getInt("LogTypeId"));
                    dataStore.setName(rset.getString("Name"));
                    dataStore.setDescription(rset.getString("Description")); 
    
                    // Save entries 
                    logTypeList.add(dataStore);
                }
                
                System.out.println("Class: DbLogType. Action: EntryList returned.");
                log.info("Class: DbLogType. Action: EntryList returned.");
                con.close(); //Close Connection
                log.info("Connection closed");
            }
            catch (SQLException e) {
                System.out.println("Class: DbLogType. Action: Connection Failed. No Entry loaded or Found.");
                log.error("Class: DbLogType. Action: Connection Failed. No Entry loaded or Found.");
                log.error("\nDetail Error: " + e);
            }
            
            return logTypeList;
        }

    //Print all Entries
        @Override
        public void ViewAllEntryPrint() throws SQLException {
            log.info("Class: DbLogType. Action: Print all Entry usign Console operation triggered. ");
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
                    System.out.println("\n\nLogType Entry Id: " + _logTypeId);
                    System.out.println("Name : " + _name );
                    System.out.println("Description : " + _description);
                    System.out.println("+++++++++++++++++++++"); 
                }
                
                log.info("Class: DbLogType. Action: EntryList printed using console.");
                con.close(); //Close Connection
                log.info("Connection closed");
            }
            catch (SQLException e) {
                System.out.println("Class: DbLogType. Action: Connection Failed. No Entry loaded or Found.");
                log.error("Class: DbLogType. Action: Connection Failed. No Entry loaded or Found.");
                log.error("\nDetail Error: " + e);
            }
        }
}
