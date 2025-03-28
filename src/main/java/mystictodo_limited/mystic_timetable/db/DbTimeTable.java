/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.db;
import java.sql.*;
import java.time.LocalDate;
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
public class DbTimeTable extends DbConnectionManager implements DbService<DbTimeTable> {
 //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
 public DbTimeTable(){
     super(DbTimeTable.class);
     CreateLog("info", "Default Constructor Triggered.", null);  
 }
    
 //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>     

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
    public void InsertEntry(String eventName, String eventCategory, String color, int frequency, boolean hasNotification,
                                LocalDate day, LocalDateTime eventStart, LocalDateTime eventEnd ) throws SQLException{
        CreateLog("info", "Insert Entry Operation Triggered.", null);   
        
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
    public void UpdateEntrybyId(int id, String eventName, String eventCategory, String color, int frequency, boolean hasNotification,
                                LocalDate day, LocalDateTime eventStart, LocalDateTime eventEnd) throws SQLException{
        CreateLog("info", "Update Entry by Id operation triggered.", null);  
        
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
                    // Perform Action if not empty
                    CreateLog("info", "Entry Updated.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null);
                }
            }else
            {
                // Data not saved due to validation
                CreateLog("error", "Validation Failed. Entry Not Updated.", null);  
                con.close(); //Close Connectionr
                CreateLog("info", "Connection closed.", null); 
            }  
        }
        catch(SQLException e) {  
            CreateLog("error", "Connection Failed. Entry Not updated.", e);
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
                String deleteSQL = "DELETE FROM timetable WHERE TimetableId = ?";
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
        public DbTimeTable GetEntrybyId(int id) throws SQLException {
            CreateLog("info", "Return Entry by Id operation triggered.", null); 
            
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
                    
                    CreateLog("info", "Entry returned.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null); 
                    
                }else {
                    CreateLog("error", "Validation Failed. Entry not found.", null);  
                    con.close(); //Close Connectionr
                    CreateLog("info", "Connection closed.", null); 
                    
                }
            }
            catch (SQLException e) {               
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return dataStore;
        }   
        
        //Return Entry by EventName
        public DbTimeTable GetEntrybyEventName(String eventName) throws SQLException {
            CreateLog("info", "Return Entry by EventName operation triggered.", null); 
            
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
                    
                    CreateLog("info", "Entry returned.", null);
                    con.close(); //Close Connection
                    CreateLog("info", "Connection closed.", null); 
                }else {
                    CreateLog("error", "Validation Failed. Entry not found.", null);  
                    con.close(); //Close Connectionr
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
        public ArrayList<DbTimeTable> GetAllEntries() throws SQLException {
            CreateLog("info", "Return all Entry operation triggered.", null); 
           
            
            try {
                 //database connection 
                Connection con = Connection();
                
                // Return all entries
                Statement stmTest = con.createStatement();
                String SQL = "SELECT * FROM timetable";
                ResultSet rset = stmTest.executeQuery(SQL);
                
                while(rset.next())
                {
                     //Create new class instance
                     DbTimeTable dataStore = new DbTimeTable();
                    
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
    
                     // Save entry elements to datastore
                    timetableList.add(dataStore);
                }
                
                CreateLog("info", "EntryList returned.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null); 
            }
            catch (SQLException e) {
                CreateLog("error", "Connection Failed. No Entry loaded or Found.", e);
            }
            
            return timetableList;
        }

    //Print all Entries
        @Override
        public void ViewAllEntryPrint() throws SQLException {
            CreateLog("info", "Print all Entry using Console operation triggered.", null);
            
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
                
                CreateLog("info", "EntryList printed using console.", null);
                con.close(); //Close Connection
                CreateLog("info", "Connection closed.", null); 
            }
            catch (SQLException e) {  
               CreateLog("error", "Connection Failed. No Entry loaded or Found.", e); 
            }
        }
}
