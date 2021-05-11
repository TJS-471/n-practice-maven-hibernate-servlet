package main.java.com.mycompany.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple Java domain class that represents a {@link User}.
 * @author Julia Tsukanova
 * @version 1.0
 */
@Entity
@Table(name = "users_tb")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "login")
    @NotNull(message = "Login cannot be empty")
    private String login;

    @Column(name = "email")
    @NotNull(message = "Email cannot be empty")
    @Email(message = "Please, enter a valid email")
    private String email;

    @Column(name = "password", nullable = false)
    @Pattern(regexp = "[^<>%$]+", message = "Please, enter a password that does not contain special characters")
    private String password;

    @Column(name = "first_name")
    @Size(min = 2, max = 20, message = "Please,  enter the first name that is greater than 2 and less than 20 characters")
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 2, max = 45, message = "Please, enter the last name that is greater than 2 and less than 45 characters")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    @Past(message = "Please, enter the birth date that is in the past")
    private Date birthDate;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "roles_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public User(long id, String login, String email, String password, String firstName, String lastName, Date birthDate) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public User(String login, String email, String password, String firstName, String lastName, Date birthDate) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public User() {
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    // utility method to set bi-directional relationships
    public void addRole(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }

}
