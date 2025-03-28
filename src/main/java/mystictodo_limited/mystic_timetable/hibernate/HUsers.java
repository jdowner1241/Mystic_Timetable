/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jamario_Downer
 */

@Entity
@Table(name = "users")
public class HUsers {
//Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    

public HUsers(){}
  

//Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int userId;
  
@Column(name = "UserName", nullable = false, unique = true)
private String userName;
  
@Column(name = "EmailAddress", nullable = false, unique = true)
private String emailAddress;
  
@Column(name = "Password", nullable = false)
private String password;
  
@Temporal(TemporalType.TIMESTAMP)
@Column(name = "RegistrationDate", nullable = false)
private Date registrationDate; 

@OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
private List<HFolderPerUser> folderPerUserList = new ArrayList<>();

@OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
private List<HLoginInfo> loginInfoList = new ArrayList<>();

@OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
private List<HLogEntry> logEntryList = new ArrayList<>();
    
    
//Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
   
    public List<HFolderPerUser> getFolderPerUserList() {
        return folderPerUserList;
    }
    public void setFolderPerUserList() {
                //Method not required 
                
        HFolderPerUserDAOImpl folderPerUserDAO = new HFolderPerUserDAOImpl();
        List<HFolderPerUser> folderPerUsers = folderPerUserDAO.findAll();
        
        for (HFolderPerUser folderPerUser : folderPerUsers){
            if(folderPerUser.getUserId() == this.userId){
                folderPerUserList.add(folderPerUser);
            }
        }
    }

    public List<HLoginInfo> getLoginInfoList() {
        return loginInfoList;
    }
    public void setLoginInfoList() {
                //Method not required 
                
        HLoginInfoDAOImpl loginInfoDAO = new HLoginInfoDAOImpl();
        List<HLoginInfo> loginInfos = loginInfoDAO.findAll();
        
        for (HLoginInfo loginInfo : loginInfos){
            if(loginInfo.getUserId() == this.userId){
                loginInfoList.add(loginInfo);
            }
        }
    }
    

    public List<HLogEntry> getLogEntryList() {
        return logEntryList;
    }
    public void setLogEntryList() {
        //Method not required 
        
        HLogEntryDAOImpl logEntryDAO = new HLogEntryDAOImpl();
        List<HLogEntry> logEntries = logEntryDAO.findAll();
        
        for (HLogEntry logEntry : logEntries){
            if(logEntry.getUserId() == this.userId){
                logEntryList.add(logEntry);
            }
        }
    }
    
    
//Method >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        

    
  
}
