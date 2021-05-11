package main.java.com.mycompany.dao;

import main.java.com.mycompany.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * A class that extends a {@link AbstractjdbcDao} and implements an interface {@link UserDao}.
 * @author Julia Tsukanova
 * @version 1.0
 */
public class JdbcUserDao extends AbstractjdbcDao implements UserDao {
    private Session session;

    @Override
    public void create(User user) {
        session = getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(User user) {
        session = getSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void remove(User user) {
        session = getSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> findAll() {
        session = getSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User ", User.class).getResultList();
        session.close();
        return users;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        session = getSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where login = :login");
        query.setParameter("login", login);
        List userByLogin = query.list();
        if (userByLogin != null && userByLogin.size() > 0) {
            user = (User) userByLogin.get(0);
        }
        session.close();
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        session = getSession();
        session.beginTransaction();
        Query query = session.createQuery("from User where email = :email");
        query.setParameter("email", email);
        List userByEmail = query.list();
        if (userByEmail != null && userByEmail.size() > 0) {
            user = (User) userByEmail.get(0);
        }
        session.close();
        return user;
    }

}
