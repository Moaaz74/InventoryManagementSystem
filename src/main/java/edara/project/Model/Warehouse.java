package edara.project.Model;

import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name is required")
    @Size(min = 4, max = 20, message = "Name must be between 4 and 20 characters")
    private String name;

    @NotNull(message = "Location cannot be null")
    @NotBlank(message = "Location is required")
    @Size(min = 4, max = 50, message = "Location must be between 4 and 20 characters")
    private String location;

    @NotNull(message = "State cannot be null")
    @NotBlank(message = "State is required")
    @Pattern(regexp = "^(active|in-active)$", message = "State must be either 'active' or 'in-active'")
    private String state;

    @NotNull(message = "Type cannot be null")
    @NotBlank(message = "Type is required")
    @Size(min = 4, max = 20, message = "Type must be between 4 and 20 characters")
    private String type;

    @Digits(integer = 11, fraction = 0, message = "Supervisor Id must be a numeric value")
    @NotNull(message = "SupervisorId cannot be null")
    private Long supervisorId;
}
