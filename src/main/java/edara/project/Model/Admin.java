package edara.project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30, nullable = false)
    @NotEmpty(message="Name can not be Empty")
    private String name;
    @Column(nullable = false, unique = true, length = 45)
    @NotEmpty(message="Email can not be Empty")
    @Email(message = "Should be a valid Email")
    private String email;
    @Column(length = 30 , nullable = false)
    @NotEmpty(message="Password can not be Empty")
    @Size(min = 8, max = 30, message = "Password should be between 8 and 30 characters")
    private String password;

    @Column(columnDefinition = "varchar(255) default 'admin'")
    private String role = "admin";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
