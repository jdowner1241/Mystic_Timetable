/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mytictodo_limited.mystic_timetable.db;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import javax.swing.RowFilter.Entry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 *
 * @author Jamario_Downer
 */
public class dbConnectionManager {
    //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>   
    public dbConnectionManager (){
        log.info("Default Constructor Triggered.");
    }
  /*  public dbConnectionManager (String firstName, String lastName, String gender, String streetAddress, String parish, 
            String country, String phoneNumber, String emailAddress )
    {
        log.info("Parameterized Constructor Triggered.");
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.streetAddress = streetAddress;
        this.parish = parish;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress; 
        log.info("Parameterized Constructor Complete. Fields updated. ");
    } */
    
 //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
   /* private String firstName;
    private String lastName;
    private String gender;
    private String streetAddress;
    private String parish;
    private String country;
    private String phoneNumber;
    private String emailAddress;
    */
    
    private static final Logger log = LogManager.getLogger(dbConnectionManager.class);
 //Getter/Setter >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
     /*   
    // FirstName 
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName; 
    }
    
    // LastName
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName; 
    }
    
    // Gender
    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
        this.gender = gender; 
    }
    
    // StreetAddress
    public String getStreetAddress(){
        return streetAddress;
    }
    public void setStreetAddress(String streetAddress){
        this.streetAddress = streetAddress; 
    }
    
    // Parish
    public String getParish(){
        return parish;
    }
    public void setParish(String parish){
        this.parish = parish; 
    }
    
    // Country
    public String getCountry(){
        return country;
    }
    public void setCountry(String country){
        this.country = country; 
    }
    
    // PhoneNumber
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber; 
    }
    
    // EmailAddress
    public String getEmailAddress(){
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress; 
    }
    */
    
 //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
   
    // Return Connection 
    public static Connection Connection() {
        log.info("Connection method triggered.");
        Connection con = null;
        try{
            // connection info
            final String url = "jdbc:mysql://localhost:3306/mystic_timetable?zeroDateTimeBehavior=CONVERT_TO_NULL";
            final String user="root";
            final String password="Blade1241$";
            
           // Load the MySQL JDBC Driver
           Class.forName("com.mysql.cj.jdbc.Driver");
           log.info("Connection Driver loaded.");
           
           //Establish a connection
           con = DriverManager.getConnection(url, user, password);
           log.info("Connection Established.");
        }
        catch (ClassNotFoundException e){
           System.out.println("JDBC Not Found");
           
        }
        
        catch (SQLException e){
            System.out.println("Connection Failed");
            log.error("Connection Failed.");
            log.debug("SQLException Error : " + e);
        }
    
        log.info("Connection returned to variable con.");
        return con;
    }
    
    
    
    //Insert User Entry 
    public void InsertUserEntry(String userName, String emailAddress, String password) throws SQLException{
        log.info("InsertUserEntry Operation Triggered.");
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            String _userName = userName;
            String _emailAddress = emailAddress;
            String _password = password;
            LocalDateTime registrationDate = LocalDateTime.now();
            String _registrationDate = registrationDate.toString();
            
            boolean isValid = true;
            
           if (isValid == true) 
           {
               //insert to database
                String insertSQL = "INSERT INTO users "
                        + "(UserName, EmailAdderss, Password, RegistrationDate) "
                        + "VALUE (?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(insertSQL);
                ps.setString(1, _userName);
                ps.setString(2, _emailAddress);
                ps.setString(3, _password);
                ps.setString(4, _registrationDate);
            
                /*String tempDebug = "Debug: Current Entry info : " + "Name : " + clientInfo.firstName + " " + clientInfo.lastName 
                            + " , Gender : " + clientInfo.gender 
                            + ", Address : " + clientInfo.streetAddress + ", " + clientInfo.parish +  ;
                log.debug( tempDebug);
                */
            
                int rowsInserted = ps.executeUpdate();
            
                if(rowsInserted > 0) {
                    System.out.println("User Entry Inserted.");
                    log.info("User Entry Inserted.");
                
                    //Close Connection
                    con.close();
                   log.info("Connection closed");
                }
           } else 
           {
             // Data not saved due to validation
             System.out.println("Validation Failed. User Entry Not Added.");
             log.info("Validation Failed. User Entry Not Added.");
             con.close(); //Close Connection
             log.info("Connection closed");
           }
        }
        catch(SQLException e) {
            System.out.println("Connection Failed. User Entry Not Added");
            log.error("Connection Failed. User Entry Not Added");
        }
    }
    
    //Update User Entry
    public void UpdateUserEntry(int id, String userName, String emailAddress, String password) throws SQLException{
        log.info("UpdateUserEntry operation triggerede. ");
        try{
             //database connection 
            Connection con = Connection();
            
            //Variables and validation
            String _userName = userName;
            String _emailAddress = emailAddress;
            String _password = password;
            //String _registrationDate = registrationDate.toString();
            
            boolean isValid = true;
            
            if (isValid == true)
            {
                // update the entry
                String updateSQL = "UPDATE users "
                        + "SET UserName = ? EmailAdderss = ? Password = ?"
                        + "WHERE UserId = ?";
                PreparedStatement psUpdate  = con.prepareStatement(updateSQL);
                psUpdate.setString(1, _userName);
                psUpdate.setString(2, _emailAddress);
                psUpdate.setString(3, _password);
                psUpdate.setInt(4, id);
                int rowsUpdated = psUpdate.executeUpdate();
                
                if(rowsUpdated > 0) {
                    System.out.println("User Entry Updated.");
                    log.info("User Entry Updated.");
                    con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }else
            {
                 // Data not saved due to validation
                System.out.println("Validation Failed. User Entry Not Updated.");
                log.info("Validation Failed. User Entry Not Updated.");
                con.close(); //Close Connection
                log.info("Connection closed");
            }  
        }
        catch(SQLException e) {
            System.out.println("Connection Failed. User Entry not updated.");
            log.error("Connection Failed. User Entry not updated.");
        }
    }
    
    // Delete User Entry
        public void DeleteUserEntry(int id) throws SQLException {
            log.info("DeleteUserEntry operation trigger.");
            try{
                 //database connection 
                Connection con = Connection();
                
                //Delete entry from database 
                String deleteSQL = "DELETE FROM users WHERE UserId = ?";
                PreparedStatement psDelete = con.prepareStatement(deleteSQL);
                psDelete.setInt(1, id);
                int rowsDeleted = psDelete.executeUpdate();
                
                if(rowsDeleted > 0){
                    System.out.println("User Entry Deleted.");
                    log.info("User Entry Deleted.");
                     con.close(); //Close Connection
                    log.info("Connection closed");
                }
            }
            catch (SQLException e){
                System.out.println("Connection Failed. User Entry not Deleted. ");
                log.error("Connection Failed. User Entry not Deleted. ");   
            }
        }
    
       //View User Entry using print Statment 
        public static void ViewAllUserEntryPrint() throws SQLException {
            log.info("View all User entries print operation triggered. ");
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
                
                //Close Connection
                con.close(); 
                log.info("Connection closed");
            }
            catch (SQLException e) {
                System.out.println("Connection Failed. No Entry loaded or Found.");
                log.error("Connection Failed. No Entry loaded or Found.");
            }
        }
    
    public static void ShowLog() {
    
    }
}
