package io.github.plindzek.fuelcost;

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
    private FuelCostService fuelCostService;

    FuelCostController(FuelCostService fuelCostService) {
        this.fuelCostService = fuelCostService;
    }

@PostMapping("/{id}")
    ResponseEntity<Double> getResult(@PathVariable Integer id, @RequestBody Trip trip){

    logger.info("Request got with car id " +id+ " and " + trip);
    return ResponseEntity.ok(fuelCostService.calcCost(id, trip));
        }
    }

