package edara.project.Controller;

import edara.project.Model.Supervisor;
import edara.project.Model.Warehouse;
import edara.project.Service.SupervisorService;
import edara.project.Service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    private final WarehouseService warehouseService;

    private final SupervisorService supervisorService;

    @Autowired
    public AdminController(WarehouseService warehouseService , SupervisorService supervisorService){
        this.warehouseService = warehouseService;
        this.supervisorService = supervisorService;
    }

    @GetMapping("/warehouse/showall")
    public ResponseEntity<List<Warehouse>> getWarehouses() {
        return new ResponseEntity<>(warehouseService.getWarehouses() , HttpStatus.OK);
    }

    @GetMapping("/warehouse/{id}")
    public ResponseEntity getWarehouse(@PathVariable Long id) {
        return warehouseService.getWarehouse(id);
    }

    @GetMapping("/warehouse/products/{id}")
    public ResponseEntity getWarehouseProducts(@PathVariable Long id){
        return warehouseService.getWarehousesProducts(id);

    }

    @PostMapping("/warehouse/create")
    public ResponseEntity createWarehouse(@Valid @RequestBody Warehouse warehouse ) {
        return warehouseService.createWarehouse(warehouse);
    }

    @PutMapping("/warehouse/update/{id}")
    public ResponseEntity updateWarehouse(@Valid @RequestBody Warehouse updatedWarehouse , @PathVariable Long id ){
        return warehouseService.updateWarehouse(id, updatedWarehouse);
    }

    @DeleteMapping("/warehouse/delete/{id}")
    public ResponseEntity deleteWarehouse(@PathVariable Long id) {
        return warehouseService.deleteWarehouse(id);
    }

    @PostMapping("/supervisor/createSupervisor")
    public ResponseEntity addSupervisor(@Valid @RequestBody Supervisor supervisor) {
        return supervisorService.createSupervisor(supervisor);
    }

    @PutMapping("/supervisor/updateSupervisor/{id}")
    public ResponseEntity updateSupervisorById(@PathVariable Long id,
                                               @Valid @RequestBody Supervisor supervisor) {
        return supervisorService.updateSupervisorById(id , supervisor);
    }

    @GetMapping("/supervisor/all-supervisors")
    public ResponseEntity getAllSupervisors() {
        return supervisorService.getAllSupervisors();
    }

    @GetMapping("/supervisor/{id}")
    public ResponseEntity getSupervisor (@PathVariable ("id") Long id){
        return supervisorService.getSupervisor(id);
    }



    @DeleteMapping("/supervisor/deleteSupervisor/{id}")
    public ResponseEntity deleteSupervisor(@PathVariable Long id) {
        return supervisorService.deleteSupervisorById(id);
    }

}
