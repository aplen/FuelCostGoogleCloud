package io.github.plindzek.fuelcost;

import io.github.plindzek.car.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Adam
 */
@RestController
@RequestMapping("/api/fuelcost")
class FuelCostController {

    private final Logger logger = LoggerFactory.getLogger(FuelCostController.class);
    private FuelCostService service;
    private CarRepository repository;

    FuelCostController(FuelCostService service, CarRepository repository) {
        this.service = service;
        this.repository = repository;

    }
@PostMapping("/{id}")
    ResponseEntity<Double> getResult(@PathVariable Integer id, @RequestBody Trip trip){
    logger.info("Request got with path info");
    var car = repository.findById(id).orElse(null);
    return ResponseEntity.ok(service.calcCost(car, trip));
        }
    }

