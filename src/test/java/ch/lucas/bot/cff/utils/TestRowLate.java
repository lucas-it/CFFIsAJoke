package ch.lucas.bot.cff.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class TestRowLate {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private String recordId = "f9ab33f3d7a39af32e1a1590e3094ab85665f2d5";
    private String stopName = "Basel Bad Bf";
    private int lineId = 5;
    private Date arrivedDate = simpleDateFormat.parse("2020-09-10T10:03:00");
    private Date arrivedprogrammedDate = simpleDateFormat.parse("2020-09-10T09:35:00");
    private RowLate rowLate = new RowLate(recordId, stopName, lineId, arrivedDate, arrivedprogrammedDate);

    public TestRowLate() throws ParseException {}

    @Test
    void testRowLateRecordId() {
        Assertions.assertEquals(recordId, rowLate.getRecordId());
    }

    @Test
    void testRowLateStopName() {
        Assertions.assertEquals(rowLate.getStopName(), stopName);
    }

    @Test
    void testRowLateLineId() {
        Assertions.assertEquals(rowLate.getLineId(), lineId);
    }

    @Test
    void testRowLateArrivedDate() {
        Assertions.assertEquals(rowLate.getArrivedDate(), arrivedDate);
    }

    @Test
    void testRowLateArrivedProgrammedDate() {
        Assertions.assertEquals(rowLate.getArrivedProgrammedDate(), arrivedprogrammedDate);
    }
}
