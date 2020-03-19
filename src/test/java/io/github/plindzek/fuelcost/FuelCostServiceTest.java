package io.github.plindzek.fuelcost;

import io.github.plindzek.car.Car;
import io.github.plindzek.car.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FuelCostServiceTest {

    @Autowired
    private FuelCostService fuelCostService;
    @Autowired
    private CarRepository carRepository;
    private Car mockCar =new Car();
    private Trip mockTrip = new Trip();

    @Test
    public void shouldReturnResult(){
mockTrip.setLpgPrice(10);
mockTrip.setKmOnLpg(100);
        assertThat(fuelCostService.calcCost(1, mockTrip)).isEqualTo(105);
    }

    @Test
    public void shouldReturnLpgCostObject() {

        mockCar.setLpgPowered(true);
        assertThat((fuelCostService.selectFuel(mockCar).getClass().getName())).isEqualTo("io.github.plindzek.fuelcost.LpgCost");
    }

    @Test
    public void shouldReturnPbCostObject() {
        mockCar.setPbPowered(true);
        assertThat((fuelCostService.selectFuel(mockCar).getClass().getName())).isEqualTo("io.github.plindzek.fuelcost.PbCost");
    }

    @Test
    public void shouldReturnOnCostObject() {
        mockCar.setOnPowered(true);
        assertThat((fuelCostService.selectFuel(mockCar).getClass().getName())).isEqualTo("io.github.plindzek.fuelcost.OnCost");
    }

    @Test
    public void shouldReturnNoCostObject() {
        assertThat((fuelCostService.selectFuel(mockCar).getClass().getName())).isEqualTo("io.github.plindzek.fuelcost.NoFuelCost");

    }

}
