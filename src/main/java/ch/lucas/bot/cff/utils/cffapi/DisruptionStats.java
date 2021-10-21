package ch.lucas.bot.cff.utils.cffapi;

/**
 * Statistics about disruptions.
 *
 * @author Lucas-it@github
 */
public class DisruptionStats {
    private int numberOfDelayedTravels;
    private long averageDelayPerTrain;
    private long cumulativeLate;

    /**
     * Create a new DisruptionStats with number of delayed travels and the total of delays.
     *
     * @param numberOfDelayedTravels the number of delayed travels
     * @param averageDelayPerTrain the average delay per train in milliseconds
     * @param cumulativeLate the total of delays in milliseconds
     */
    public DisruptionStats(int numberOfDelayedTravels, long averageDelayPerTrain, long cumulativeLate) {
        this.numberOfDelayedTravels = numberOfDelayedTravels;
        this.cumulativeLate = cumulativeLate;
        this.averageDelayPerTrain = averageDelayPerTrain;
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
     * @return the total delays in milliseconds
     */
    public long getCumulativeLate() {
        return cumulativeLate;
    }

    /**
     * Set the total delays of travels.
     * @param cumulativeLate total delays of travels in milliseconds
     */
    public void setCumulativeLate(long cumulativeLate) {
        this.cumulativeLate = cumulativeLate;
    }

    /**
     * Get the average delay per train
     * @return average delay per train in milliseconds
     */
    public long getAverageDelayPerTrain() {
        return averageDelayPerTrain;
    }

    /**
     * Set the average delay per train.
     * @param averageDelayPerTrain average delay per train in milliseconds
     */
    public void setAverageDelayPerTrain(int averageDelayPerTrain) {
        this.averageDelayPerTrain = averageDelayPerTrain;
    }
}
