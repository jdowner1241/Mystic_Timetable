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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;

/**
 *
 * @author Jamario_Downer
 */
@Entity
@Table(name = "folderperuser")
public class HFolderPerUser {
 
//Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    


//Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int folderPerUserId;
 
 @Column(name = "UserId", nullable = false)
 private int userId;
 
 @Column(name = "FolderId", nullable = false)
 private int folderId;
 
 @Column(name = "FolderNumberPerUser", nullable = false)
 private int folderNumberPerUser;   
    
//Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
  
 
    public int getFolderPerUserId() {
        return folderPerUserId;
    }

    public void setFolderPerUserId(int folderPerUserId) {
        this.folderPerUserId = folderPerUserId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public int getFolderNumberPerUser() {
        return folderNumberPerUser;
    }

    public void setFolderNumberPerUser(int folderNumberPerUser) {
        this.folderNumberPerUser = folderNumberPerUser;
    }
    
 
//Method >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        

    
}
