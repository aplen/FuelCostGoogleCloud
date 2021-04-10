package io.github.plindzek.car;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    private String username;
    @NotNull
    @Column(name = "lpgpowered")
    private boolean lpgPowered;
    @NotNull
    @Column(name = "pbpowered")
    private boolean pbPowered;
    @NotNull
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

    Car(Integer id, String name, String username, boolean lpgPowered, boolean pbPowered, boolean onPowered, double lpgOn100Km, double pbOn100Km, double onOn100Km) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.lpgPowered = lpgPowered;
        this.pbPowered = pbPowered;
        this.onPowered = onPowered;
        this.lpgOn100Km = lpgOn100Km;
        this.pbOn100Km = pbOn100Km;
        this.onOn100Km = onOn100Km;
    }

}
