package edara.project.Model.Clothes;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "clothesProduct_id")
@Getter
@Setter
public class JacketProduct extends ClothesProduct {

    private boolean hasHood;

    @Override
    public String getProductType() {
        return "Jacket";
    }

    @Override
    public String getProductDetails() {
        return "Size: " + super.getSize() + " - hasHood: " + hasHood ;
    }
}
