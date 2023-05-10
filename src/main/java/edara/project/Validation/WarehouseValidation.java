package edara.project.Validation;

import edara.project.Interface.ProductService;
import edara.project.Model.Product;
import edara.project.Model.Supervisor;
import edara.project.Model.Warehouse;
import edara.project.Repository.WarehouseRepository;
import edara.project.Service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WarehouseValidation {
    @Autowired
    private final WarehouseRepository warehouseRepository;
    private final ProductService productService ;
    public WarehouseValidation(WarehouseRepository warehouseRepository,@Lazy ProductService productService) {
        this.warehouseRepository = warehouseRepository;
        this.productService = productService;

    }

    public boolean checkNameExists(String name){
        List<Warehouse> existingWarehouse = warehouseRepository.findByName(name);
        return existingWarehouse.isEmpty();
    }

    public boolean checkAssignedSupervisor(Long id){
        return warehouseRepository.getBySupervisorId(id).isEmpty();
    }


    public boolean checkWarehouseActivation(Long id){
        String state = warehouseRepository.findById(id).get().getState();
        return state.equals("active");
    }

    public boolean checkWarehouseExistsBySupervisor(long SupervisorId){
        List<Warehouse> warehouse  = warehouseRepository.getBySupervisorId(SupervisorId) ;
        return warehouse.isEmpty();
    }
    public boolean isExist(Long id){
        Optional<Warehouse> existingWarehouse = warehouseRepository.findById(id);
        return existingWarehouse.isPresent();
    }



    public boolean checkDuplicationNames(Long id , String name){
        List<Warehouse> product = warehouseRepository.findByNameAndId(id ,name);
        return product.isEmpty();
    }
    public boolean getWarehousesProducts(Long id){
        List<Product> Products = productService.getWarehouseProducts(id);
        return Products.isEmpty();
    }
}
