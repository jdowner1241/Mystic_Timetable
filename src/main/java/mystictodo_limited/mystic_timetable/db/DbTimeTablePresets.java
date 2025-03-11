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
public class DbTimeTablePresets extends DbConnectionManager implements DbService<DbTimeTablePresets>  {
 //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
 public DbTimeTablePresets(){
     log.info("Class: DbTimeTablePresets. Action: Default Constructor Triggered.");
     //Connection con = Connection();
 }
    
 //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     
 //private Connection con; 
 
 private int presetId;
 private String presetName;
 private String presetCategory;
 private String presetColor;
 private ArrayList<DbTimeTablePresets> presetList;
 
 private static final Logger log = LogManager.getLogger(DbTimeTablePresets.class);
 //Getter/Setter >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 //PresetId
 public int getPresetId(){
     return presetId;
 }
 public void setPresetId(int presetId){
     this.presetId = presetId;
 }
 
 //PresetName
 public String getPresetName(){
     return presetName;
 }
 public void setPresetName(String presetName){
    this.presetName = presetName;
 }
 
 //PresetCategory
 public String getPresetCategory(){
     return presetCategory;
 }
 public void setPresetCategory(String presetCategory){
     this.presetCategory = presetCategory;
 }
 
 //PresetColor
 public String getPresetColor(){
     return presetColor;
 }
 public void setPresetColor(String presetColor){
     this.presetColor = presetColor;
 }
 
