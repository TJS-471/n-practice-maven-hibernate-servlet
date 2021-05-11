package main.java.com.mycompany.dao;



import main.java.com.mycompany.entity.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


/**
 * A class that extends a {@link AbstractjdbcDao} and implements an interface {@link RoleDao}.
 * @author Julia Tsukanova
 * @version 1.0
 */
public class JdbcRoleDao extends AbstractjdbcDao implements RoleDao {
    private Session session;

    @Override
    public void create(Role role) {
        session = getSession();
        session.beginTransaction();
        session.save(role);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Role role) {
        session = getSession();
        session.beginTransaction();
        session.update(role);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void remove(Role role) {
        long id = role.getId();
        session = getSession();
        session.beginTransaction();
        session.delete(role);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Role findByName(String name) {
        Role role = null;
        session = getSession();
        session.beginTransaction();
        Query query = session.createQuery("from Role where name=:name");
        query.setParameter("name", name);
        List list = query.list();
        if (list != null && list.size() > 0) {
            role = (Role) list.get(0);
        }
        session.close();
        return role;
    }

    public List<Role> getAll() {
        List<Role> roles = null;
        session = getSession();
        session.beginTransaction();
        roles = session.createQuery("from Role", Role.class).getResultList();
        session.close();
        return roles;
    }
}
