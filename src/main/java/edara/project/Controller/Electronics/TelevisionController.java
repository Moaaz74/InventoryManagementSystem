package edara.project.Controller.Electronics;
import edara.project.Model.Product;
import edara.project.Model.Electronics.TelevisionProduct;
import edara.project.Interface.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class TelevisionController {

    private final ProductService productService;


    @PostMapping("/Televisions")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> addProduct(@Valid @RequestBody TelevisionProduct product) {
        return productService.createProduct(product);
    }

    @PutMapping("/Televisions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> updateProduct( @Valid @RequestBody TelevisionProduct product , @PathVariable Long id) {
        return productService.updateProduct(id , product);
    }
}
