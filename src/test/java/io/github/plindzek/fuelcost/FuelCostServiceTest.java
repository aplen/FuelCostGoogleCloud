package io.github.plindzek.fuelcost;

import io.github.plindzek.car.Car;
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

    private Car car=new Car();

    @Test
    public void shouldReturnLpgCostObject() {
        car.setLpgPowered(true);
        assertThat((fuelCostService.selectFuel(car).getClass().getName())).isEqualTo("io.github.plindzek.fuelcost.LpgCost");
    }

    @Test
    public void shouldReturnPbCostObject() {
        car.setPbPowered(true);
        assertThat((fuelCostService.selectFuel(car).getClass().getName())).isEqualTo("io.github.plindzek.fuelcost.PbCost");
    }

    @Test
    public void shouldReturnOnCostObject() {
        car.setOnPowered(true);
        assertThat((fuelCostService.selectFuel(car).getClass().getName())).isEqualTo("io.github.plindzek.fuelcost.OnCost");
    }

    @Test
    public void shouldReturnNoCostObject() {
        assertThat((fuelCostService.selectFuel(car).getClass().getName())).isEqualTo("io.github.plindzek.fuelcost.NoFuelCost");

    }

}
