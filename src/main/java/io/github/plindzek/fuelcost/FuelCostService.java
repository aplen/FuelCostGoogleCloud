package io.github.plindzek.fuelcost;

import io.github.plindzek.car.Car;
import io.github.plindzek.car.CarRepository;
import org.springframework.stereotype.Service;

/**
 * @author Adam
 */
@Service
class FuelCostService {

    private CarRepository carRepository;
    private FuelCost fuelCost;

    FuelCostService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    double calcCost(Integer id, Trip trip) {
      var car = carRepository.findById(id).orElse(null);
      this.selectFuel(car);
        return fuelCost.calculateFuelCost(car, trip);
    }

    /*
  based on Strategy Pattern
  proper object is created in dependence of which fuel is chosen
   */
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


