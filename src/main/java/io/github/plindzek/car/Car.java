package io.github.plindzek.car;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Adam
 *
 */
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer id;
    private String name;
    private boolean lpgPowered;
    private boolean pbPowered;
    private boolean onPowered;
    private double onOn100Km;
    private double lpgOn100Km;
    private double pbOn100Km;

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
