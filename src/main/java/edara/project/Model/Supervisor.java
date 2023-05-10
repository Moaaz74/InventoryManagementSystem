package edara.project.Model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;

@Entity
public class Supervisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    @NotEmpty(message="email does not Empty")
    @Email(message = "Email should be valid")
    private String email;

    public Supervisor(String state) {
        this.state = state;
    }

    public Supervisor() {

    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    //  public String getRole() {
    //  return role;
    // }

    @Column(length = 30 , nullable = false)
    @NotEmpty(message="password can not be Empty")
    @Size(min = 8, max = 30, message = "Password should be between 8 and 30 characters")
    private String password;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(length = 15, nullable = false)
    @NotEmpty(message="name can not be Empty")
    private String name;
    @Column(length = 15, nullable = false)
    @NotEmpty(message="state can not be Empty")
    @Pattern(regexp = "^(active|in-active)$", message = "State must be either 'active' or 'in-active'")
    private String state;

    @Column(columnDefinition = "varchar(255) default 'supervisor'")
    private String role = "supervisor";


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

