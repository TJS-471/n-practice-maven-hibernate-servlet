package main.java.com.mycompany.service;

import main.java.com.mycompany.dto.UserDto;

import java.util.List;

/**
 * A service interface for {@link UserDto}.
 * @author Julia Tsukanova
 * @version 1.0
 */
public interface UserService {
    void save(UserDto user);
    void update(UserDto user);
    List<UserDto> getAll();
    UserDto getByLogin(String login);
    UserDto getByEmail(String email);
    void remove(long id);
}
