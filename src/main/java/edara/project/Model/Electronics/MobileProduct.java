package edara.project.Model.Electronics;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "electronicProduct_id")
@Getter
@Setter
public class MobileProduct extends ElectronicProduct {
    @Min(value = 1, message = "RAM must be greater than or equal to 1 GB")
    @Max(value = 32, message = "RAM must be less than or equal to 32 GB")
    private int ram;
    @Min(value = 1, message = "storage must be greater than or equal to 1 GB")
    @Max(value = 1024, message = "storage must be less than or equal to 1024 GB")
    private int storage;

    @Override
    public String getProductType() {
        return "Mobile";
    }

    @Override
    public String getProductDetails() {
        return "Warranty period: " + super.getWarrantyPeriod() + " months - Ram: "  + ram + "GB - Storage: " + storage + "GB";
    }

}
