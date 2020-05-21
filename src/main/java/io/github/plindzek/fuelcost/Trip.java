package io.github.plindzek.fuelcost;

import lombok.Data;

/**
 * @author Adam
 * Temporary Trip object created to collect data of fuel prices and trip length
 */
@Data
class Trip {

    private double kmOnLpg;
    private double kmOnPb;
    private double kmOnOn;
    private double lpgPrice;
    private double pbPrice;
    private double onPrice;
}