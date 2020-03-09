package io.github.plindzek.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Adam
 * Spring Car repository
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
//  managed by Spring, methods not needed

//  List<Car> findAll();
//  Optional<Car> findById(Integer id);
//  boolean deleteCar(Integer id);
//  Optional<Car> findByName(String name);
//  Car updateCar(Integer id, String newName);
}