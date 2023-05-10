package edara.project.Repository;

import edara.project.Model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    @Query("SELECT supervisorId FROM Warehouse")
    List<String> findAssignedSuperviors();

   /* @Query("SELECT Warehouse.id , Warehouse.name ,Warehouse.location,Warehouse.state,User.id as supervisorId,User.email,SUM(Product.stock) as totalStockQuantity FROM Warehouse JOIN User on Warehouse.supervisorId = User.id LEFT JOIN Product on Product.warehouseId = Warehouse.id  GROUP BY Warehouse.id,Warehouse.name")
    List<Warehouse> findAllWarehouses();*/

    /*@Query("SELECT Product.name , Product.stock , Product.description FROM Warehouse JOIN Product ON Product.WarehosueId = Warehouse.id")
    List<Product> findProducts();*/

    @Query("SELECT w FROM Warehouse w WHERE w.name = :name")
    List<Warehouse> findByName(@Param("name") String name);


    @Query("SELECT w FROM Warehouse w WHERE w.id != :id AND w.name = :name")
    List<Warehouse> findByNameAndId(@Param("id") Long id,@Param("name") String name);


    @Query("SELECT w FROM Warehouse w WHERE w.supervisorId = :id")
    List<Warehouse> getBySupervisorId(@Param("id") long id);


}
