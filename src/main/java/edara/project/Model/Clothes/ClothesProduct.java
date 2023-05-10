package edara.project.Model.Clothes;

import edara.project.Model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "product_id")
@Getter
@Setter
public class ClothesProduct extends Product {
    @NotEmpty(message = "size must not be empty")
    private String size;
    @NotEmpty(message = "color must not be empty")
    private String color;
    @Override
    public String getProductType() {
        return "Clothes";
    }

    @Override
    public String getProductDetails() {
        return "Size: " + size + " color: " + color;
    }
}
