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


    private FuelCostService fuelCostService;

    FuelCostController(FuelCostService fuelCostService) {
        this.fuelCostService = fuelCostService;
    }

    @PostMapping("/{id}")
    ResponseEntity<Double> getResult(@PathVariable Integer id, @RequestBody Trip trip) {
        return ResponseEntity.ok(fuelCostService.calcCost(id, trip));
    }
}

