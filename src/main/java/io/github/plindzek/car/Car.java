package io.github.plindzek.car;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Adam
 */

@Entity
@Table(name = "cars")
public @Data
class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "lpgpowered")
    private boolean lpgPowered;
    @Column(name = "pbpowered")
    private boolean pbPowered;
    @Column(name = "onpowered")
    private boolean onPowered;
    @Column(name = "onon100km")
    private double onOn100Km;
    @Column(name = "lpgon100km")
    private double lpgOn100Km;
    @Column(name = "pbon100km")
    private double pbOn100Km;

    public Car() {
    }

    Car(Integer id, String name, boolean lpgPowered, boolean pbPowered, boolean onPowered, double lpgOn100Km, double pbOn100Km, double onOn100Km) {
        this.id = id;
        this.name = name;
        this.lpgPowered = lpgPowered;
        this.pbPowered = pbPowered;
        this.onPowered = onPowered;
        this.lpgOn100Km = lpgOn100Km;
        this.pbOn100Km = pbOn100Km;
        this.onOn100Km = onOn100Km;
    }

}
