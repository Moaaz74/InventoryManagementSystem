package edara.project.Service;
import edara.project.Model.Supervisor;
import edara.project.Repository.SupervisorRepository;
import edara.project.Interface.CreateSupervisor;
import edara.project.Interface.DeleteSupervisor;
import edara.project.Validation.SupervisorValidation;
import edara.project.Validation.WarehouseValidation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupervisorService implements CreateSupervisor, DeleteSupervisor {
    private static SupervisorRepository supervisorRepository = null;

    private final SupervisorValidation supervisorValidation;

    private final WarehouseValidation warehouseValidation;

    @Autowired
    public SupervisorService(SupervisorRepository supervisorRepository , SupervisorValidation supervisorValidation,
                             WarehouseValidation warehouseValidation) {
        this.supervisorRepository = supervisorRepository;
        this.supervisorValidation = supervisorValidation;
        this.warehouseValidation = warehouseValidation;
    }

    @Override
    public ResponseEntity createSupervisor(Supervisor supervisor) {
          if (supervisorValidation.checkSupervisorByEmail(supervisor.getEmail())) {
              return ResponseEntity.badRequest().body("Email is already exists");
          } else {
              supervisorRepository.save(supervisor);
              return ResponseEntity.ok("Supervisor created successfully");
          }
    }

    public ResponseEntity updateSupervisorById(Long id, Supervisor supervisor) {

        if(!supervisorValidation.checkSupervisorById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supervisor is not found " +
                    "" + id);
        }
        if(supervisorValidation.checkEmailDuplications(id , supervisor.getEmail())){
            return ResponseEntity.badRequest().body("Email is already exists");
        }
        supervisor.setId(id);
        supervisorRepository.save(supervisor);
        return ResponseEntity.ok("Supervisor updated successfully");
    }

    @Override
    public ResponseEntity deleteSupervisorById(Long id) {
        if(!warehouseValidation.checkAssignedSupervisor(id)){
            return ResponseEntity.badRequest().body("Please delete the warehouse assigned to this Supervisor first");
        }

        if (supervisorValidation.checkSupervisorById(id)){
          supervisorRepository.deleteById(id);
          return ResponseEntity.ok("Supervisor deleted successfully");
        }



        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Supervisor is not exists");
    }

    public ResponseEntity getAllSupervisors(){
        List<Supervisor> supervisors = supervisorRepository.findAll();
        return ResponseEntity.ok(supervisors) ;
    }

    public ResponseEntity getSupervisor(Long id){
        if(supervisorValidation.checkSupervisorById(id)){
            return ResponseEntity.ok(supervisorRepository.findById(id));
        }return new ResponseEntity<>( "Supervisor does not exist" , HttpStatus.NOT_FOUND);

    }

    // public Product updateproductStock(Integer id , Integer Stock){
    // Optional<Product> product = productService.findById(id);
    // if (product.isEmpty()) {
    //return null ;
    // } else {
    /// function from yousef productService.function from yousef(id,Stock) ;
    // return product.orElse(null);

    //}


    //}

    ////////////////////////////////////////
    // public List<Product> getAllProducts(){
    //  List<Product> products = warehouseService.namefunfrommoazzz();
    // return products ;
    // }





}


