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
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Jamario_Downer
 */
@Entity
@Table(name = "timetable")
public class HTimetable {
 
//Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
  public HTimetable(){}

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
 
 @Column(name = "FrequencyType", nullable = false, insertable = false, updatable = false)
 private int frequencyType;
 
 @Column(name = "FrequencyAmount", nullable = false)
 private int frequencyAmount;
 
 @Column(name = "HasNotification", nullable = false)
 private boolean hasNotification;
 

 @Column(name = "Day", nullable = false, insertable = false, updatable = false)
 private int day;
 
 @Temporal(TemporalType.TIMESTAMP)
 @Column(name = "EventStart", nullable = false)
 private Date eventStart;
 
 @Temporal(TemporalType.TIMESTAMP)
 @Column(name = "EventEnd", nullable = false)
 private Date eventEnd;
 
@OneToMany(mappedBy = "timetable", fetch = FetchType.EAGER)
private List<HTimetableLinker> timetableLinkerList = new ArrayList<>();

 @ManyToOne
 @JoinColumn(name = "Day", nullable = false)
 private HDayOfTheWeek dayoftheweek;
 
 @ManyToOne
 @JoinColumn(name = "FrequencyType", nullable = false)
 private HFrequencyType frequencytype;
 
 
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

    public int getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(int frequencyType) {
        this.frequencyType = frequencyType;
    }

    public int getFrequencyAmount() {
        return frequencyAmount;
    }

    public void setFrequencyAmount(int frequencyAmount) {
        this.frequencyAmount = frequencyAmount;
    }
    
    public boolean isHasNotification() {
        return hasNotification;
    }

    public void setHasNotification(boolean hasNotification) {
        this.hasNotification = hasNotification;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
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
    
//    public List<HTimetableLinker> getTimetableLinkerList() {
//        return timetableLinkerList;
//    }
//
//    public void setTimetableLinkerList() {
//        //Method not required 
//        
//        HTimetableLinkerDAOImpl timetableLinkerDAO = new HTimetableLinkerDAOImpl();
//        List<HTimetableLinker> timetableLinkers = timetableLinkerDAO.findAll();
//        
//        for (HTimetableLinker timetableLinker : timetableLinkers){
//            if(timetableLinker.getEventId() == this.timetableId){
//                timetableLinkerList.add(timetableLinker);
//            }
//        }
//    }
 
//Method >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        


    
    
    
}
