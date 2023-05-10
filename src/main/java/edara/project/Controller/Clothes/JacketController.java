package edara.project.Controller.Clothes;

import edara.project.Model.Clothes.JacketProduct;
import edara.project.Model.Product;
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
public class JacketController {

    private final ProductService productService;



    @PostMapping("/Jackets")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> addProduct(@Valid @RequestBody JacketProduct product) {
        return productService.createProduct(product);
    }
    @PutMapping("/Jackets/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody JacketProduct product , @PathVariable Long id ) {
        return productService.updateProduct(id , product);
    }

}
