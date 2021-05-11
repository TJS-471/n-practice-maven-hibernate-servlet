package main.java.com.mycompany.dao;

import main.java.com.mycompany.entity.Role;

import java.io.IOException;
import java.sql.SQLException;

/**
 * An interface that provides CRUD operations for a {@link Role} in the underlying storage mechanism.
 * @author Julia Tsukanova
 * @version 1.0
 */
public interface RoleDao {
    void create(Role role) throws IOException, SQLException;
    void update(Role role)throws IOException, SQLException;
    void remove(Role role)throws IOException, SQLException;
    Role findByName(String name)throws IOException, SQLException;
}
