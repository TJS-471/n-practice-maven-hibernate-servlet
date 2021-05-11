package main.java.com.mycompany.service;

import main.java.com.mycompany.dto.RoleDto;

import java.util.List;

/**
 * A service interface for {@link RoleDto}.
 * @author Julia Tsukanova
 * @version 1.0
 */
public interface RoleService {
    void save(RoleDto roleDto);
    void update(RoleDto roleDto);
    List<RoleDto> getAll();
    void remove(RoleDto roleDto);
}
