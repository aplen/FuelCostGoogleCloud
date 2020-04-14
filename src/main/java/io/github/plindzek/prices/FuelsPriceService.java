package io.github.plindzek.prices;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * return actual fuel prices
 * @author Adam
 */

@Service
class FuelsPriceService {
    private final Logger LOGGER = LoggerFactory.getLogger(FuelsPriceService.class);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private String dateOfDownload;
    private boolean freshDataGet = false;
    private List<String> prices = new ArrayList<>();
    protected static final String AUTOCENTRUM_URL = "https://www.autocentrum.pl/paliwa/ceny-paliw/";

    List<String> getPrices() {
        checkDate(dateOfDownload);
        if (!freshDataGet) {
            Document scrappedHtml = scrapAutocentrum();
            prices.clear();

            prices.add(" Cena LPG: ");
            prices.add(pickPrice(scrappedHtml.getElementsByAttributeValueContaining("href",
                    "/paliwa/ceny-paliw/lpg/").select("div").text()));

            prices.add(" Cena PB: ");
            prices.add(pickPrice(scrappedHtml.getElementsByAttributeValueContaining("href",
                    "/paliwa/ceny-paliw/pb/").select("div").text()));

            prices.add(" Cena PB Premium: ");
            prices.add(pickPrice(scrappedHtml.getElementsByAttributeValueContaining("href",
                    "/paliwa/ceny-paliw/pb-premium").select("div").text()));

            prices.add(" Cena ON: ");
            prices.add(pickPrice(scrappedHtml.getElementsByAttributeValueContaining("href",
                    "/paliwa/ceny-paliw/on/").select("div").text()));
        }
        return prices;
    }

    boolean checkDate(String dateOfDownload) {

        String dateNow = dateFormat.format(Calendar.getInstance().getTime());
        if (dateNow.equals(dateOfDownload)) {
            freshDataGet = true;
        } else {
            freshDataGet = false;
        }
        return freshDataGet;
    }

    Document scrapAutocentrum() {
        Document scrappedHtml = new Document(AUTOCENTRUM_URL);
        try {
            scrappedHtml = Jsoup.connect(AUTOCENTRUM_URL)
                    .timeout(5000)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("Data download error");
        }
        LOGGER.info("Data downloaded successfully!");
        dateOfDownload = dateFormat.format(Calendar.getInstance().getTime());
        return scrappedHtml;
    }

    String pickPrice(String tempPrice) {
        String price = "0.00";
        try {
            price = tempPrice.charAt(0) + "." + tempPrice.charAt(2) + tempPrice.charAt(3);
            freshDataGet = true;
        } catch (Exception e) {
            LOGGER.error("Price not found");
        }
        return price;
    }
}