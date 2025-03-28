/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.hibernate;

import java.io.Serializable;
import java.util.List;
import mystictodo_limited.mystic_timetable.db.DbConnectionManager;
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
        
        //Use the ConnectionManager class from a another package for logging
        logger = new DbConnectionManager(persistentClass);
    }
    
    //Fields >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
    private Class<T> persistentClass;
    private SessionFactory sessionFactory;
    private DbConnectionManager logger;
    
    //Getters/Setters >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
    protected Session getSession() {
        return sessionFactory.openSession();
    }
    
    //Methods >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>    
    
    @Override
    public T findById(ID id) {
        logger.CreateLog("info", "Hibernate DAO : FindById Operations triggered.", null);
        T entity = null;
        Session session = null;
        
        try {
            // Get session and display the object/entry by classid
            session = getSession();
            entity = session.get(persistentClass, id);
            
            if(entity != null){
               logger.CreateLog("info", "Hibernate DAO : FindById returned entity.", null);
            }
            
        }catch(Exception e){
            logger.CreateLog("error", "Hibernate DAO : FindById failed to return entity due to error. ", e);  
        }finally{
            if(session != null){
                session.close();
                logger.CreateLog("info", "Hibernate DAO : Connection Closed.", null);
            } 
        }
        return entity;
    }

    @Override
    public List<T> findAll() {
       logger.CreateLog("info", "Hibernate DAO : FindAll Operations triggered.", null);
       List<T> entities = null;
       Session session = null;
       
       try{
           // Get session and display all entities
            session = getSession();
            entities = session.createQuery("from " 
               + persistentClass.getName(), persistentClass).list();
            
            if(!entities.isEmpty()){
                logger.CreateLog("info", "Hibernate DAO : FindAll returned entities.", null);
            }
                    
       }catch(Exception e){
            logger.CreateLog("error", "Hibernate DAO : FindAll failed to return entities due to error. ", e);  
       }finally {
        if(session != null){
                session.close();
                logger.CreateLog("info", "Hibernate DAO : Connection Closed.", null);
            } 
       }
       
       return entities;
    }

    @Override
    public boolean save(T entity) {
        logger.CreateLog("info", "Hibernate DAO : Save Operations triggered.", null);
        Session session = null;
        Transaction transaction = null;
        boolean success = false;
        
        try{
            // Get session and save entity
            session = getSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            success = true;
            logger.CreateLog("info", "Hibernate DAO : Save successful.", null);
            
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            logger.CreateLog("error", "Hibernate DAO : Save unsuccessful due to error. ", e);  
        }finally {
            if(session != null){
                session.close();
                logger.CreateLog("info", "Hibernate DAO : Connection Closed.", null);
            } 
        }

        return success;
    }

    @Override
    public boolean update(T entity) {
        logger.CreateLog("info", "Hibernate DAO : Update Operations triggered.", null);
        Session session = null;
        Transaction transaction = null;
        boolean success = false;
        
        try{
            // Get session and update entity
            session = getSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            success = true;
            logger.CreateLog("info", "Hibernate DAO : Update successful.", null);
            
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            logger.CreateLog("error", "Hibernate DAO : Update unsuccessful due to error. ", e);  
        }finally {
            if(session != null){
                session.close();
                logger.CreateLog("info", "Hibernate DAO : Connection Closed.", null);
            } 
        }

        return success;
    }

    @Override
    public boolean delete(T entity) {
        logger.CreateLog("info", "Hibernate DAO : Delete Operations triggered.", null);
        Session session = null;
        Transaction transaction = null;
        boolean success = false;
        
        try{
            // Get session and delete entity by id
            session = getSession();
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
            success = true;
            logger.CreateLog("info", "Hibernate DAO : Delete successful.", null);
            
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            logger.CreateLog("error", "Hibernate DAO : Delete unsuccessful due to error. ", e);  
        }finally {
            if(session != null){
                session.close();
                logger.CreateLog("info", "Hibernate DAO : Connection Closed.", null);
            } 
        }

        return success;
          
    }
    
}
