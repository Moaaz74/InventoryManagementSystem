package edara.project.Interface;


import edara.project.Model.Supervisor;
import org.springframework.http.ResponseEntity;

public interface CreateSupervisor {
    public ResponseEntity createSupervisor(Supervisor supervisor);
}
