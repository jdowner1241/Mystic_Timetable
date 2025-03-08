/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mytictodo_limited.mystic_timetable.db;
import java.sql.*;
import java.time.LocalDate;
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
public class DbTimeTable extends DbConnectionManager {
 //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
 public DbTimeTable(){
     log.info("Class: DbTimeTable. Action: Default Constructor Triggered.");
     //Connection con = Connection();
 }
    
 //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     
 //private Connection con; 
 
 private int timetableId;
 private String eventName;
 private String eventCategory;
 private String color;
 private int frequency;
 private boolean hasNotification;
 private LocalDate day;
 private LocalDateTime eventStart;
 private LocalDateTime eventEnd;
 
 private ArrayList<DbTimeTable> timetableList;
 
 private static final Logger log = LogManager.getLogger(DbTimeTable.class);
 //Getter/Setter >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 //TimetableId
 public int getTimetableId(){
     return timetableId;
 }
 public void setTimetableId(int timetableId){
     this.timetableId = timetableId;
 }
 
 //EventName
 public String getEventName(){
     return eventName;
 }
 public void setEventName(String eventName){
     this.eventName = eventName;
 }
 
 //EventCategory
 public String getEventCategory(){
     return eventCategory;
 }
 public void setEventCategory(String eventCategory){
     this.eventCategory = eventCategory;
 }
 
 //Color
 public String getColor(){
     return color;
 }
 public void setColor(String color){
     this.color = color;
 }
 
  //Frequency
 public int getFrequency(){
     return frequency;
 }
 public void setFrequency(int frequency){
     this.frequency = frequency;
 }
 
 //HasNotification
 public boolean getHasNotification(){
     return hasNotification;
 }
 public void setHasNotification(boolean hasNotification){
     this.hasNotification = hasNotification;
 }
 
 
 // day
 public LocalDate getDay(){
     return day;
 }
 public void setDay(String _day){
     DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE; // yyyy-MM-dd
     day = LocalDate.parse(_day, formatter);
 }
 
  // EventStart
 public LocalDateTime getEventStart(){
     return eventStart;
 }
 public void setEventStart(String _eventStart){
     DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // yyyy-MM-dd-HH-mm-ss-ns
     eventStart = LocalDateTime.parse(_eventStart, formatter);
 }
 
