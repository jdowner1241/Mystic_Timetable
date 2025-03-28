/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.hibernate;

import java.time.LocalDateTime;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 *
 * @author Jamario_Downer
 */
@Entity
@Table(name = "logininfo")
public class HLoginInfo {
 
//Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    


//Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int loginInfoId;
 
 @Column(name = "UserId", nullable = false)
 private int userId;
 
 @Column(name = "LoginResult", nullable = false)
 private boolean loginResult;
 
 @Column(name = "SuccessLoginCount", nullable = false)
 private int successLoginCount;
 
 @Column(name = "FailedLoginCount", nullable = false)
 private int failedLoginCount;
 
 @Temporal(TemporalType.TIMESTAMP)
 @Column(name = "LastLogin")
 private Date lastLogin;   
    
//Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
    
 public int getLoginInfoId() {
        return loginInfoId;
    }

    public void setLoginInfoId(int loginInfoId) {
        this.loginInfoId = loginInfoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isLoginResult() {
        return loginResult;
    }

    public void setLoginResult(boolean loginResult) {
        this.loginResult = loginResult;
    }

    public int getSuccessLoginCount() {
        return successLoginCount;
    }

    public void setSuccessLoginCount(int successLoginCount) {
        this.successLoginCount = successLoginCount;
    }

    public int getFailedLoginCount() {
        return failedLoginCount;
    }

    public void setFailedLoginCount(int failedLoginCount) {
        this.failedLoginCount = failedLoginCount;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
    
 
//Method >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        

    
    
}
