package edara.project.Validation;

import edara.project.Model.Product;
import edara.project.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@AllArgsConstructor
@Component
public class ProductValidation {

    private ProductRepository productRepository;
    private WarehouseValidation warehouseValidation;

    public Boolean checkProductExist(Long id) {
        Optional<Product> existingProduct = productRepository.findById(id);
        return existingProduct.isPresent();
    }

    public boolean checkNameDuplicationUpdateReq(Long id , String name){
        Optional<Product> product = productRepository.findByNameAndId(id ,name);
        return product.isPresent();
    }

    public boolean checkWarehouseExists(Long id){
        return warehouseValidation.isExist(id);
    }
}
