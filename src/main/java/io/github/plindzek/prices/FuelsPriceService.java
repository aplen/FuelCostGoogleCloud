package io.github.plindzek.prices;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class FuelsPriceService {

    List<String> prices;

    List<String> getPrices() {
        prices = new ArrayList<>();
        prices.add(" Cena LPG: ");
        prices.add(FuelsPriceScrapper.getAvgLpgPrice());
        prices.add(" Cena PB: ");
        prices.add(FuelsPriceScrapper.getAvgPbPrice());
        prices.add(" Cena PB Premium: ");
        prices.add(FuelsPriceScrapper.getAvgPbPremiumPrice());
        prices.add(" Cena ON: ");
        prices.add(FuelsPriceScrapper.getAvgOnPrice());
        return prices;
    }



}
