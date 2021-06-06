package ch.lucas.bot.cff.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimeFormatterTest {
    // Test minutes
    @Test
    public void testConvertSecondsToTimeWith1Min() {
        Assertions.assertEquals("1 minute", TimeFormatter.convertSecondsToTime(60), "60 seconds must be converted to : 1 minute");
    }

    @Test
    public void testConvertSecondsToTimeWith10Mins() {
        Assertions.assertEquals("10 minutes", TimeFormatter.convertSecondsToTime(600), "600 seconds must be converted to : 10 minutes");
    }

    // Test hours
    @Test
    public void testConvertSecondsToTimeWith1Hour() {
        Assertions.assertEquals("1 heure", TimeFormatter.convertSecondsToTime(3600), "3600 secondes doit être converti en : 1 heure");
    }

    @Test
    public void testConvertSecondsToTimeWith1Hour1Min() {
        Assertions.assertEquals("1 heure, 1 minute", TimeFormatter.convertSecondsToTime(3660), "3660 secondes doit être converti en : 1 heure, 1 minute");
    }

    @Test
    public void testConvertSecondsToTimeWith1Hour30Mins() {
        Assertions.assertEquals("1 heure, 30 minutes", TimeFormatter.convertSecondsToTime(5400), "5400 secondes doit être converti en : 1 heure, 30 minutes");
    }

    @Test
    public void testConvertSecondsToTimeWith2Hours() {
        Assertions.assertEquals("2 heures", TimeFormatter.convertSecondsToTime(7200), "7200 secondes doit être converti en : 2 heures");
    }

    @Test
    public void testConvertSecondsToTimeWith2Hours1Min() {
        Assertions.assertEquals("2 heures, 1 minute", TimeFormatter.convertSecondsToTime(7260), "7260 secondes doit être converti en : 2 heures, 1 minute");
    }

    @Test
    public void testConvertSecondsToTimeWith2Hours6Mins() {
        Assertions.assertEquals("2 heures, 6 minutes", TimeFormatter.convertSecondsToTime(7560), "7560 secondes doit être converti en : 2 heures, 6 minutes");
    }

    // Test days
    @Test
    public void testConvertSecondsToTimeWith1Day() {
        Assertions.assertEquals("1 jour", TimeFormatter.convertSecondsToTime(86400), "86400 secondes doit être converti en : 1 jour");
    }

    @Test
    public void testConvertSecondsToTimeWith1Day1Mins() {
        Assertions.assertEquals("1 jour, 1 minute", TimeFormatter.convertSecondsToTime(86460), "86460 secondes doit être converti en : 1 jour, 1 minute");
    }

    @Test
    public void testConvertSecondsToTimeWith1Day2Mins() {
        Assertions.assertEquals("1 jour, 2 minutes", TimeFormatter.convertSecondsToTime(86520), "86520 secondes doit être converti en : 1 jour, 2  minutes");
    }

    @Test
    public void testConvertSecondsToTimeWith1DayAnd1Hours1Mins() {
        Assertions.assertEquals("1 jour, 1 heure, 1 minute", TimeFormatter.convertSecondsToTime(90060), "86460 secondes doit être converti en : 1 jour, 1 heure, 1 minute");
    }

    @Test
    public void testConvertSecondsToTimeWith1DayAnd2Hours2Mins() {
        Assertions.assertEquals("1 jour, 2 heures, 2 minutes", TimeFormatter.convertSecondsToTime(93720), "86520 secondes doit être converti en : 1 jour, 2 heures, 2 minutes");
    }

    @Test
    public void testConvertSecondsToTimeWith2Days() {
        Assertions.assertEquals("2 jours", TimeFormatter.convertSecondsToTime(172800), "172800 secondes doit être converti en : 2 jours");
    }

    @Test
    public void testConvertSecondsToTimeWith2Days1Hour() {
        Assertions.assertEquals("2 jours, 1 heure", TimeFormatter.convertSecondsToTime(176400), "176400 secondes doit être converti en : 2 jours, 1 heure");
    }

    @Test
    public void testConvertSecondsToTimeWith2Days2Hours() {
        Assertions.assertEquals("2 jours, 2 heures", TimeFormatter.convertSecondsToTime(180000), "180000 secondes doit être converti en : 2 jours, 2 heures");
    }

    @Test
    public void testConvertSecondsToTimeWith2Days1Hour1Mins() {
        Assertions.assertEquals("2 jours, 1 heure, 1 minute", TimeFormatter.convertSecondsToTime(176460), "176460 secondes doit être converti en : 2 jours, 1 heure, 1 minute");
    }

    @Test
    public void testConvertSecondsToTimeWith2Days1Hour2Mins() {
        Assertions.assertEquals("2 jours, 1 heure, 2 minutes", TimeFormatter.convertSecondsToTime(176520), "176520 secondes doit être converti en : 2 jours, 1 heure, 2 minutes");
    }

    @Test
    public void testConvertSecondsToTimeWith2Days2Hours1Mins() {
        Assertions.assertEquals("2 jours, 2 heures, 1 minute", TimeFormatter.convertSecondsToTime(180060), "180060 doit être converti en : 2 jours, 2 heures, 1 minutes");
    }

    @Test
    public void testConvertSecondsToTimeWith2Days2Hours2Mins() {
        Assertions.assertEquals("2 jours, 2 heures, 2 minutes", TimeFormatter.convertSecondsToTime(180120), "180120 doit être converti en : 2 jours, 2 heures, 2 minutes");
    }

    // Test months
    @Test
    public void testConvertSecondsToTime1Month() {
        Assertions.assertEquals("1 mois", TimeFormatter.convertSecondsToTime(2592000));
    }

    @Test
    public void testConvertSecondsToTime1Month1Day() {
        Assertions.assertEquals("1 mois, 1 jour", TimeFormatter.convertSecondsToTime(2678400));
    }

    @Test
    public void testConvertSecondsToTime1Month1Hour() {
        Assertions.assertEquals("1 mois, 1 heure", TimeFormatter.convertSecondsToTime(2595600));
    }

    @Test
    public void testConvertSecondsToTime1Month1Day1Hour() {
        Assertions.assertEquals("1 mois, 1 jour, 1 heure", TimeFormatter.convertSecondsToTime(2682000));
    }

    @Test
    public void testConvertSecondsToTime2Months() {
        Assertions.assertEquals("2 mois", TimeFormatter.convertSecondsToTime(5184000));
    }

    @Test
    public void testConvertSecondsToTime2Months2Days() {
        Assertions.assertEquals("2 mois, 2 jours", TimeFormatter.convertSecondsToTime(5356800));
    }

    @Test
    public void testConvertSecondsToTime2Months2Hours() {
        Assertions.assertEquals("2 mois, 2 heures", TimeFormatter.convertSecondsToTime(5191200));
    }

    @Test
    public void testConvertSecondsToTime2Months2Days2Hours() {
        Assertions.assertEquals("2 mois, 2 jours, 2 heures", TimeFormatter.convertSecondsToTime(5364000));
    }
}
