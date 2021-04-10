package io.github.plindzek.fuelcost;

import io.github.plindzek.car.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Adam
 * Service receives data needed to calc fuel cost and returns result
 */
@Service
class FuelCostService {
    private final Logger logger = LoggerFactory.getLogger(FuelCostService.class);
    private final CarRepository carRepository;
    FuelCost fuelCost;

    FuelCostService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    double calcCost(Integer id, Trip trip) {
        logger.info("Request got with car id " + id + " and " + trip);
        var car = carRepository.findById(id).orElse(null);


            if (car.isOnPowered()) {
fuelCost = ()-> (Math.round((car.getOnOn100Km() * trip.getOnPrice()* trip.getKmOnOn() / 100) * 100.0))
        / 100.0;
            } else if (car.isLpgPowered()) {
              fuelCost = ()->  ((Math.round((car.getLpgOn100Km() * trip.getLpgPrice()* trip.getKmOnLpg() / 100) * 100.0))
                        + (Math.round((car.getPbOn100Km() * trip.getPbPrice()* trip.getKmOnPb() / 100) * 100.0)))
                        / 100.0;


            } else if (car.isPbPowered()) {
                fuelCost = ()->(Math.round((car.getPbOn100Km() * trip.getPbPrice()* trip.getKmOnPb() / 100) * 100.0))
                        / 100.0;
            } else {
                fuelCost = ()->0.00;
            }

                   return fuelCost.calculateFuelCost(); }

    }





