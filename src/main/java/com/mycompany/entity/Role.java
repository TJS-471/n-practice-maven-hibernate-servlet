package main.java.com.mycompany.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple Java class that represents a role of a {@link User}.
 * @author Julia Tsukanova
 * @version 1.0
 */
@Entity
@Table(name = "roles_tb")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "name")
    @NotNull(message = "The name cannot be null")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }

    public Role() {
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
