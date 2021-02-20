package io.github.plindzek.fuelcost;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author Adam
 * Temporary Trip object created to collect data of fuel prices and trip length
 */
@Data
class Trip {

    private double kmOnLpg;
    //@Pattern(regexp = "[0-9]{0,8}\\.{0,1}[0-9]{0,8}$")
    private double kmOnPb;
    private double kmOnOn;
    private double lpgPrice;
    private double pbPrice;
    private double onPrice;
}