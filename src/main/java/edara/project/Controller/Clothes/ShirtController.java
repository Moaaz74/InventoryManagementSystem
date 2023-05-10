package edara.project.Controller.Clothes;

import edara.project.Model.Clothes.ShirtProduct;
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
public class ShirtController {

    private final ProductService productService;


    @PostMapping("/Shirts")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ShirtProduct product) {
        return productService.createProduct(product);
    }
    @PutMapping("/Shirts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody ShirtProduct product , @PathVariable Long id ) {
        return productService.updateProduct(id , product);
    }


}
