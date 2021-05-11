package main.java.com.mycompany.dao;

import main.java.com.mycompany.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * An interface that provides CRUD operations for a {@link User} in the underlying storage mechanism.
 * @author Julia Tsukanova
 * @version 1.0
 */
public interface UserDao {
    void create(User user)throws SQLException, IOException;
    void update(User user)throws SQLException, IOException;
    void remove(User user)throws SQLException, IOException;
    List<User> findAll()throws SQLException, IOException;
    User findByLogin(String loging)throws SQLException, IOException;
    User findByEmail(String email)throws SQLException, IOException;

}
