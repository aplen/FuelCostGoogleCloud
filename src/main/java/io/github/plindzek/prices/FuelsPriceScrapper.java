/**
 *
 */
package io.github.plindzek.prices;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author Adam
 *
 */
public class FuelsPriceScrapper {


    static Document doc;
    /**
     * download site with actual fuel prices
     * @return
     */
    public static Document getAutocentrum() {
        try {

            doc = Jsoup.connect("https://www.autocentrum.pl/paliwa/ceny-paliw/")
                    .timeout(5000)
                    .get();
        } catch (IOException ole) {

            ole.printStackTrace();
        }
        return doc;
    }

    public static String getAvgLpgPrice() {

        var txtAvgLpgPrice = doc.getElementsByAttributeValueContaining("href",
                "/paliwa/ceny-paliw/lpg/").select("div").text();
        var avgLpgPrice =
                txtAvgLpgPrice.charAt(0) + "." + txtAvgLpgPrice.charAt(2) + txtAvgLpgPrice.charAt(3);
        return avgLpgPrice;

    }

    public static String getAvgPbPrice() {

        var txtAvgPbPrice = doc.getElementsByAttributeValueContaining("href",
                "/paliwa/ceny-paliw/pb/").select("div").text();
        var avgPbPrice =
                txtAvgPbPrice.charAt(0) + "." + txtAvgPbPrice.charAt(2) + txtAvgPbPrice.charAt(3);
        return avgPbPrice;
    }

    public static String getAvgPbPremiumPrice() {

        var txtAvgPbPremiumPrice = doc.getElementsByAttributeValueContaining("href",
                "/paliwa/ceny-paliw/pb-premium").select("div").text();
        var avgPbPremiumPrice =
                txtAvgPbPremiumPrice.charAt(0) + "." + txtAvgPbPremiumPrice.charAt(2) + txtAvgPbPremiumPrice.charAt(3);
        return avgPbPremiumPrice;

    }

    public static String getAvgOnPrice() {

        var txtAvgOnPrice = doc.getElementsByAttributeValueContaining("href",
                "/paliwa/ceny-paliw/on/").select("div").text();
        var avgOnPrice =
                txtAvgOnPrice.charAt(0) + "." + txtAvgOnPrice.charAt(2) + txtAvgOnPrice.charAt(3);
        return avgOnPrice;

    }

}
