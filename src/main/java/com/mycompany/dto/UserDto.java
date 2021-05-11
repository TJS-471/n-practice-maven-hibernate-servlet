package main.java.com.mycompany.dto;
import java.util.Date;
import java.util.List;

/**
 * A class that transfers the required information.
 * @author Julia Tsukanova
 * @version 1.0
 */

public class UserDto {
    private long id;
    private String login;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private List<String> roles;
    public UserDto(long id, String login, String email, String password, String firstName, String lastName,
                   Date birthDate, List<String> roles) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.roles = roles;
    }

    public UserDto(String login, String email, String password, String firstName, String lastName, Date birthDate,
                   List<String> roles) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.roles = roles;
    }

    public UserDto(long id, String email, String password, String firstName, String lastName, Date birthDate,
                   List<String> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.roles = roles;
    }

    public UserDto() {
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}
