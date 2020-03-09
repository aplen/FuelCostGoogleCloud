package io.github.plindzek.fuelcost;
import io.github.plindzek.car.Car;
/**
 * @author Adam
 */
public interface FuelCost {

    double calculateFuelCost(Car car, Trip trip);
}
