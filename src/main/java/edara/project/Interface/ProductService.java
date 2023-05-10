package edara.project.Interface;
import edara.project.Model.Product;
import org.springframework.http.ResponseEntity;


import java.util.List;


public interface ProductService {
    ResponseEntity createProduct(Product product);
    ResponseEntity updateProduct(Long id, Product product);
    ResponseEntity deleteProduct(Long id);
    ResponseEntity getProduct(Long id);
    List<Product> getAllProducts();
    ResponseEntity updateStock(Long id , int stock);
    public List<Product> getWarehouseProducts(Long id);

}
