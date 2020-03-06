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
@RequestMapping("/api")
class CarServlet {

    private final Logger logger = LoggerFactory.getLogger(CarServlet.class);
    private CarRepository repository;

    CarServlet() {

    }

    @GetMapping("/cars")
    ResponseEntity<List<Car>> findAllCars() {
        logger.info("Request got");
        return ResponseEntity.ok(repository.findAll());
    }


    @PostMapping(path = "/cars/*", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        return ResponseEntity.ok(repository.addCar(car));
    }
}
