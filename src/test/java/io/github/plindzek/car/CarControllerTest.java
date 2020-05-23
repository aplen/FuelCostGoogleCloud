package io.github.plindzek.car;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser("user")
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarRepository carRepository;
    Car car1;

    @Before
    public void initialize() {
        car1 = new Car();
        car1.setName("car1");

        var car = new Car();
        car.setId(1);
        car.setName("Test");
        List<Car> cars = new ArrayList();
        cars.add(car);

        when(carRepository.findAll()).thenReturn(cars);
        when(carRepository.findById(1)).thenReturn(Optional.of(car));
        when(carRepository.findById(43)).thenReturn(Optional.empty());
        when(carRepository.save(ArgumentMatchers.any())).thenReturn(car1);
    }

    @Test
    public void ShouldReturnAddedCar() throws Exception {
        var json = new ObjectMapper().writeValueAsString(car1);

        mockMvc
                .perform(post("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(jsonPath("name", containsString("car1")))
                .andExpect(status().is(201));
    }

    @Test
    public void ShouldReturnJsonWithCars() throws Exception {
        mockMvc
                .perform(get("/api/cars"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("[0].name", containsString("Test")));
    }

    @Test
    public void ShouldReturn_404() throws Exception {
        mockMvc
                .perform(put("/api/cars/{id}", 43))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void ShouldReturnUpdatedCarName() throws Exception {
        mockMvc
                .perform(put("/api/cars/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("name", containsString("NameChanged")));
    }


    @Test
    public void ShouldReturnStatus200() throws Exception {
        mockMvc
                .perform(delete("/api/cars/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void ShouldReturn404() throws Exception {
        mockMvc
                .perform(delete("/api/cars/{id}", 43))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}