package io.github.plindzek.prices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
interface FuelsPriceRepository extends JpaRepository {


    List<String> getPrices() {
        List<String> prices = new ArrayList<>();
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
