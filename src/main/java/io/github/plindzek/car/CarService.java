package io.github.plindzek.car;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
class CarService {

    private CarRepository carRepository;

    CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
carRepository.save(new Car(4,"Mitsubishi", true, true, false, 0,20,10 ));
    }
}
