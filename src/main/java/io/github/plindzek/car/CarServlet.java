package io.github.plindzek.car;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Adam
 */

@RestController
@RequestMapping("/api/cars")
class CarServlet {

    private final Logger logger = LoggerFactory.getLogger(CarServlet.class);
    private CarRepository repository;

    CarServlet(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<List<Car>> findAllCars() {
        logger.info("Request for list all cars got");
        return ResponseEntity.ok(repository.findAll());
    }

    @PutMapping("/{id}")
    ResponseEntity<Car> updateCar(@PathVariable Integer id) {
        logger.info("Request for update car got");
        var car = repository.findById(id);
        car.ifPresent(c -> {
            c.setName(c.getName());
            repository.save(c);
        });
        return car.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<Car> saveCar(@RequestBody Car car) {
        logger.info("Request for add car got");
        return ResponseEntity.ok(repository.save(car));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Car> deleteCar(@PathVariable Integer id) {
        logger.info("Request for delete car got");
        var car = repository.findById(id);
        car.ifPresent(c -> {
            repository.delete(c);
        });
        return ResponseEntity.ok().build();
    }
}