package main.java.com.mycompany.dto;

/**
 * A class that transfers the required information.
 * @author Julia Tsukanova
 * @version 1.0
 */

public class RoleDto {
    private long id;
    private String name;
    public RoleDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleDto(String name) {
        this.name = name;
    }

    public RoleDto() {
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
