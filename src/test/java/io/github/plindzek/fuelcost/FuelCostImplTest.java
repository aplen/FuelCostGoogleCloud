package io.github.plindzek.fuelcost;

import io.github.plindzek.car.Car;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FuelCostImplTest {
    FuelCost fuelCost;

    Car car = new Car();
    Trip trip = new Trip();

    @Test
    public void shouldReturnZeroValue(){
        fuelCost = new NoFuelCost();
        assertThat(fuelCost.calculateFuelCost(car, trip)).isEqualTo(0.00);
    }

    @Test
    public void shouldReturnLpgCostValue(){
        fuelCost = new LpgCost();
        car.setLpgOn100Km(10.00);
        trip.setLpgPrice(2.00);
        trip.setKmOnLpg(100.00);
        assertThat(fuelCost.calculateFuelCost(car, trip)).isEqualTo(20.00);
    }
    @Test
    public void shouldReturnOnCostValue(){
        fuelCost = new OnCost();
        car.setOnOn100Km(10.00);
        trip.setOnPrice(2.00);
        trip.setKmOnOn(100.00);
        assertThat(fuelCost.calculateFuelCost(car, trip)).isEqualTo(20.00);
    }
    @Test
    public void shouldReturnPbCostValue(){
        fuelCost = new PbCost();
        car.setPbOn100Km(10.00);
        trip.setPbPrice(2.00);
        trip.setKmOnPb(100.00);
        assertThat(fuelCost.calculateFuelCost(car, trip)).isEqualTo(20.00);
    }

  }
