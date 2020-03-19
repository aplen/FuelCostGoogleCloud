package io.github.plindzek.prices;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
class FuelsPriceService {

    private final Logger logger = LoggerFactory.getLogger(io.github.plindzek.prices.FuelsPriceService.class);
    private Document doc;
    private String tempPrice;
    private List<String> prices = new ArrayList<>();

    List<String> getPrices() {

        try {
            doc = Jsoup.connect("https://www.autocentrum.pl/paliwa/ceny-paliw/")
                    .timeout(5000)
                    .get();
            logger.info("Prices loaded successfully!");
        } catch (IOException ole) {

            ole.printStackTrace();
            logger.error("Prices loading error!");
        }

        tempPrice = doc.getElementsByAttributeValueContaining("href",
                "/paliwa/ceny-paliw/lpg/").select("div").text();
        prices.add(" Cena LPG: ");
        prices.add(tempPrice.charAt(0) + "." + tempPrice.charAt(2) + tempPrice.charAt(3));

        tempPrice = doc.getElementsByAttributeValueContaining("href",
                "/paliwa/ceny-paliw/pb/").select("div").text();
        prices.add(" Cena PB: ");
        prices.add(tempPrice.charAt(0) + "." + tempPrice.charAt(2) + tempPrice.charAt(3));

        tempPrice = doc.getElementsByAttributeValueContaining("href",
                "/paliwa/ceny-paliw/pb-premium").select("div").text();
        prices.add(" Cena PB Premium: ");
        prices.add(tempPrice.charAt(0) + "." + tempPrice.charAt(2) + tempPrice.charAt(3));

        tempPrice = doc.getElementsByAttributeValueContaining("href",
                "/paliwa/ceny-paliw/on/").select("div").text();
        prices.add(" Cena ON: ");
        prices.add(tempPrice.charAt(0) + "." + tempPrice.charAt(2) + tempPrice.charAt(3));

        return prices;
    }
}