 //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
 //Insert Entry 
    public void InsertEntry(String presetName, String presetCategory, String presetColor) throws SQLException{
        log.info("Class: DbTimeTablePresets. Action: Insert Entry Operation Triggered.");
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            String _presetName = presetName;
            String _presetCategory = presetCategory;
            String _presetColor = presetColor;
            
            boolean isValid = true;
            
           if (isValid == true) 
           {
               //insert to database
                String insertSQL = "INSERT INTO timetablepresets "
                        + "(PresetName, PresetCategory, PresetColor) "
                        + "VALUE (?,?,?)";
                PreparedStatement ps = con.prepareStatement(insertSQL);
                ps.setString(1, _presetName);
                ps.setString(2, _presetCategory);
                ps.setString(3, _presetColor);     
            
                int rowsInserted = ps.executeUpdate();
            
                if(rowsInserted > 0) {
                    System.out.println("Class: DbTimeTablePresets. Action: Entry Inserted.");
                    log.info("Class: DbTimeTablePresets. Action: Entry Inserted.");
                
                    //Close Connection
                    con.close();
                   log.info("Connection closed");
                }
           } else 
           {
             // Data not saved due to validation
             System.out.println("Class: DbTimeTablePresets. Action: Validation Failed. Entry Not Added.");
             log.error("Class: DbTimeTablePresets. Action: Validation Failed. Entry Not Added.");
             con.close(); //Close Connection
             log.info("Connection closed");
           }
        }
        catch(SQLException e) {
            System.out.println("Class: DbTimeTablePresets. Action: Connection Failed. Entry Not Added");
            log.error("Class: DbTimeTablePresets. Action: Connection Failed. Entry Not Added");
            log.error("\nDetail Error: " + e);
        }
    }
    
    //Update Entry
    public void UpdateEntrybyId(int id, String presetName, String presetCategory, String presetColor) throws SQLException{
        log.info("Class: DbTimeTablePresets. Action: Update Entry by Id operation triggered. ");
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            String _presetName = presetName;
            String _presetCategory = presetCategory;
            String _presetColor = presetColor;
  
            boolean isValid = true;
            
            if (isValid == true)
            {
                // update the entry
                String updateSQL = "UPDATE timetablepresets "
                        + "SET PresetName = ? PresetCategory = ? PresetColor = ?"
                        + "WHERE PresetId = ?";
                PreparedStatement psUpdate  = con.prepareStatement(updateSQL);
                psUpdate.setString(1, _presetName);
                psUpdate.setString(2, _presetCategory);
                psUpdate.setString(3, _presetColor);
                psUpdate.setInt(4, id);
                int rowsUpdated = psUpdate.executeUpdate();
                
                if(rowsUpdated > 0) {
                    System.out.println("Class: DbTimeTablePresets. Action: Entry Updated.");
                    log.info("Class: DbTimeTablePresets. Action: Entry Updated.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }else
            {
                 // Data not saved due to validation
                System.out.println("Class: DbTimeTablePresets. Action: Validation Failed. Entry Not Updated.");
                log.info("Class: DbTimeTablePresets. Action: Validation Failed. Entry Not Updated.");
                con.close(); //Close Connection
                log.info("Connection closed");
            }  
        }
        catch(SQLException e) {
            System.out.println("Class: DbTimeTablePresets. Action: Connection Failed. Entry not updated.");
            log.error("Class: DbTimeTablePresets. Action: Connection Failed. Entry not updated.");
            log.error("\nDetail Error: " + e);
        }
    }
    
    // Delete Entry
        @Override
        public void DeleteEntryById(int id) throws SQLException {
            log.info("Class: DbTimeTablePresets. Action: Delete Entry by Id operation trigger.");
            try{
                 //database connection 
                Connection con = Connection();
                
                //Delete entry from database 
                String deleteSQL = "DELETE FROM timetablepresets WHERE PresetId = ?";
                PreparedStatement psDelete = con.prepareStatement(deleteSQL);
                psDelete.setInt(1, id);
                int rowsDeleted = psDelete.executeUpdate();
                
                if(rowsDeleted > 0){
                    System.out.println("Class: DbTimeTablePresets. Action: Entry Deleted.");
                    log.info("Class: DbTimeTablePresets. Action: Entry Deleted.");
                     con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }
            catch (SQLException e){
                System.out.println("Class: DbTimeTablePresets. Action: Connection Failed. Entry not Deleted. ");
                log.error("Class: DbTimeTablePresets. Action: Connection Failed. Entry not Deleted. ");   
                log.error("\nDetail Error: " + e);
            }
        }
        
     //Return Entry by Id
        @Override
        public DbTimeTablePresets GetEntrybyId(int id) throws SQLException {
            log.info("Class: DbTimeTablePresets. Action: Return Entry by Id operation triggered. ");
            
            //Create new class instance
            DbTimeTablePresets dataStore = new DbTimeTablePresets();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use id to locate entry
                String selectSQL = "SELECT PresetId, PresetName, PresetCategory, PresetColor "
                        + "FROM timetablepresets "
                        + "WHERE PresetId = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setInt(1, id);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setPresetId(rset.getInt("PresetId"));
                    dataStore.setPresetName(rset.getString("PresetName"));
                    dataStore.setPresetCategory(rset.getString("PresetCategory")); 
                    dataStore.setPresetColor(rset.getString("PresetColor"));
                    
                    System.out.println("Class: DbTimeTablePresets. Action: Entry returned.");
                    log.info("Class: DbTimeTablePresets. Action: Entry returned.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }else {
                    System.out.println("Class: DbTimeTablePresets. Action: Entry not found.");
                    log.error("Class: DbTimeTablePresets. Action: Entry not found.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }
            catch (SQLException e) {
                System.out.println("Class: DbTimeTablePresets. Action: Connection Failed. No Entry loaded or Found.");
                log.error("Class: DbTimeTablePresets. Action: Connection Failed. No Entry loaded or Found.");
                log.error("\nDetail Error: " + e);
            }
            
            return dataStore;
        }   
        
        //Return Entry by PresetName
        public DbTimeTablePresets GetEntrybyPresetName(String presetName) throws SQLException {
            log.info("Class: DbTimeTablePresets. Action: Return Entry by PresetName operation triggered. ");
            
            //Create new class instance
            DbTimeTablePresets dataStore = new DbTimeTablePresets();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use name to locate entry
                String selectSQL = "SELECT PresetId, PresetName, PresetCategory, PresetColor "
                        + "FROM timetablepresets"
                        + "WHERE PresetName = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setString(1, presetName);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setPresetId(rset.getInt("PresetId"));
                    dataStore.setPresetName(rset.getString("PresetName"));
                    dataStore.setPresetCategory(rset.getString("PresetCategory")); 
                    dataStore.setPresetColor(rset.getString("PresetColor"));  
            
                    System.out.println("Class: DbTimeTablePresets. Action: Entry returned.");
                    log.info("Class: DbTimeTablePresets. Action: Entry returned.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }else {
                    System.out.println("Class: DbTimeTablePresets. Action: Entry not found.");
                    log.error("Class: DbTimeTablePresets. Action: Entry not found.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }
            catch (SQLException e) {
                System.out.println("Class: DbTimeTablePresets. Action: Connection Failed. No Entry loaded or Found.");
                log.error("Class: DbTimeTablePresets. Action: Connection Failed. No Entry loaded or Found.");
                log.error("\nDetail Error: " + e);
            }
            
            return dataStore;
        }   
        
    //Return All Presets
        @Override
        public ArrayList<DbTimeTablePresets> GetAllEntries() throws SQLException {
            log.info("Class: DbTimeTablePresets. Action: Return all Entry operation triggered. ");
            
            //Create new class instance
            DbTimeTablePresets dataStore = new DbTimeTablePresets();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Return all entries
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM timetablepresets";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
                    //get info from db to a variable 
                    dataStore.setPresetId(rset.getInt("PresetId"));
                    dataStore.setPresetName(rset.getString("PresetName"));
                    dataStore.setPresetCategory(rset.getString("PresetCategory")); 
                    dataStore.setPresetColor(rset.getString("PresetColor"));
    
                    // Save entries 
                    presetList.add(dataStore);
                }
                
                System.out.println("Class: DbTimeTablePresets. Action: EntryList returned.");
                log.info("Class: DbTimeTablePresets. Action: EntryList returned.");
                con.close(); //Close Connection
                log.info("Connection closed");
            }
            catch (SQLException e) {
                System.out.println("Class: DbTimeTablePresets. Action: Connection Failed. No Entry loaded or Found.");
                log.error("Class: DbTimeTablePresets. Action: Connection Failed. No Entry loaded or Found.");
                log.error("\nDetail Error: " + e);
            }
            
            return presetList;
        }

    //Print all Entries
        @Override
        public void ViewAllEntryPrint() throws SQLException {
            log.info("Class: DbTimeTablePresets. Action: Print all Entry usign Console operation triggered. ");
            try {
                 //database connection 
                Connection con = Connection();
                
                // Variables
                int _presetId = 0;
                String _presetName = null;
                String _presetCategory = null;
                String _presetColor = null;
                
                
                // View all entries using the console
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM timetablepresets";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
          
                    //get info from db to a variable 
                    _presetId = rset.getInt("PresetId");
                    _presetName = rset.getString("PresetName");
                    _presetCategory = rset.getString("PresetCategory");
                    _presetColor  = rset.getString("PresetColor");
    
                    //print info 
                    System.out.println("\n\nPreset Entry Id: " + _presetId);
                    System.out.println("PresetName : " + _presetName );
                    System.out.println("PresetCategory : " + _presetCategory);
                    System.out.println("PresetColor : " + _presetColor);
                    System.out.println("+++++++++++++++++++++"); 
                }
                
                log.info("Class: DbTimeTablePresets. Action: EntryList printed using console.");
                con.close(); //Close Connection
                log.info("Connection closed");
            }
            catch (SQLException e) {
                System.out.println("Class: DbTimeTablePresets. Action: Connection Failed. No Entry loaded or Found.");
                log.error("Class: DbTimeTablePresets. Action: Connection Failed. No Entry loaded or Found.");
                log.error("\nDetail Error: " + e);
            }
        }
}
