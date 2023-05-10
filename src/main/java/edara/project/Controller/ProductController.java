package edara.project.Controller;
import edara.project.Model.*;
import edara.project.Interface.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("getAllProducts")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("getProduct/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

    @PutMapping("updateStock/{stock}/{id}")
    public void updateStock(@Valid @PathVariable int stock , @PathVariable Long id) {
        productService.updateStock(id, stock);
    }


}
