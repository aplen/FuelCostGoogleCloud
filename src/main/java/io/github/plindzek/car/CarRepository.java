package io.github.plindzek.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author Adam
 * Car repository
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}