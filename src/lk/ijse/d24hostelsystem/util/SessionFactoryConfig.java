package lk.ijse.d24hostelsystem.util;

import lk.ijse.d24hostelsystem.entity.Reservation;
import lk.ijse.d24hostelsystem.entity.Room;
import lk.ijse.d24hostelsystem.entity.Student;
import lk.ijse.d24hostelsystem.entity.Users;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfiguration;
    //factoryConfiguration is a SessionFactoryConfig single instance static variable
    private final SessionFactory sessionFactory;
    // SessionFactoryConfig is a singleton class
    private SessionFactoryConfig() { // Private constructor initializes the SessionFactory
        sessionFactory = new Configuration() //configuration object
                .mergeProperties(Utility.getProperties())
                .addAnnotatedClass(Student.class)   // Annotated classes are added for Hibernate to manage
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(Users.class)
                .buildSessionFactory();  // BuildSessionFactory() is called to build the SessionFactory object
    }
    public static SessionFactoryConfig getInstance() {
        // If factoryConfiguration is null, a new instance is created, otherwise, the existing instance is returned
        return (null == factoryConfiguration)
                ? factoryConfiguration = new SessionFactoryConfig()
                : factoryConfiguration;
    }
    public Session getSession() throws HibernateException {  // Method to get a new Hibernate Session
        // Opens a new Session through the Session Factory & returns the Session created
        return sessionFactory.openSession();
    }
}
