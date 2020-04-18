package io.github.plindzek.fuelcost;

import io.github.plindzek.car.Car;
import io.github.plindzek.car.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Adam
 * Service receives data needed to calc fuel cost and returns result
 * Strategy Pattern is used to create proper object in dependence of which fuel is chosen
 */
@Service
class FuelCostService {
    private final Logger logger = LoggerFactory.getLogger(FuelCostService.class);
    private CarRepository carRepository;
    private FuelCost fuelCost;

    FuelCostService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    double calcCost(Integer id, Trip trip) {
        logger.info("Request got with car id " + id + " and " + trip);
        var car = carRepository.findById(id).orElse(null);
        selectFuel(car);
        return fuelCost.calculateFuelCost(car, trip);
    }

    FuelCost selectFuel(Car car) {
        if (car.isOnPowered()) {
            fuelCost = new OnCost();
        } else if (car.isLpgPowered()) {
            fuelCost = new LpgCost();
        } else if (car.isPbPowered()) {
            fuelCost = new PbCost();
        } else {
            fuelCost = new NoFuelCost();
        }
        return fuelCost;
    }
}


