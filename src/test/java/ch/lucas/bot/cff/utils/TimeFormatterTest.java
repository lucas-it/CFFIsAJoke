package ch.lucas.bot.cff.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;

public class TimeFormatterTest {
    // Test minutes
    @Test
    public void testConvertSecondsToTimeWith1MinFR() {
        Assertions.assertEquals("1 minute", TimeFormatter.convertSecondsToTime(60, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTimeWith10MinsFR() {
        Assertions.assertEquals("10 minutes", TimeFormatter.convertSecondsToTime(600, Locale.FRENCH));
    }

    // Test hours
    @Test
    public void testConvertSecondsToTimeWith1HourFR() {
        Assertions.assertEquals("1 heure", TimeFormatter.convertSecondsToTime(3600, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTimeWith1Hour1MinFR() {
        Assertions.assertEquals("1 heure, 1 minute", TimeFormatter.convertSecondsToTime(3660, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTimeWith1Hour30MinsFR() {
        Assertions.assertEquals("1 heure, 30 minutes", TimeFormatter.convertSecondsToTime(5400, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTimeWith2HoursFR() {
        Assertions.assertEquals("2 heures", TimeFormatter.convertSecondsToTime(7200, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTimeWith2Hours1MinFR() {
        Assertions.assertEquals("2 heures, 1 minute", TimeFormatter.convertSecondsToTime(7260, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTimeWith2Hours6MinsFR() {
        Assertions.assertEquals("2 heures, 6 minutes", TimeFormatter.convertSecondsToTime(7560, Locale.FRENCH));
    }

    // Test days
    @Test
    public void testConvertSecondsToTimeWith1DayFR() {
        Assertions.assertEquals("1 jour", TimeFormatter.convertSecondsToTime(86400, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTimeWith1Day1MinsFR() {
        Assertions.assertEquals("1 jour, 1 minute", TimeFormatter.convertSecondsToTime(86460, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTimeWith1Day2MinsFR() {
        Assertions.assertEquals("1 jour, 2 minutes", TimeFormatter.convertSecondsToTime(86520, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTimeWith1DayAnd1Hours1MinsFR() {
        Assertions.assertEquals("1 jour, 1 heure, 1 minute", TimeFormatter.convertSecondsToTime(90060, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTimeWith1DayAnd2Hours2MinsFR() {
        Assertions.assertEquals("1 jour, 2 heures, 2 minutes", TimeFormatter.convertSecondsToTime(93720, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTimeWith2DaysFR() {
        Assertions.assertEquals("2 jours", TimeFormatter.convertSecondsToTime(172800, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTimeWith2Days1HourFR() {
        Assertions.assertEquals("2 jours, 1 heure", TimeFormatter.convertSecondsToTime(176400, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTimeWith2Days2HoursFR() {
        Assertions.assertEquals("2 jours, 2 heures", TimeFormatter.convertSecondsToTime(180000, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTimeWith2Days1Hour1MinsFR() {
        Assertions.assertEquals("2 jours, 1 heure, 1 minute", TimeFormatter.convertSecondsToTime(176460, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTimeWith2Days1Hour2MinsFR() {
        Assertions.assertEquals("2 jours, 1 heure, 2 minutes", TimeFormatter.convertSecondsToTime(176520, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTimeWith2Days2Hours1MinsFR() {
        Assertions.assertEquals("2 jours, 2 heures, 1 minute", TimeFormatter.convertSecondsToTime(180060, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTimeWith2Days2Hours2MinsFR() {
        Assertions.assertEquals("2 jours, 2 heures, 2 minutes", TimeFormatter.convertSecondsToTime(180120, Locale.FRENCH));
    }

    // Test months
    @Test
    public void testConvertSecondsToTime1MonthFR() {
        Assertions.assertEquals("1 mois", TimeFormatter.convertSecondsToTime(2592000, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTime1Month1DayFR() {
        Assertions.assertEquals("1 mois, 1 jour", TimeFormatter.convertSecondsToTime(2678400, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTime1Month1HourFR() {
        Assertions.assertEquals("1 mois, 1 heure", TimeFormatter.convertSecondsToTime(2595600, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTime1Month1Day1HourFR() {
        Assertions.assertEquals("1 mois, 1 jour, 1 heure", TimeFormatter.convertSecondsToTime(2682000, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTime2MonthsFR() {
        Assertions.assertEquals("2 mois", TimeFormatter.convertSecondsToTime(5184000, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTime2Months2DaysFR() {
        Assertions.assertEquals("2 mois, 2 jours", TimeFormatter.convertSecondsToTime(5356800, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTime2Months2HoursFR() {
        Assertions.assertEquals("2 mois, 2 heures", TimeFormatter.convertSecondsToTime(5191200, Locale.FRENCH));
    }

    @Test
    public void testConvertSecondsToTime2Months2Days2HoursFR() {
        Assertions.assertEquals("2 mois, 2 jours, 2 heures", TimeFormatter.convertSecondsToTime(5364000, Locale.FRENCH));
    }

    // DE Language test

    @Test
    public void testConvertSecondsToTimeWith1DayAnd2Hours2MinsDE() {
        Assertions.assertEquals("1 Tag, 2 Stunden, 2 Minuten", TimeFormatter.convertSecondsToTime(93720, Locale.GERMAN));
    }

    @Test
    public void testConvertSecondsToTime2Months2Days2HoursDE() {
        Assertions.assertEquals("2 Monate, 2 Tage, 2 Stunden", TimeFormatter.convertSecondsToTime(5364000, Locale.GERMAN));
    }
}
