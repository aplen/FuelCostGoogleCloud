package io.github.plindzek.prices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FuelsPriceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FuelsPriceService fuelsPriceService;

    @Test
    public void shouldReturnExpectedStatus() throws Exception {

        List<String> prices = new ArrayList();
        prices.add("Test");
        when(fuelsPriceService.getPrices()).thenReturn(prices);

        mockMvc
                .perform(get("/api/prices"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}