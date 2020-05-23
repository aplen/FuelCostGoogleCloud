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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser("user")
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
        String json = new ObjectMapper().writeValueAsString(trip);

        when(fuelCostService.calcCost(anyInt(), any(Trip.class))).thenReturn(12.05);

        mockMvc
                .perform(post("/api/fuelcost/{id}", 43)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk());

        verify(fuelCostService, times(1)).calcCost(anyInt(), any(Trip.class));
    }
}