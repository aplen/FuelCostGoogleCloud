package io.github.plindzek.fuelcost;

import io.github.plindzek.car.Car;

interface FuelCost {

    double calculateFuelCost(Car car, Trip trip);
}
