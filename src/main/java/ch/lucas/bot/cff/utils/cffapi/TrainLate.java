package ch.lucas.bot.cff.utils.cffapi;

import java.util.Date;

/**
 * Store a delay when a train arrives at a station.
 *
 * @author Lucas-it@github
 */
public class TrainLate {

    private final String recordId;
    private final String stopName;
    private final int lineId;
    private final Date arrivedDate;
    private final Date arrivedProgrammedDate;

    /**
     * Create a new RowLate object with information about the train, the station and the time.
     * @param recordId the unique id of the record
     * @param stopName the name of the station
     * @param lineId the unique id of the line
     * @param arrivedDate the time the train arrived at the station
     * @param arrivedProgrammedDate the scheduled arrival time of the train at the station
     */
    public TrainLate(String recordId, String stopName, int lineId, Date arrivedDate, Date arrivedProgrammedDate) {
        this.recordId = recordId;
        this.stopName = stopName;
        this.lineId = lineId;
        this.arrivedDate = arrivedDate;
        this.arrivedProgrammedDate = arrivedProgrammedDate;
    }

    /**
     * Get the record id.
     * @return the record id
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * Get the name of the station.
     * @return the station name
     */
    public String getStopName() {
        return stopName;
    }

    /**
     * Get the line id.
     * @return the line id
     */
    public int getLineId() {
        return lineId;
    }

    /**
     * Get the time the train arrived at the station.
     * @return the arrived date
     */
    public Date getArrivedDate() {
        return arrivedDate;
    }

    /**
     * Get the scheduled arrival time of the train at the station.
     * @return the arrived programmed date
     */
    public Date getArrivedProgrammedDate() {
        return arrivedProgrammedDate;
    }
}
