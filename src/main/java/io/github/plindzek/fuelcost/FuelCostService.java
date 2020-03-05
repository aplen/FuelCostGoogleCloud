package io.github.plindzek.fuelcost;

import io.github.plindzek.car.Car;
import org.springframework.stereotype.Service;

@Service
class FuelCostService {

    private FuelCost fuelCost;

//    private CarRepository repository;
//    FuelCostService(){this(new CarRepository());}
//
//    FuelCostService(CarRepository repository){this.repository=repository;}

    public double calcCost(Car car, Trip trip) {
        if (car.isOnPowered()) {
            fuelCost = new OnCost();
        } else {
            if (car.isLpgPowered()) {
                fuelCost = new LpgCost();
            } else {
                fuelCost = new PbCost();
            }
        }
        return fuelCost.calculateFuelCost(car, trip);
    }
}