  // EventEnd
 public LocalDateTime getEventEnd(){
     return eventEnd;
 }
 public void setEventEnd(String _eventEnd){
     DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
     eventEnd = LocalDateTime.parse(_eventEnd, formatter);
 }
 
 
 
 
 //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
 //Insert Entry 
    public void UserInsertEntry(String eventName, String eventCategory, String color, int frequency, boolean hasNotification,
                                LocalDate day, LocalDateTime eventStart, LocalDateTime eventEnd ) throws SQLException{
        log.info("Class: DbTimeTable. Action: Insert Entry Operation Triggered.");
        
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            String _eventName = eventName;
            String _eventCategory = eventCategory;
            String _color = color;
            int _frequency = frequency;
            boolean _hasNotification = hasNotification; 
            LocalDate _setday = day;
            String _day = _setday.toString();
            LocalDateTime _setEventStart = eventStart;
            String _eventStart = _setEventStart.toString();
            LocalDateTime _setEventEnd = eventStart;
            String _eventEnd = _setEventEnd.toString();
            
            boolean isValid = true;
            
           if (isValid == true) 
           {
               //insert to database
                String insertSQL = "INSERT INTO timetable "
                        + "(EventName, EventCategory, Color, Frequency, HasNotification, Day, EventStart, EventEnd) "
                        + "VALUE (?,?,?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(insertSQL);
                ps.setString(1, _eventName);
                ps.setString(2, _eventCategory);
                ps.setString(3, _color);
                ps.setInt(4, _frequency);
                ps.setBoolean(5, _hasNotification);
                ps.setString(6, _day);    
                ps.setString(7, _eventStart);  
                ps.setString(8, _eventEnd);  
            
                int rowsInserted = ps.executeUpdate();
            
                if(rowsInserted > 0) {
                    System.out.println("Class: DbTimeTable. Action: Entry Inserted.");
                    log.info("Class: DbTimeTable. Action: Entry Inserted.");
                
                    //Close Connection
                    con.close();
                   log.info("Connection closed");
                }
           } else 
           {
             // Data not saved due to validation
             System.out.println("Class: DbTimeTable. Action: Validation Failed. Entry Not Added.");
             log.error("Class: DbTimeTable. Action: Validation Failed. Entry Not Added.");
             con.close(); //Close Connection
             log.info("Connection closed");
           }
        }
        catch(SQLException e) {
            System.out.println("\nClass: DbTimeTable. Action: Connection Failed. Entry Not Added");
            log.error("Class: DbTimeTable. Action: Connection Failed. Entry Not Added");
            log.error("\nDetail Error: " + e);
        }
    }
    
    //Update Entry
    public void UserUpdateEntrybyId(int id, String eventName, String eventCategory, String color, int frequency, boolean hasNotification,
                                LocalDate day, LocalDateTime eventStart, LocalDateTime eventEnd) throws SQLException{
        log.info("Class: DbTimeTable. Action: Update Entry by Id operation triggered. ");
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            String _eventName = eventName;
            String _eventCategory = eventCategory;
            String _color = color;
            int _frequency = frequency;
            boolean _hasNotification = hasNotification; 
            LocalDate _setday = day;
            String _day = _setday.toString();
            LocalDateTime _setEventStart = eventStart;
            String _eventStart = _setEventStart.toString();
            LocalDateTime _setEventEnd = eventStart;
            String _eventEnd = _setEventEnd.toString();
  
            boolean isValid = true;
            
            if (isValid == true)
            {
                // update the entry
                String updateSQL = "UPDATE timetable "
                        + "SET EventName = ? EventCategory = ? Color = ? Frequency = ? HasNotification = ? Day = ? EventStart = ? EventEnd = ? "
                        + "WHERE TimetableId = ?";
                PreparedStatement psUpdate  = con.prepareStatement(updateSQL);
                psUpdate.setString(1, _eventName);
                psUpdate.setString(2, _eventCategory);
                psUpdate.setString(3, _color);
                psUpdate.setInt(4, _frequency);
                psUpdate.setBoolean(5, _hasNotification);
                psUpdate.setString(6, _day);    
                psUpdate.setString(7, _eventStart);  
                psUpdate.setString(8, _eventEnd);  
                psUpdate.setInt(9, id);
                int rowsUpdated = psUpdate.executeUpdate();
                
                if(rowsUpdated > 0) {
                    System.out.println("Class: DbTimeTable. Action: Entry Updated.");
                    log.info("Class: DbTimeTable. Action: Entry Updated.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }else
            {
                 // Data not saved due to validation
                System.out.println("Class: DbTimeTable. Action: Validation Failed. Entry Not Updated.");
                log.error("Class: DbTimeTable. Action: Validation Failed. Entry Not Updated.");
                con.close(); //Close Connection
                log.info("Connection closed");
            }  
        }
        catch(SQLException e) {
            System.out.println("Class: DbTimeTable. Action: Connection Failed. Entry not updated.");
            log.error("Class: DbTimeTable. Action: Connection Failed. Entry not updated.");
            log.error("\nDetail Error: " + e);
        }
    }
    
    // Delete Entry
        public void UserDeleteEntryById(int id) throws SQLException {
            log.info("Class: DbTimeTable. Action: Delete Entry by Id operation trigger.");
            try{
                 //database connection 
                Connection con = Connection();
                
                //Delete entry from database 
                String deleteSQL = "DELETE FROM timetable WHERE TimetableId = ?";
                PreparedStatement psDelete = con.prepareStatement(deleteSQL);
                psDelete.setInt(1, id);
                int rowsDeleted = psDelete.executeUpdate();
                
                if(rowsDeleted > 0){
                    System.out.println("Class: DbTimeTable. Action: Entry Deleted.");
                    log.info("Class: DbTimeTable. Action: Entry Deleted.");
                     con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }
            catch (SQLException e){
                System.out.println("Class: DbTimeTable. Action: Connection Failed. Entry not Deleted. ");
                log.error("Class: DbTimeTable. Action: Connection Failed. Entry not Deleted. ");   
                log.error("\nDetail Error: " + e);
            }
        }
        
     //Return Entry by Id
        public DbTimeTable UserGetEntrybyId(int id) throws SQLException {
            log.info("Class: DbTimeTable. Action: Return Entry by Id operation triggered. ");
            
            //Create new class instance
            DbTimeTable dataStore = new DbTimeTable();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use id to locate entry
                String selectSQL = "SELECT TimetableId, EventName, EventCategory, Color, Frequency, HasNotification, Day, EventStart, EventEnd "
                        + "FROM timetable"
                        + "WHERE TimetableId = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setInt(1, id);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setTimetableId(rset.getInt("TimetableId"));
                    dataStore.setEventName(rset.getString("EventName"));
                    dataStore.setEventCategory(rset.getString("EventCategory")); 
                    dataStore.setColor(rset.getString("Color"));
                    dataStore.setFrequency(rset.getInt("Frequency"));
                    dataStore.setHasNotification(rset.getBoolean("HasNotification"));
                    dataStore.setDay(rset.getString("Day")); 
                    dataStore.setEventStart(rset.getString("EventStart")); 
                    dataStore.setEventEnd(rset.getString("EventEnd")); 
                    
                    System.out.println("Class: DbTimeTable. Action: Entry returned.");
                    log.info("Class: DbTimeTable. Action: Entry returned.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }else {
                    System.out.println("Class: DbTimeTable. Action: Entry not found.");
                    log.error("Class: DbTimeTable. Action: Entry not found.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }
            catch (SQLException e) {
                System.out.println("Class: DbTimeTable. Action: Connection Failed. No Entry loaded or Found.");
                log.error("Class: DbTimeTable. Action: Connection Failed. No Entry loaded or Found.");
                log.error("\nDetail Error: " + e);
            }
            
            return dataStore;
        }   
        
        //Return Entry by EventName
        public DbTimeTable UserGetEntrybyEventName(String eventName) throws SQLException {
            log.info("Class: DbTimeTable. Action: Return Entry by EventName operation triggered. ");
            
            //Create new class instance
            DbTimeTable dataStore = new DbTimeTable();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // use name to locate entry
                String selectSQL = "SELECT TimetableId, EventName, EventCategory, Color, Frequency, HasNotification, Day, EventStart, EventEnd "
                        + "FROM timetable"
                        + "WHERE EventName = ?";
                PreparedStatement psSelect = con.prepareStatement(selectSQL);
                psSelect.setString(1, eventName);
                ResultSet rset = psSelect.executeQuery();
                
                if (rset.next()){
                    dataStore.setTimetableId(rset.getInt("TimetableId"));
                    dataStore.setEventName(rset.getString("EventName"));
                    dataStore.setEventCategory(rset.getString("EventCategory")); 
                    dataStore.setColor(rset.getString("Color"));
                    dataStore.setFrequency(rset.getInt("Frequency"));
                    dataStore.setHasNotification(rset.getBoolean("HasNotification"));
                    dataStore.setDay(rset.getString("Day")); 
                    dataStore.setEventStart(rset.getString("EventStart")); 
                    dataStore.setEventEnd(rset.getString("EventEnd"));      
            
                    System.out.println("Class: DbTimeTable. Action: Entry returned.");
                    log.info("Class: DbTimeTable. Action: Entry returned.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }else {
                    System.out.println("Class: DbTimeTable. Action: Entry not found.");
                    log.error("Class: DbTimeTable. Action: Entry not found.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }
            catch (SQLException e) {
                System.out.println("Class: DbTimeTable. Action: Connection Failed. No Entry loaded or Found.");
                log.error("Class: DbTimeTable. Action: Connection Failed. No Entry loaded or Found.");
                log.error("\nDetail Error: " + e);
            }
            
            return dataStore;
        }   
        
    //Return All Users
        public ArrayList<DbTimeTable> UserGetAllEntries() throws SQLException {
            log.info("Class: DbTimeTable. Action: Return all Entry operation triggered. ");
            
            //Create new class instance
            DbTimeTable dataStore = new DbTimeTable();
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Return all entries
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM timetable";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
                    //get info from db to a variable 
                    dataStore.setTimetableId(rset.getInt("TimetableId"));
                    dataStore.setEventName(rset.getString("EventName"));
                    dataStore.setEventCategory(rset.getString("EventCategory")); 
                    dataStore.setColor(rset.getString("Color"));
                    dataStore.setFrequency(rset.getInt("Frequency"));
                    dataStore.setHasNotification(rset.getBoolean("HasNotification"));
                    dataStore.setDay(rset.getString("Day")); 
                    dataStore.setEventStart(rset.getString("EventStart")); 
                    dataStore.setEventEnd(rset.getString("EventEnd")); 
    
                    // Save entries 
                    timetableList.add(dataStore);
                }
                
                System.out.println("Class: DbTimeTable. Action: EntryList returned.");
                log.info("Class: DbTimeTable. Action: EntryList returned.");
                con.close(); //Close Connection
                log.info("Connection closed");
            }
            catch (SQLException e) {
                System.out.println("Class: DbTimeTable. Action: Connection Failed. No Entry loaded or Found.");
                log.error("Class: DbTimeTable. Action: Connection Failed. No Entry loaded or Found.");
                log.error("\nDetail Error: " + e);
            }
            
            return timetableList;
        }

    //Print all Entries
        public void UserViewAllEntryPrint() throws SQLException {
            log.info("Class: DbTimeTable. Action: Print all Entry usign Console operation triggered. ");
            try {
                 //database connection 
                Connection con = Connection();
                
                // Variables
                int _timetableId = 0;
                String _eventName = null;
                String _eventCategory = null;
                String _color = null;
                int _frequency = 0;
                boolean _hasNotification = false; 
                String _day = null;
                String _eventStart = null;
                String _eventEnd = null;
                
                
                // View all entries using the console
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM timetable";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
          
                    //get info from db to a variable 
                    _timetableId = rset.getInt("TimetableId");
                    _eventName = rset.getString("EventName");
                    _eventCategory = rset.getString("EventCategory");
                    _color  = rset.getString("Color");
                    _frequency = rset.getInt("Frequency");
                    _hasNotification = rset.getBoolean("HasNotification");
                    _day = rset.getString("Day"); 
                    _eventStart = rset.getString("EventStart"); 
                    _eventEnd = rset.getString("EventEnd"); 
    
                    //print info 
                    System.out.println("\n\nTimetable Entry Id: " + _timetableId);
                    System.out.println("EventName : " + _eventName );
                    System.out.println("EventCategory : " + _eventCategory);
                    System.out.println("Color : " + _color);
                    System.out.println("Frequency : " + _frequency);
                    System.out.println("HasNotification : " + _hasNotification);
                    System.out.println("Day : " + _day); 
                    System.out.println("EventStart : " + _eventStart);
                    System.out.println("EventEnd : " + _eventEnd);
                    System.out.println("+++++++++++++++++++++"); 
                }
                
                log.info("Class: DbTimeTable. Action: EntryList printed using console.");
                con.close(); //Close Connection
                log.info("Connection closed");
            }
            catch (SQLException e) {
                System.out.println("Class: DbTimeTable. Action: Connection Failed. No Entry loaded or Found.");
                log.error("Class: DbTimeTable. Action: Connection Failed. No Entry loaded or Found.");
                log.error("\nDetail Error: " + e);
            }
        }
}
