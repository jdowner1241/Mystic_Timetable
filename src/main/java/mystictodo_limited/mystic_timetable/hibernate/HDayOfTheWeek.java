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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jamario_Downer
 */

@Entity
@Table(name = "dayoftheweek")
public class HDayOfTheWeek {
  //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    

  public HDayOfTheWeek(){
  }

//Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int dayId;
 
 @Column(name = "DayName", nullable = false)
 private String dayName;   
 
 @OneToMany(mappedBy = "dayoftheweek", fetch = FetchType.EAGER)
private List<HTimetable> timetableList = new ArrayList<>();
    
//Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
    
 
    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }
    
    public List<HTimetable> getTimetableList() {
        return timetableList;
    }

//    public void setFolderPerUserList() {
//        //This method is not required anymore
//       
//        HFolderPerUserDAOImpl folderPerUserDAO = new HFolderPerUserDAOImpl();
//        List<HFolderPerUser> folderPerUsers = folderPerUserDAO.findAll();
//        
//        // Ensure folderPerUserList is initialized
//        if (timetableList == null) {
//            timetableList = new ArrayList<>();
//        }
//
//        for (HFolderPerUser folderPerUser : folderPerUsers){
//            if(folderPerUser.getFolderId() == this.dayId){
//                timetableList.add(folderPerUser);
//            }
//        }
//    }
 
//Method >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        
  
    
    
    
}
