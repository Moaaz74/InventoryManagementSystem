package edara.project.Controller;


import edara.project.Model.Product;
import edara.project.Model.Supervisor;
import edara.project.Service.ProductServiceImpl;
import edara.project.Service.SupervisorService;
import edara.project.Service.WarehouseService;
import edara.project.Validation.SupervisorValidation;
import jakarta.validation.Valid;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/supervisor")
public class SupervisorController {

    private final SupervisorService supervisorService;
    private final ProductServiceImpl productService;
    private final WarehouseService warehouseService;
    private final SupervisorValidation supervisorValidation;

    @Autowired
    public SupervisorController(SupervisorService supervisorService, @Lazy ProductServiceImpl productService1, @Lazy WarehouseService warehouseService,@Lazy SupervisorValidation supervisorValidation) {
        this.supervisorService = supervisorService;
        this.productService = productService1;
        this.warehouseService = warehouseService;
        this.supervisorValidation = supervisorValidation;
    }

     @PutMapping("/updatestock/{id}/{stock}")
     public ResponseEntity<String> updateproductStock(@PathVariable Long id, @PathVariable Integer stock) {
         return productService.updateStock(id, stock);
    }



     @GetMapping("/all-product/{id}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable Long id) {
        return warehouseService.getWarehousesProducts(id);
    }



}

