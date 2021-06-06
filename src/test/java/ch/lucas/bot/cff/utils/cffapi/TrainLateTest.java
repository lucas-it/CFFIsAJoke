package ch.lucas.bot.cff.utils.cffapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrainLateTest {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private String recordId = "f9ab33f3d7a39af32e1a1590e3094ab85665f2d5";
    private String stopName = "Basel Bad Bf";
    private int lineId = 5;
    private Date arrivedDate = simpleDateFormat.parse("2020-09-10T10:03:00");
    private Date arrivedprogrammedDate = simpleDateFormat.parse("2020-09-10T09:35:00");
    private TrainLate trainLate = new TrainLate(recordId, stopName, lineId, arrivedDate, arrivedprogrammedDate);

    public TrainLateTest() throws ParseException {}

    @Test
    public void testRowLateRecordId() {
        Assertions.assertEquals(recordId, trainLate.getRecordId());
    }

    @Test
    public void testRowLateStopName() {
        Assertions.assertEquals(stopName, trainLate.getStopName());
    }

    @Test
    public void testRowLateLineId() {
        Assertions.assertEquals(lineId, trainLate.getLineId());
    }

    @Test
    public void testRowLateArrivedDate() {
        Assertions.assertEquals(arrivedDate, trainLate.getArrivedDate());
    }

    @Test
    public void testRowLateArrivedProgrammedDate() {
        Assertions.assertEquals(arrivedprogrammedDate, trainLate.getArrivedProgrammedDate());
    }
}
