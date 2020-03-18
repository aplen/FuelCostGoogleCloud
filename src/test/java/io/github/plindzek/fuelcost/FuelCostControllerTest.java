package io.github.plindzek.fuelcost;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.plindzek.car.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FuelCostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FuelCostService fuelCostService;
    @MockBean
    private CarRepository carRepository;
    private Trip trip;

    @Test
    public void shouldReturnExpectedStatus() throws Exception {
        trip = new Trip();
        trip.setKmOnLpg(10);
        trip.setLpgPrice(2);
        String json = new ObjectMapper().writeValueAsString(trip);

        when(fuelCostService.calcCost(anyInt(), any(Trip.class))).thenReturn(66.25);

        mockMvc
                .perform(post("/api/fuelcost/{id}", 100)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());

        verify(fuelCostService, times(1)).calcCost(anyInt(), any(Trip.class));
    }
}