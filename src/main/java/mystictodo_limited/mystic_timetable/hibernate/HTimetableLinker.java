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

/**
 *
 * @author Jamario_Downer
 */

@Entity
@Table(name = "timetablelinker")
public class HTimetableLinker {
 
//Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    


//Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int timetableLinkerId;
 
 @Column(name = "EventId", nullable = false)
 private int eventId;
 
 @Column(name = "UserAndFolderId", nullable = false)
 private int userAndFolderId;
 
 
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

    public int getUserAndFolderId() {
        return userAndFolderId;
    }

    public void setUserAndFolderId(int userAndFolderId) {
        this.userAndFolderId = userAndFolderId;
    }
 
//Method >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        

    
    
    
}
