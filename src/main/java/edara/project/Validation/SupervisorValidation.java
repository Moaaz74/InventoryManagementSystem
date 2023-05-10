package edara.project.Validation;

import edara.project.Model.Supervisor;
import edara.project.Repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SupervisorValidation {
    @Autowired
    private final SupervisorRepository supervisorRepository;

    public SupervisorValidation(SupervisorRepository supervisorRepository){
        this.supervisorRepository = supervisorRepository;
    }
    public boolean checkSupervisorByEmail(String Email){
        return supervisorRepository.findByemail(Email).isPresent();
    }

    // only after validation
    public boolean checkSupervisorActivation(Long id){
        String state =  supervisorRepository.findById(id).get().getState();
        return state.equals("active");
    }

    public boolean checkSupervisorById(long id){
        return supervisorRepository.findById(id).isPresent();
    }

    public boolean checkEmailDuplications(Long id , String email){
        return supervisorRepository.findByEmailAndId(id , email).isPresent();
    }



}
