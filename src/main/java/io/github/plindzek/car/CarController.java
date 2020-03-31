package io.github.plindzek.car;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import org.jsoup.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

/**
 * @author Adam
 */

@RestController
@RequestMapping("/api/cars")
class CarController {

    private final Logger logger = LoggerFactory.getLogger(CarController.class);
    private CarRepository repository;

    CarController(CarRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<List<Car>> findAllCars() {
        logger.info("Request for list all cars got");
        return ok(repository.findAll());
    }

    @PutMapping("/{id}")
    ResponseEntity<Car> updateCar(@PathVariable Integer id) {
        logger.info("Request for update car got");
        var car = repository.findById(id);
        car.ifPresent(c -> {
            c.setName("NameChanged");
            repository.save(c);
        });
        return car.map(ResponseEntity::ok).orElse(notFound().build());
    }

    @PostMapping
    ResponseEntity<Car> saveCar(@RequestBody Car car) {
        logger.info("Request for add car got");
        return ok(repository.save(car));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Car> deleteCar(@PathVariable Integer id) {
        logger.info("Request for delete car got");
        var car = repository.findById(id);
        ResponseEntity responseEntity;
        if(car.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
        car.ifPresent(c -> {
            repository.delete(c);
        });
        responseEntity = ResponseEntity.ok().build();
        }
        return responseEntity;
    }
}