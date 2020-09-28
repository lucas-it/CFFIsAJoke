package ch.lucas.bot.cff.utils;

import java.util.Date;

public class RowLate {

    private final String recordId;
    private final String stopName;
    private final int lineId;
    private final Date arrivedDate;
    private final Date arrivedProgrammedDate;

    public RowLate(String recordId, String stopName, int lineId, Date arrivedDate, Date arrivedProgrammedDate) {
        this.recordId = recordId;
        this.stopName = stopName;
        this.lineId = lineId;
        this.arrivedDate = arrivedDate;
        this.arrivedProgrammedDate = arrivedProgrammedDate;
    }

    public String getRecordId() {
        return recordId;
    }

    public String getStopName() {
        return stopName;
    }

    public int getLineId() {
        return lineId;
    }

    public Date getArrivedDate() {
        return arrivedDate;
    }

    public Date getArrivedProgrammedDate() {
        return arrivedProgrammedDate;
    }
}
