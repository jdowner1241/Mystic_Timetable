/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.hibernate;

import java.sql.Time;
import java.time.LocalDate;
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
@Table(name = "timetable")
public class HTimetable {
 
//Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    


//Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int timetableId;
 
 @Column(name = "EventName", nullable = false)
 private String eventName;
 
 @Column(name = "EventCategory", nullable = true)
 private String eventCategory;
 
 @Column(name = "Color", nullable = true)
 private String color;
 
 @Column(name = "Frequency", nullable = false)
 private int frequency;
 
 @Column(name = "HasNotification", nullable = false)
 private boolean hasNotification;
 
 @Temporal(TemporalType.DATE)
 @Column(name = "Day", nullable = false)
 private Date day;
 
 @Temporal(TemporalType.TIMESTAMP)
 @Column(name = "EventStart", nullable = false)
 private Date eventStart;
 
 @Temporal(TemporalType.TIMESTAMP)
 @Column(name = "EventEnd", nullable = false)
 private Date eventEnd;
 
//Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
  
 public int getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(int timetableId) {
        this.timetableId = timetableId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public boolean isHasNotification() {
        return hasNotification;
    }

    public void setHasNotification(boolean hasNotification) {
        this.hasNotification = hasNotification;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public Date getEventStart() {
        return eventStart;
    }

    public void setEventStart(Date eventStart) {
        this.eventStart = eventStart;
    }

    public Date getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(Date eventEnd) {
        this.eventEnd = eventEnd;
    }
 
//Method >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        

    
    
    
}
