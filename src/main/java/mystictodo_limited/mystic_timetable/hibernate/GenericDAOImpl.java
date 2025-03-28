/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.hibernate;

import java.io.Serializable;
import java.util.List;
import mystictodo_limited.mystic_timetable.hInterface.GenericDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Jamario_Downer
 * @param <T>
 * @param <ID>
 */
public abstract class GenericDAOImpl<T, ID extends Serializable> implements
        GenericDAO<T, ID> {
    //Constructor >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
    public GenericDAOImpl(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
        this.sessionFactory = new 
            Configuration().configure().buildSessionFactory();
    }
    
    //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
    private Class<T> persistentClass;
    private SessionFactory sessionFactory;
    
    //Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
    protected Session getSession() {
        return sessionFactory.openSession();
    }
    
    //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
    
    @Override
    public T findById(ID id) {
        Session session = getSession();
        T entity = session.get(persistentClass, id);
        session.close();
        return entity;
    }

    @Override
    public List<T> findAll() {
       Session session = getSession();
       List<T> entities = session.createQuery("from " 
               + persistentClass.getName(), persistentClass).list();
       session.close();
       return entities;
    }

    @Override
    public void save(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        
    }

    @Override
    public void update(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(T enity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(enity);
        transaction.commit();
        session.close();
          
    }
    
}
