package io.github.plindzek;

import io.github.plindzek.prices.FuelsPriceScrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FuelCostApplication {

	public static void main(String[] args) {
		FuelsPriceScrapper.getAutocentrum();
		SpringApplication.run(FuelCostApplication.class, args);
	}

}
