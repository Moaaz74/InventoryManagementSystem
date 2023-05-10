package edara.project.Service;

import edara.project.Model.Product;
import edara.project.Repository.ProductRepository;
import edara.project.Interface.ProductService;
import edara.project.Validation.ProductValidation;
import edara.project.Validation.WarehouseValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private final ProductRepository productRepository;
    private final WarehouseValidation warehouseValidation;
    private final ProductValidation productValidation ;

    public ProductServiceImpl(ProductRepository productRepository , WarehouseValidation warehouseValidation, ProductValidation productValidation) {
        this.productRepository = productRepository;
        this.warehouseValidation =warehouseValidation ;
        this.productValidation = productValidation;
    }

    @Override
    public ResponseEntity createProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findByName(product.getName());
        if (!existingProduct.isEmpty()) {
            return new ResponseEntity<String>("Product name is already exist", HttpStatus.BAD_REQUEST);
        }
        if(!productValidation.checkWarehouseExists(product.getWarehouseId())){
            return new ResponseEntity<String>("Warehouse is not exist" , HttpStatus.NOT_FOUND);
        }
        if(!warehouseValidation.checkWarehouseActivation(product.getWarehouseId())){
            return new ResponseEntity<String>("Warehouse is In-active" , HttpStatus.BAD_REQUEST);
        }
        productRepository.save(product);
        return new ResponseEntity<Product>( product ,HttpStatus.OK);
    }
    @Override
    public ResponseEntity updateStock(Long id , int stock) {
        if (!productValidation.checkProductExist(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body ("Product is not found"
                    + id); }
        else {
            Product product = productRepository.findById(id).get();
            product.setStock(stock+product.getStock());
            productRepository.save(product);
            return ResponseEntity.status(HttpStatus.OK).body("Product Stock updated");
        }
    }

    @Override
    public ResponseEntity updateProduct(Long id, Product product) {
        if(!productValidation.checkProductExist(id))
            return new ResponseEntity<String>("Product id isn't exist", HttpStatus.BAD_REQUEST);
        if(productValidation.checkNameDuplicationUpdateReq(id, product.getName())){
            return new ResponseEntity<String>("Product Name is already exist", HttpStatus.BAD_REQUEST);
        }
        if(!productValidation.checkWarehouseExists(product.getWarehouseId())){
            return new ResponseEntity<String>("Warehouse is not exist" , HttpStatus.NOT_FOUND);
        }
        product.setId(id);
        productRepository.save(product);
        return new ResponseEntity<Product>( product ,HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteProduct(Long id) {
        if(!productValidation.checkProductExist(id))
            return new ResponseEntity<String>("Product id isn't exist", HttpStatus.BAD_REQUEST);
        productRepository.deleteById(id);
        return new ResponseEntity<String>( "Product is deleted" ,HttpStatus.OK);
    }

    @Override
    public ResponseEntity getProduct(Long id) {
        if(!productValidation.checkProductExist(id))
            return new ResponseEntity<String>("Product id isn't exist", HttpStatus.BAD_REQUEST);
        Product product = productRepository.findById(id).get();
        return new ResponseEntity<Product> (product , HttpStatus.OK);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getWarehouseProducts(Long id){
        return productRepository.getWarehouseProducts(id);
    }

}
