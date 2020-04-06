package io.github.plindzek.car;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Adam
 */
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(generator = "inc")
    //@GenericGenerator(name = "inc", strategy = "increment")
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

    @Override
    public String toString() {
        return "Car [" + name + "]";
    }

    public boolean isPbPowered() {
        return pbPowered;
    }

    public void setPbPowered(boolean pbPowered) {
        this.pbPowered = pbPowered;
    }

    public boolean isOnPowered() {
        return onPowered;
    }

    public void setOnPowered(boolean onPowered) {
        this.onPowered = onPowered;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLpgPowered() {
        return lpgPowered;
    }

    public void setLpgPowered(boolean lpgPowered) {
        this.lpgPowered = lpgPowered;
    }

    public double getOnOn100Km() {
        return onOn100Km;
    }

    public void setOnOn100Km(double onOn100Km) {
        this.onOn100Km = onOn100Km;
    }

    public double getLpgOn100Km() {
        return lpgOn100Km;
    }

    public void setLpgOn100Km(double lpgOn100Km) {
        this.lpgOn100Km = lpgOn100Km;
    }

    public double getPbOn100Km() {
        return pbOn100Km;
    }

    public void setPbOn100Km(double pbOn100Km) {
        this.pbOn100Km = pbOn100Km;
    }

}
