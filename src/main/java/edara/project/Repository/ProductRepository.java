package edara.project.Repository;

import edara.project.Model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Optional<Product> findByName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.id != :id AND p.name = :name")
    Optional<Product> findByNameAndId(@Param("id") Long id ,@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.warehouseId = :id")
    List<Product> getWarehouseProducts(@Param("id") Long id);

}
