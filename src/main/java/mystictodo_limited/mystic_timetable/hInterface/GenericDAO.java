/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mystictodo_limited.mystic_timetable.hInterface;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jamario_Downer
 */
public interface GenericDAO <T, ID extends Serializable> {
    T findById(ID id);
    List<T> findAll();
    boolean save(T entity);
    boolean update(T entity);
    boolean delete(T enity);
}
