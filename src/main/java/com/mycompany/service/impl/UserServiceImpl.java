package main.java.com.mycompany.service.impl;

import main.java.com.mycompany.dao.JdbcRoleDao;
import main.java.com.mycompany.dao.JdbcUserDao;
import main.java.com.mycompany.dto.RoleDto;
import main.java.com.mycompany.dto.UserDto;
import main.java.com.mycompany.entity.Role;
import main.java.com.mycompany.entity.User;
import main.java.com.mycompany.mapper.BeanMapper;
import main.java.com.mycompany.service.UserService;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * A service class that implements the interface {@link UserService}.
 * @author Julia Tsukanova
 * @version 1.0
 */
public class UserServiceImpl implements UserService {
    private Session session;
    private BeanMapper beanMapper = BeanMapper.getInstance();
    private JdbcUserDao dao = new JdbcUserDao();
    private JdbcRoleDao daoRole = new JdbcRoleDao();
    private static final long INITIAL_USER_ID = 1L;

    @Override
    public void save(UserDto userDto) {
        String email = userDto.getEmail().trim();
        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email can not be empty");
        }
        User userFromDb = dao.findByEmail(email);
        if (userFromDb != null) {
            throw new RuntimeException("Can not create a new record. User with the email " + email + " already exists");
        }
        User entityUser = new User();
        entityUser.setLastName(userDto.getLastName());
        entityUser.setFirstName(userDto.getFirstName());
        entityUser.setLogin(userDto.getLogin());
        entityUser.setPassword(userDto.getPassword());
        entityUser.setEmail(userDto.getEmail());
        entityUser.setBirthDate(userDto.getBirthDate());
        List<Role> roles = new ArrayList<>();
        for (String roleName : userDto.getRoles()) {
            Role roleByName = daoRole.findByName(roleName);
            if (roleByName != null) {
                roles.add(roleByName);
            }
        }
        entityUser.setRoles(roles);
        dao.create(entityUser);
    }

    @Override
    public void update(UserDto userDto) {
        long id = userDto.getId();
        session = dao.getSession();
        session.beginTransaction();
        User userFromDb = session.get(User.class, id);
        session.close();
        if (userFromDb == null) {
            throw new RuntimeException("Can not update a user. The record does not exist");
        }
        userFromDb.setLastName(userDto.getLastName());
        userFromDb.setFirstName(userDto.getFirstName());
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            userFromDb.setPassword(userDto.getPassword());
        }
        userFromDb.setEmail(userDto.getEmail());
        userFromDb.setBirthDate(userDto.getBirthDate());
        List<Role> roles = new ArrayList<>();
        for (String roleName : userDto.getRoles()) {
            Role roleByName = daoRole.findByName(roleName);
            if (roleByName != null) {
                roles.add(roleByName);
            }
            userFromDb.setRoles(roles);
            dao.update(userFromDb);
        }
    }

    @Override
    public List<UserDto> getAll() {
        List<User> all = dao.findAll();
        List<UserDto> userDtoList = BeanMapper.listMapToList(all, UserDto.class);
        for (int i = 0; i < all.size(); i++) {
            List<RoleDto> roles = null;
            roles = BeanMapper.listMapToList(all.get(i).getRoles(), RoleDto.class);
            if (roles != null) {
                userDtoList.get(i).setRoles(roles.stream().map(RoleDto::getName).collect(Collectors.toList()));
            }

        }
        return userDtoList;
    }

    @Override
    public UserDto getByLogin(String login) {
        UserDto userDto = null;
        if (login == null || login.isEmpty()) {
            throw new RuntimeException("Login can not be empty");
        }
        User daoByLogin = dao.findByLogin(login);
        if (daoByLogin != null) {
            // throw new RuntimeException("Can not get the record with the login " + login);
            List<RoleDto> roles = null;
            roles = BeanMapper.listMapToList(daoByLogin.getRoles(), RoleDto.class);
            userDto = BeanMapper.singleMapper(daoByLogin, UserDto.class);
            if (roles != null) {
                userDto.setRoles(roles.stream().map(RoleDto::getName).collect(Collectors.toList()));
            }
        }
        return userDto;
    }

    @Override
    public UserDto getByEmail(String email) {
        UserDto userDto = null;
        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email can not be empty");
        }
        User daoByEmail = dao.findByEmail(email);
        if (daoByEmail != null) {
            List<RoleDto> roles = null;
            roles = BeanMapper.listMapToList(daoByEmail.getRoles(), RoleDto.class);
            userDto = BeanMapper.singleMapper(daoByEmail, UserDto.class);
            if (roles != null) {
                userDto.setRoles(roles.stream().map(RoleDto::getName).collect(Collectors.toList()));
            }
        }
        return userDto;
    }

    @Override
    public void remove(long id) {
        session = dao.getSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.close();
        if (user == null) {
            throw new RuntimeException("Can not remove the user. The record does not exist");
        } else if (user.getId() == INITIAL_USER_ID) {
            throw new RuntimeException("Can not remove the initial user.");
        }
        dao.remove(user);
    }
}
