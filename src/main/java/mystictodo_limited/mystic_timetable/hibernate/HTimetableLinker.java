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

/**
 *
 * @author Jamario_Downer
 */

@Entity
@Table(name = "timetablelinker")
public class HTimetableLinker {
 
//Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
 public HTimetableLinker(){}
    

//Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int timetableLinkerId;
 
 @Column(name = "EventId", nullable = false, insertable = false, updatable = false)
 private int eventId;
 
 @ManyToOne
 @JoinColumn(name = "EventId", nullable = true)
 private HTimetable timetable;
 
 @Column(name = "UserAndFolderId", nullable = false, insertable = false, updatable = false)
 private int userAndFolderId;
 
 @ManyToOne
 @JoinColumn(name = "UserAndFolderId", nullable = false)
 private HFolderPerUser folderperuser;

 
//Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
  
 public int getTimetableLinkerId() {
        return timetableLinkerId;
    }

    public void setTimetableLinkerId(int timetableLinkerId) {
        this.timetableLinkerId = timetableLinkerId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public HTimetable getTimetable() {
        return timetable;
    }

    public void setTimetable(HTimetable timetable) {
        this.timetable = timetable;
        if(timetable != null){
            this.eventId = timetable.getTimetableId();
        }
    }
    
    public int getUserAndFolderId() {
        return userAndFolderId;
    }

    public void setUserAndFolderId(int userAndFolderId) {
        this.userAndFolderId = userAndFolderId;
    }
    
    public HFolderPerUser getFolderperuser() {
        return folderperuser;
    }

    public void setFolderperuser(HFolderPerUser folderperuser) {
        this.folderperuser = folderperuser;
        if(folderperuser != null){
            this.userAndFolderId = folderperuser.getFolderPerUserId();
        }
    }
 
//Method >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        

    
    
}
