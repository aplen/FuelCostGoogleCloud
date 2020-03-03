package io.github.plindzek.fuelcost;

import io.github.plindzek.car.Car;
import io.github.plindzek.prices.FuelsPriceScrapper;

/**
 * @author Adam
 */
class OnCost implements FuelCost {

    @Override
    public double calculateFuelCost(Car car, Trip trip) {
        return  (Math.round((car.getOnOn100Km() * trip.getOnPrice()* trip.getKmOnOn() / 100) * 100.0))
                / 100.0;
    }
}
