package edara.project.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "product_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter

public class Product  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 1, max = 15 , message = "Name Length must be from 1 to 15")
    private String name;

    @NotEmpty(message = "description must not be empty")
    @Size(min = 1, max = 15 , message = "description Length must be from 1 to 15")
    private String description;

    @Min(value = 1, message = "Stock must be greater than or equal to 1")
    @Max(value = 500, message = "Stock must be less than or equal to 50")
    private int stock;

    @DecimalMin(value = "0.0", inclusive = true, message = "Price Value must be greater than or equal to 0.0")
    private Double price;

    @Min(value = 1, message = "warehouseId must be greater than or equal to 1")
    private Long warehouseId;


    public  String getProductType() {
        return null;
    }
    public  String getProductDetails() {
        return null;
    }




}
