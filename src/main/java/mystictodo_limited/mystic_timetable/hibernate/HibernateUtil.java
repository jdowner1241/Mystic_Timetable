/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mystictodo_limited.mystic_timetable.hibernate;

import mystictodo_limited.mystic_timetable.db.DbConnectionManager;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author Jamario_Downer
 */
public class HibernateUtil {
    
    private static final SessionFactory sessionFactory = buildSessionFactory();
    static DbConnectionManager logger = new DbConnectionManager(HibernateUtil.class);
    
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            logger.CreateLog("error", "Hibernate Error : " + ex, null);
            
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
    
    
}
