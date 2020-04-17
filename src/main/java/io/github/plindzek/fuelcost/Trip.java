package io.github.plindzek.fuelcost;

import lombok.*;

@Data
class Trip {

    private double kmOnLpg;
    private double kmOnPb;
    private double kmOnOn;
    private double lpgPrice;
    private double pbPrice;
    private double onPrice;

}
