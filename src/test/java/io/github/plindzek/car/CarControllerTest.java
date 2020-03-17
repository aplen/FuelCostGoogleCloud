package io.github.plindzek.car;

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
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //@Autowired to call service methods or @MockBean to use mocked service
    @MockBean
    private CarRepository carRepository;

//    @Test
//    public void shouldReturnExpectedText() throws Exception {
//        //service mocking
//        when(carRepository).thenReturn("Siema" + "<br />" + "kitty" + "!");
//
//        mockMvc
//                .perform(get("/api"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Siema" + "<br />" + "kitty" + "!")));
//    }
//
//    @Test
//    public void shouldReturnExpectedTextWithParameters() throws Exception {
//        when(carRepository.prepareGreeting("Adam", 1)).thenReturn("Siema<br />Adam!");
//
//        mockMvc
//                .perform(get("/api")
//                        .param("lang", "1")
//                        .param("name", "Adam"))
//                .andDo(print())
//                .andExpect(status().isOk())
//
//                .andExpect(content().string(containsString("Siema<br />Adam!")));
////checks whether the mock has been called the specified number of times
//        verify(carRepository, times(1)).prepareGreeting(anyString(), anyInt());
//
//    }

}


