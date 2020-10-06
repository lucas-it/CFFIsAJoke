package ch.lucas.bot.cff.utils.cffapi;

public class DisruptionStats {
    private int numberOfDelayedTravels;
    private long cumulativeLate;

    public DisruptionStats(int numberOfDelayedTravels, long cumulativeLate) {
        this.numberOfDelayedTravels = numberOfDelayedTravels;
        this.cumulativeLate = cumulativeLate;
    }

    public int getNumberOfDelayedTravels() {
        return numberOfDelayedTravels;
    }

    public void setNumberOfDelayedTravels(int numberOfDelayedTravels) {
        this.numberOfDelayedTravels = numberOfDelayedTravels;
    }

    public long getCumulativeLate() {
        return cumulativeLate;
    }

    public void setCumulativeLate(long cumulativeLate) {
        this.cumulativeLate = cumulativeLate;
    }
}
