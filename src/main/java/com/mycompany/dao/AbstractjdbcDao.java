package main.java.com.mycompany.dao;

import main.java.com.mycompany.entity.Role;
import main.java.com.mycompany.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * An abstract class that creates a {@link SessionFactory}.
 * @author Julia Tsukanova
 * @version 1.0
 */
public abstract class AbstractjdbcDao {
    private static SessionFactory factory;
    private Session session;

    static  {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Role.class)
                .buildSessionFactory();
    }

    public void closeResources() {
        if (factory != null) {
            factory.close();
        }
    }

    public SessionFactory getFactory() {

        return factory;
    }

    public Session getSession() {
        return getFactory().getCurrentSession();
    }
}
