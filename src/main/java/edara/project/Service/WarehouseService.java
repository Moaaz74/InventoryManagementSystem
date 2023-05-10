package edara.project.Service;

import edara.project.Model.Product;
import edara.project.Model.Supervisor;
import edara.project.Model.Warehouse;
import edara.project.Repository.WarehouseRepository;
import edara.project.Interface.ProductService;
import edara.project.Validation.SupervisorValidation;
import edara.project.Validation.WarehouseValidation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
     private final SupervisorService userService;
    private final ProductService productService ;
    private final WarehouseValidation warehouseValidation;

    private final SupervisorValidation supervisorValidation;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository , @Lazy SupervisorService userService, @Lazy ProductService productService ,
                            WarehouseValidation warehouseValidation , SupervisorValidation supervisorValidation) {
        this.warehouseRepository = warehouseRepository;
        this.userService = userService;
        this.productService = productService;
        this.warehouseValidation = warehouseValidation;
        this.supervisorValidation = supervisorValidation;
    }

    public ResponseEntity createWarehouse(Warehouse warehouse) {
        // Check duplication of names
        if(!warehouseValidation.checkNameExists(warehouse.getName())){
            return ResponseEntity.badRequest().body("Warehouse Name is already exists");
        }

        if (!supervisorValidation.checkSupervisorById(warehouse.getSupervisorId()))
            return  ResponseEntity.badRequest().body("Supervisor does not exist");


        if(!supervisorValidation.checkSupervisorActivation(warehouse.getSupervisorId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Supervisor is In-active");
        }

        if (warehouseValidation.checkWarehouseExistsBySupervisor(warehouse.getSupervisorId())){
            warehouseRepository.save(warehouse);
            return ResponseEntity.status(HttpStatus.OK).body("Warehouse Added successfully");
        }
        return ResponseEntity.badRequest().body("Supervisor has already a warehouse" ) ;
    }

    public List<Warehouse> getWarehouses(){
        return warehouseRepository.findAll();
    }

    public ResponseEntity getWarehouse(Long id){
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);
        if (warehouse.isPresent()){
            return new ResponseEntity<>(warehouse , HttpStatus.OK) ;
        }return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    // Called only after validation
    public Optional<Warehouse> getOptionalWarehouse(Long id){
        return warehouseRepository.findById(id);
    }

    public ResponseEntity updateWarehouse(Long id , Warehouse updatedwarehouse){
        // Check id is existing or not
        if(!warehouseValidation.isExist(id)){
            return new ResponseEntity<String>("Warehouse is not Found",HttpStatus.NOT_FOUND);
        }

        // Check duplication of names
        if(!warehouseValidation.checkDuplicationNames(id , updatedwarehouse.getName())){
            return ResponseEntity.badRequest().body("Warehouse Name is already exists");
        }

        // Check Supervisor id is existing or not
        if (!supervisorValidation.checkSupervisorById(updatedwarehouse.getSupervisorId()))
            return  ResponseEntity.badRequest().body("Supervisor does not exist");

        // Check Activation of supervisor
        if(!supervisorValidation.checkSupervisorActivation(updatedwarehouse.getSupervisorId())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supervisor is In-active");
        }

        updatedwarehouse.setId(id);


        warehouseRepository.save(updatedwarehouse);
        return ResponseEntity.status(HttpStatus.OK).body("Warehouse Updated successfully");
    }

    public ResponseEntity deleteWarehouse(Long id) {
        try {
            if(!warehouseValidation.isExist(id)){
                return ResponseEntity.badRequest().body("Warehouse does not exist");
            }
            if(!warehouseValidation.getWarehousesProducts(id)){
                return ResponseEntity.badRequest().body("Warehouse has already products must be deleted first");
            }
            warehouseRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Warehouse Deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    public ResponseEntity getWarehousesProducts(Long id){
        if(warehouseValidation.isExist(id))
            return ResponseEntity.ok( productService.getWarehouseProducts(id));
        else
            return ResponseEntity.badRequest().body("Warehouse does not exist");
    }



}