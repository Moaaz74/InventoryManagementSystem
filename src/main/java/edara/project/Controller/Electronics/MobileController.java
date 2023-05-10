package edara.project.Controller.Electronics;

import edara.project.Model.Electronics.MobileProduct;
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
public class MobileController {

    private final ProductService productService;


    @PostMapping("/Mobiles")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> addProduct(@Valid @RequestBody MobileProduct product ) {
        return productService.createProduct(product);
    }
    @PutMapping("/Mobiles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody MobileProduct product , @PathVariable Long id ) {
        return productService.updateProduct(id , product);
    }
}
