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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Jamario_Downer
 */
@Entity
@Table(name = "folder")
public class HFolder {
    
//Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    

  public HFolder(){
  }

//Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int folderId;
 
 @Column(name = "FolderName", nullable = false)
 private String folderName;   
 
 @OneToMany(mappedBy = "folder", fetch = FetchType.EAGER)
private List<HFolderPerUser> folderPerUserList = new ArrayList<>();
    
//Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
    
 
    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
    
    public List<HFolderPerUser> getFolderPerUserList() {
        return folderPerUserList;
    }

    public void setFolderPerUserList() {
        //This method is not required anymore
       
        HFolderPerUserDAOImpl folderPerUserDAO = new HFolderPerUserDAOImpl();
        List<HFolderPerUser> folderPerUsers = folderPerUserDAO.findAll();
        
        // Ensure folderPerUserList is initialized
        if (folderPerUserList == null) {
            folderPerUserList = new ArrayList<>();
        }

        for (HFolderPerUser folderPerUser : folderPerUsers){
            if(folderPerUser.getFolderId() == this.folderId){
                folderPerUserList.add(folderPerUser);
            }
        }
    }
 
//Method >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        


}
