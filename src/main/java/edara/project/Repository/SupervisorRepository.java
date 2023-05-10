package edara.project.Repository;
import edara.project.Model.Supervisor;
import edara.project.Model.Warehouse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
    @Query("SELECT s FROM Supervisor s WHERE s.email = :email")
    public Optional<Supervisor> findByemail(@Param("email") String email);



    @Query("SELECT s FROM Supervisor s WHERE s.id != :id AND s.email = :email")
    Optional<Supervisor> findByEmailAndId(@Param("id") Long id, @Param("email") String email);

}



