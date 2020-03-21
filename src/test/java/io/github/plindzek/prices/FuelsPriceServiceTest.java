package io.github.plindzek.prices;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FuelsPriceServiceTest {

    @Autowired
    FuelsPriceService fuelsPriceService;

    @Test
    public void shouldReturnPrice() {
        String tempPrice = "4,20";
        assertThat(fuelsPriceService.pickPrice(tempPrice)).isEqualTo("4.20");
    }
    @Test
    public void shouldReturnDefaultPriceWhenException(){
    assertThat(fuelsPriceService.pickPrice(null)).isEqualTo("0.00");
    }

    @Test
    public void shouldReturnDocument() {

        assertThat(fuelsPriceService.scrapAutocentrum().getClass()).isEqualTo(Document.class);
    }

    @Test
    public void shouldReturnTrueIfEquals() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateOfDownload = dateFormat.format(Calendar.getInstance().getTime());
        assertThat(fuelsPriceService.checkDate(dateOfDownload)).isTrue();
    }

    @Test
    public void shouldContainFourPrices() {
        Pattern pattern = Pattern.compile("\\d.\\d\\d.*\\d.\\d\\d.*\\d.\\d\\d.*\\d.\\d\\d");

        List<String> prices = fuelsPriceService.getPrices();

        assertThat(prices.toString()).containsPattern(pattern);
    }

    @Test
    public void shouldHaveCorrectUrl() {
        assertThat(FuelsPriceService.AUTOCENTRUM_URL).isEqualTo("https://www.autocentrum.pl/paliwa/ceny-paliw/");
    }
}
