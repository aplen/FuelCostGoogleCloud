package io.github.plindzek.fuelcost;

import io.github.plindzek.car.Car;

/**
 * @author Adam
 */
class NoFuelCost implements FuelCost {

    @Override
    public double calculateFuelCost(Car car, Trip trip) {
        return 0.00;
    }
}
