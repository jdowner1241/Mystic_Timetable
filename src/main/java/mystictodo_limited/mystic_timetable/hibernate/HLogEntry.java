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
 
 @Column(name = "LogType", nullable = false)
 private String logType;
 
 
 @Column(name = "LogLevelId", nullable = false, insertable = false, updatable = false )
 private int logLevelId;
 
 @ManyToOne
 @JoinColumn(name = "LogLevelId", nullable = false)
 private HLogLevel loglevel;
 
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
 
    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }
    
      public HLogLevel getLoglevel() {
        return loglevel;
    }

    public void setLoglevel(HLogLevel loglevel) {
        this.loglevel = loglevel;
        if(loglevel != null){
            this.logLevelId = loglevel.getLogLevelId();
        }
    }
    
    public int getLogLevelId() {
        return logLevelId;
    }

    public void setLogLevelId(int logLevelId) {
        this.logLevelId = logLevelId;
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
