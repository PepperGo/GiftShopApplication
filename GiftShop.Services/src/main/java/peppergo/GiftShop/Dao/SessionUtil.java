/**
* @author MingSun E-mail:meetmark.sun@gmail.com
*/
package peppergo.GiftShop.Dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import peppergo.GiftShop.Model.User;


public class SessionUtil {
    private static SessionUtil instance=new SessionUtil();
    private SessionFactory sessionFactory;
    
    public static SessionUtil getInstance(){
         return instance;
    }
    
    @SuppressWarnings("deprecation")
    private SessionUtil(){
       // Configuration configuration = new Configuration();
      //  configuration.configure("classpath:hibernate.cfg.xml");

        try {
            sessionFactory = new AnnotationConfiguration().
                    configure().
                    addPackage("pepeprgo.GiftShop.Model"). //add package if used.
                    addAnnotatedClass(User.class).
                    buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
        } 
    }
    
    public static Session getSession(){
        Session session =  getInstance().sessionFactory.openSession();
        return session;
    }
    
}
