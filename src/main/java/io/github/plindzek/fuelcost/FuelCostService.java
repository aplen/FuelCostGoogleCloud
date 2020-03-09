package io.github.plindzek.fuelcost;

import io.github.plindzek.car.Car;
import org.springframework.stereotype.Service;

/**
 * @author Adam
 */
@Service
class FuelCostService {

    private FuelCost fuelCost;

    /*
    based on Strategy Pattern
    proper object is created in dependence of which fuel is chosen
     */
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


