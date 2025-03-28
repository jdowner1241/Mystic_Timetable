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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "folderperuser")
public class HFolderPerUser {
 
//Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
 public HFolderPerUser (){}

//Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int folderPerUserId;
 
 @Column(name = "UserId", nullable = false, insertable = false, updatable = false)
 private int userId;
 
 @ManyToOne
 @JoinColumn(name = "UserId", nullable = false)
 private HUsers users;
 
 @Column(name = "FolderId", nullable = false, insertable = false, updatable = false)
 private int folderId;
 
 @ManyToOne
 @JoinColumn(name = "FolderId", nullable = false)
 private HFolder folder;
 
 @Column(name = "FolderNumberPerUser", nullable = false)
 private int folderNumberPerUser;   
 
 @OneToMany(mappedBy = "folderperuser", fetch = FetchType.EAGER)
private List<HTimetableLinker> timetableLinkerList = new ArrayList<>();
    
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

     public HUsers getUsers() {
        return users;
    }

    public void setUsers(HUsers users) {
        this.users = users;
        if (users != null){
            this.userId = users.getUserId();// returns the userid if not null
        }
    }
    
    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public HFolder getFolder() {
        return folder;
    }

    public void setFolder(HFolder folder) {
        this.folder = folder;
        if(folder != null){
            this.folderId = folder.getFolderId(); //returns the folderId if not null
        }
    }

    public int getFolderNumberPerUser() {
        return folderNumberPerUser;
    }

    public void setFolderNumberPerUser(int folderNumberPerUser) {
        this.folderNumberPerUser = folderNumberPerUser;
    }
    
    public List<HTimetableLinker> getTimetableLinkerList() {
        return timetableLinkerList;
    }

    public void setTimetableLinkerList() {
        HTimetableLinkerDAOImpl timetableLinkerDAO = new HTimetableLinkerDAOImpl();
        List<HTimetableLinker> timetableLinkers = timetableLinkerDAO.findAll();
        
        for (HTimetableLinker timetableLinker : timetableLinkers){
            if(timetableLinker.getUserAndFolderId() == this.folderPerUserId){
                timetableLinkerList.add(timetableLinker);
            }
        }
    }
    
 
//Method >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        


}
