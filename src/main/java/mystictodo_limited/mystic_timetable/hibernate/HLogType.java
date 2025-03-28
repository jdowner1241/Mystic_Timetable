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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jamario_Downer
 */
@Entity
@Table(name = "logtype")
public class HLogType {
 
//Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    

  public HLogType(){
      //setLogEntryList();
  }

//Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int logTypeId;
 
 @Column(name = "Name", nullable = false)
 private String name;
 
 @Column(name = "Description", nullable = false)
 private String description;
 
 @OneToMany(mappedBy = "logtype", fetch = FetchType.EAGER )
 private List<HLogEntry> logEntryList = new ArrayList<>();
 
//Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
  public int getLogTypeId() {
        return logTypeId;
    }

    public void setLogTypeId(int logTypeId) {
        this.logTypeId = logTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }  
    
    public List<HLogEntry> getLogEntryList() {
        return logEntryList;
    }

    public void setLogEntryList() {
        //Method not required 
        
        HLogEntryDAOImpl logEntryDAO = new HLogEntryDAOImpl();
        List<HLogEntry> logEntries = logEntryDAO.findAll();
        
        for (HLogEntry logEntry : logEntries){
            if(logEntry.getLogTypeId() == this.logTypeId){
                logEntryList.add(logEntry);
            }
        }
    }
 
//Method >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        


    
}
