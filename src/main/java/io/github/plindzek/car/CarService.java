package io.github.plindzek.car;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
/**
 * @author Adam
 * Car Service with example of automatic
 * fill database at app startup
 * other Car objects are created with migrations, managed by Flyway
 */

@Service
class CarService {

    private CarRepository carRepository;

    CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
carRepository.save(new Car(4,"Mitsubishi","default user" ,true, true, false, 0,20,10 ));
    }
}
