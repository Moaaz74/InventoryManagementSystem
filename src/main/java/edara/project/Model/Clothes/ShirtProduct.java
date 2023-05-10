package edara.project.Model.Clothes;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "clothesProduct_id")
@Getter
@Setter
public class ShirtProduct extends ClothesProduct {

    private boolean hasCollar;
    @Override
    public String getProductType() {
        return "Shirt";
    }

    @Override
    public String getProductDetails() {
        return "Size: " + super.getSize() + " - hasCollar " + hasCollar;
    }
}
