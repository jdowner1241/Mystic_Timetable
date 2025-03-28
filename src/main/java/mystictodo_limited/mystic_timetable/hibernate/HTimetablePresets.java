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
@Table(name = "timetablepresets")
public class HTimetablePresets {
//Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    


//Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private int presetId;

@Column(name = "PresetName", nullable = false)
 private String presetName;

 @Column(name = "PresetCategory", nullable = false)
 private String presetCategory;
 
 @Column(name = "PresetColor", nullable = false)
 private String presetColor;
    
    
//Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       
  
  public int getPresetId() {
        return presetId;
    }

    public void setPresetId(int presetId) {
        this.presetId = presetId;
    }

    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        this.presetName = presetName;
    }

    public String getPresetCategory() {
        return presetCategory;
    }

    public void setPresetCategory(String presetCategory) {
        this.presetCategory = presetCategory;
    }

    public String getPresetColor() {
        return presetColor;
    }

    public void setPresetColor(String presetColor) {
        this.presetColor = presetColor;
    }
  
 
//Method >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>        

   
    
    
}
