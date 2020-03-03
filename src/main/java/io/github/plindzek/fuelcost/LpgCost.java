package io.github.plindzek.fuelcost;

import io.github.plindzek.car.Car;
import io.github.plindzek.prices.FuelsPriceScrapper;
import org.jetbrains.annotations.NotNull;

/**
 * @author Adam
 */
class LpgCost implements FuelCost {

    @Override
    public double calculateFuelCost(@NotNull Car car, Trip trip) {
       return  ((Math.round((car.getLpgOn100Km() * trip.getLpgPrice()* trip.getKmOnLpg() / 100) * 100.0))
       + (Math.round((car.getPbOn100Km() * trip.getPbPrice()* trip.getKmOnPb() / 100) * 100.0)))
                / 100.0;
    }
}
