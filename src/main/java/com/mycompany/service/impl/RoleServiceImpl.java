package main.java.com.mycompany.service.impl;

import main.java.com.mycompany.dao.JdbcRoleDao;
import main.java.com.mycompany.dto.RoleDto;
import main.java.com.mycompany.entity.Role;
import main.java.com.mycompany.mapper.BeanMapper;
import main.java.com.mycompany.service.RoleService;
import org.hibernate.Session;

import java.util.List;

/**
 * A service class that implements the interface {@link RoleService}.
 * @author Julia Tsukanova
 * @version 1.0
 */
public class RoleServiceImpl implements RoleService {
    private Session session;
    private BeanMapper beanMapper = BeanMapper.getInstance();
    private JdbcRoleDao dao = new JdbcRoleDao();
    private static final String INITIAL_ROLE = "Admin";

    @Override
    public void save(RoleDto roleDto) {
        String name = roleDto.getName();
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Name can not be empty");
        }
        Role daoByName = dao.findByName(name);
        if (daoByName != null) {
            throw new RuntimeException("Record already exists");
        }
        Role role = BeanMapper.singleMapper(roleDto, Role.class);
        dao.create(role);
    }

    @Override
    public void update(RoleDto roleDto) {
        long id = roleDto.getId();
        String name = roleDto.getName().trim();
        session = dao.getSession();
        session.beginTransaction();
        Role role = session.get(Role.class, id);
        session.close();
        if (role == null) {
            throw new RuntimeException("Can not update a role. The record does not exist");
        }
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Name can not be empty");
        }
        role.setName(roleDto.getName());
        dao.update(role);
    }

    @Override
    public List<RoleDto> getAll() {
        List<Role> all = dao.getAll();
        List<RoleDto> returnValue = BeanMapper.listMapToList(all, RoleDto.class);
        return returnValue;
    }

    @Override
    public void remove(RoleDto roleDto) {
        String name = roleDto.getName().trim();
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Name can not be empty");
        }
        Role roleByName = dao.findByName(name);
        if (roleByName == null) {
            throw new RuntimeException("Can not remove a role. The record does not exist");
        } else if (roleByName.getName().equalsIgnoreCase(INITIAL_ROLE)) {
            throw new RuntimeException("Can not remove the admin role.");
        }
        dao.remove(roleByName);
    }
}
