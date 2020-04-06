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
public Car save(Car car){
        return carRepository.save(car);
}
@EventListener(ApplicationReadyEvent.class)
    public void fillDB(){
save(new Car(4,"Mitsubishi", true, true, false, 0,20,10 ));
    }
}
