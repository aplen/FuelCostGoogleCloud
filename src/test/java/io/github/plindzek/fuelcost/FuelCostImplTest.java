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
        assertThat(fuelCost.calculateFuelCost()).isEqualTo(0.00);
    }

    @Test
    public void shouldReturnLpgCostValue(){
        car.setLpgOn100Km(10.00);
        trip.setLpgPrice(2.00);
        trip.setKmOnLpg(100.00);
        assertThat(fuelCost.calculateFuelCost()).isEqualTo(20.00);
    }
    @Test
    public void shouldReturnOnCostValue(){
        car.setOnOn100Km(10.00);
        trip.setOnPrice(2.00);
        trip.setKmOnOn(100.00);
        assertThat(fuelCost.calculateFuelCost()).isEqualTo(20.00);
    }
    @Test
    public void shouldReturnPbCostValue(){
        car.setPbOn100Km(10.00);
        trip.setPbPrice(2.00);
        trip.setKmOnPb(100.00);
        assertThat(fuelCost.calculateFuelCost()).isEqualTo(20.00);
    }

  }
