package io.github.plindzek.fuelcost;

class Trip {

    private double kmOnLpg;
    private double kmOnPb;
    private double kmOnOn;
    private double lpgPrice;
    private double pbPrice;
    private double onPrice;

    public double getLpgPrice() {
        return lpgPrice;
    }

    public void setLpgPrice(double lpgPrice) {
        this.lpgPrice = lpgPrice;
    }

    public double getPbPrice() {
        return pbPrice;
    }

    public void setPbPrice(double pbPrice) {
        this.pbPrice = pbPrice;
    }

    public double getOnPrice() {
        return onPrice;
    }

    public void setOnPrice(double onPrice) {
        this.onPrice = onPrice;
    }

    public double getKmOnLpg() {
        return kmOnLpg;
    }

    public void setKmOnLpg(double kmOnLpg) { this.kmOnLpg = kmOnLpg; }

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

    @Override
    public String toString() {
        return "Trip{" +
                "kmOnLpg=" + kmOnLpg +
                ", kmOnPb=" + kmOnPb +
                ", kmOnOn=" + kmOnOn +
                ", lpgPrice=" + lpgPrice +
                ", pbPrice=" + pbPrice +
                ", onPrice=" + onPrice +
                '}';
    }
}
