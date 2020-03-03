/**
 *
 */
package io.github.plindzek.car;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * @author Adam
 * contain methods to execute CRUD operations on car table
 *
 */
@Repository
public interface CarRepository extends JpaRepository {

    List<Car> findAll();


    public Optional<Car> findById(Integer id);


    public Optional<Car> findByName(String name);

    Car updateCar(Integer id, String newName);


    Car addCar(Car newCar);


    boolean deleteCar(Integer id);
       

}
