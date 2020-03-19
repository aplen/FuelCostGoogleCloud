package io.github.plindzek.prices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * return actual fuel prices
 * @author Adam
 */
@RestController
@RequestMapping("/api/prices")
class FuelsPriceController {

    private final Logger logger = LoggerFactory.getLogger(FuelsPriceController.class);
    private FuelsPriceService fuelsPriceService;

    FuelsPriceController(FuelsPriceService fuelsPriceService) {
        this.fuelsPriceService = fuelsPriceService;
    }

    @GetMapping
    ResponseEntity<List<String>> getPrices() {
        logger.info("Request for prices got");
        return ResponseEntity.ok(fuelsPriceService.getPrices());
    }
}