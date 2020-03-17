package io.github.plindzek.fuelcost;

import io.github.plindzek.car.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FuelCostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //@Autowired to call service methods or @MockBean to use mocked service
    @MockBean
    private FuelCostService fuelCostService;



    @Test
    public void shouldReturnExpectedStatus() throws Exception {
        var car = new Car();
        var trip = new Trip();
        //service mocking
        when(fuelCostService.calcCost(car, trip)).thenReturn(100.00);

        mockMvc
                .perform(get("/api/fuelcost/{id}", anyInt()))
                .andDo(print())
                .andExpect(status().isOk());
    }



}


