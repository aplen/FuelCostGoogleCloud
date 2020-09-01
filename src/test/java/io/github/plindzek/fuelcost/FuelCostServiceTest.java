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



}
