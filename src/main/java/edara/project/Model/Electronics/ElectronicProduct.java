package edara.project.Model.Electronics;

import edara.project.Model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "product_id")
@Getter
@Setter
public class ElectronicProduct extends Product {
    @Min(value = 1, message = "warrantyPeriod must be greater than or equal to 12 months")
    @Max(value = 60, message = "warrantyPeriod must be less than or equal to 60 months")
    private int warrantyPeriod;

    @Override
    public String getProductType() {
        return "Electronic";
    }

    @Override
    public String getProductDetails() {
        return "Warranty period: " + warrantyPeriod + " months";
    }


}
