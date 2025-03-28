/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.hibernate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Jamario_Downer
 */
@Entity
@Table(name = "logentry")
public class HLogEntry {
 
//Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
  public HLogEntry(){};
    

//Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int logId;
 
 @Column(name = "UserId", nullable = false, insertable = false, updatable = false)
 private int userId;
 
 @ManyToOne
 @JoinColumn(name = "UserId", nullable = false)
 private HUsers users;
 
 @Column(name = "LogTypeId", nullable = false, insertable = false, updatable = false)
 private int logTypeId;
 
 @ManyToOne
 @JoinColumn(name = "LogTypeId", nullable = false)
 private HLogType logtype;
 
 @Column(name = "LogLevel", nullable = false )
 private String logLevel;
 
 @Column(name = "Message", nullable = false)
 private String message;
 
 @Temporal(TemporalType.TIMESTAMP)
 @Column(name = "TimeStamp", nullable = false)
 private Date timeStamp;
 
//Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

      public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

     public HUsers getUsers() {
        return users;
    }

    public void setUsers(HUsers users) {
        this.users = users;
        if (users != null){
            this.userId = users.getUserId();// returns the userid if not null
        }
    }
 
    public int getLogTypeId() {
        return logTypeId;
    }

    public void setLogTypeId(int logTypeId) {
        this.logTypeId = logTypeId;
    }
    
      public HLogType getLogtype() {
        return logtype;
    }

    public void setLogtype(HLogType logtype) {
        this.logtype = logtype;
        if(logtype != null){
            this.logTypeId = logtype.getLogTypeId();
        }
    }
    
    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
 
 
//Method >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        

   
}
