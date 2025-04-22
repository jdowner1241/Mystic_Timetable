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
@Table(name = "frequencytype")
public class HFrequencyType {
    //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    

  public HFrequencyType(){
  }

//Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int fTId;
 
 @Column(name = "FTName", nullable = false)
 private String fTName;   
 
 @OneToMany(mappedBy = "frequencytype", fetch = FetchType.EAGER)
private List<HTimetable> timetableList = new ArrayList<>();
    
//Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
    
 
    public int getFTId() {
        return fTId;
    }

    public void setFTId(int fTId) {
        this.fTId = fTId;
    }

    public String getFTName() {
        return fTName;
    }

    public void setFTName(String fTName) {
        this.fTName = fTName;
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
//        if (folderPerUserList == null) {
//            folderPerUserList = new ArrayList<>();
//        }
//
//        for (HFolderPerUser folderPerUser : folderPerUsers){
//            if(folderPerUser.getFolderId() == this.fTId){
//                folderPerUserList.add(folderPerUser);
//            }
//        }
//    }
 
//Method >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        


}
