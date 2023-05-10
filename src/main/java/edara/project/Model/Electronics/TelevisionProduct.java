package edara.project.Model.Electronics;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "electronicProduct_id")
@Getter
@Setter
public class TelevisionProduct extends ElectronicProduct {
    @NotEmpty(message = "resolution must not be empty")
    private String resolution;
    @Override
    public String getProductType() {
        return "Television";
    }

    @Override
    public String getProductDetails() {
        return "Warranty period: " + super.getWarrantyPeriod() + " months " + "- Resolution: " + resolution ;
    }
}
