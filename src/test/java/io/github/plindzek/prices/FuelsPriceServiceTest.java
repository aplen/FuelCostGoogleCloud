package io.github.plindzek.prices;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FuelsPriceServiceTest {

    @Autowired
    FuelsPriceService fuelsPriceService;

    @Test
    public void shouldContainFourPrices() {
        Pattern pattern = Pattern.compile("\\d.\\d\\d.*\\d.\\d\\d.*\\d.\\d\\d.*\\d.\\d\\d");

        List<String> prices = fuelsPriceService.getPrices();

        assertThat(prices.toString()).containsPattern(pattern);
    }
}
