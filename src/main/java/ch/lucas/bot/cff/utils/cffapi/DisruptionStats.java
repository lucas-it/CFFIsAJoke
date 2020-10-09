package ch.lucas.bot.cff.utils.cffapi;

/**
 * Statistics about disruptions.
 *
 * @author Lucas-it@github
 */
public class DisruptionStats {
    private int numberOfDelayedTravels;
    private long cumulativeLate;

    /**
     * Create a new DisruptionStats with number of delayed travels and the total of delays.
     *
     * @param numberOfDelayedTravels the number of delayed travels
     * @param cumulativeLate the total of delays
     */
    public DisruptionStats(int numberOfDelayedTravels, long cumulativeLate) {
        this.numberOfDelayedTravels = numberOfDelayedTravels;
        this.cumulativeLate = cumulativeLate;
    }

    /**
     * Get the number of delayed travels.
     * @return number of delayed travels
     */
    public int getNumberOfDelayedTravels() {
        return numberOfDelayedTravels;
    }

    /**
     * Set the number of delayed travels.
     * @param numberOfDelayedTravels the number of delayed travels
     */
    public void setNumberOfDelayedTravels(int numberOfDelayedTravels) {
        this.numberOfDelayedTravels = numberOfDelayedTravels;
    }

    /**
     * Get the total delays of travels.
     * @return the total delays
     */
    public long getCumulativeLate() {
        return cumulativeLate;
    }

    /**
     * Set the total delays of travels.
     * @param cumulativeLate total delays of travels
     */
    public void setCumulativeLate(long cumulativeLate) {
        this.cumulativeLate = cumulativeLate;
    }
}
