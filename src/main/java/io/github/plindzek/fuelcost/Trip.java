package io.github.plindzek.fuelcost;

class Trip {

    private double kmOnLpg;
    private double kmOnPb;
    private double kmOnOn;
    private double lpgPrice;
    private double pbPrice;
    private double onPrice;

    double getLpgPrice() {
        return lpgPrice;
    }

    void setLpgPrice(double lpgPrice) {
        this.lpgPrice = lpgPrice;
    }

    double getPbPrice() {
        return pbPrice;
    }

    void setPbPrice(double pbPrice) {
        this.pbPrice = pbPrice;
    }

    double getOnPrice() {
        return onPrice;
    }

    void setOnPrice(double onPrice) {
        this.onPrice = onPrice;
    }

    public double getKmOnLpg() {
        return kmOnLpg;
    }

    public void setKmOnLpg(double kmOnLpg) {
        this.kmOnLpg = kmOnLpg;

    }

    public double getKmOnPb() {
        return kmOnPb;
    }

    public void setKmOnPb(double kmOnPb) {
        this.kmOnPb = kmOnPb;
    }

    public double getKmOnOn() {
        return kmOnOn;
    }

    public void setKmOnOn(double kmOnOn) {
        this.kmOnOn = kmOnOn;
    }
}
