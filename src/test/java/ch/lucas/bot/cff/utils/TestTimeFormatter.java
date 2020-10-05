package ch.lucas.bot.cff.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestTimeFormatter {
    // Test minutes

    @Test
    void testConvertSecondsToTimeWith1Min() {
        Assertions.assertEquals("1 minute", TimeFormatter.convertSecondsToTime(60), "60 secondes doit être converti en : 1 minute");
    }

    @Test
    void testConvertSecondsToTimeWith10Mins() {
        Assertions.assertEquals("10 minutes", TimeFormatter.convertSecondsToTime(600), "600 secondes doit être converti en : 10 minutes");
    }

    // Test hours

    @Test
    void testConvertSecondsToTimeWith1Hour() {
        Assertions.assertEquals("1 heure", TimeFormatter.convertSecondsToTime(3600), "3600 secondes doit être converti en : 1 heure");
    }

    @Test
    void testConvertSecondsToTimeWith1HourAnd1Min() {
        Assertions.assertEquals("1 heure, 1 minute", TimeFormatter.convertSecondsToTime(3660), "3660 secondes doit être converti en : 1 heure, 1 minute");
    }

    @Test
    void testConvertSecondsToTimeWith1HourAnd30Mins() {
        Assertions.assertEquals("1 heure, 30 minutes", TimeFormatter.convertSecondsToTime(5400), "5400 secondes doit être converti en : 1 heure, 30 minutes");
    }

    @Test
    void testConvertSecondsToTimeWith2Hours() {
        Assertions.assertEquals("2 heures", TimeFormatter.convertSecondsToTime(7200), "7200 secondes doit être converti en : 2 heures");
    }

    @Test
    void testConvertSecondsToTimeWith2HoursAnd1Min() {
        Assertions.assertEquals("2 heures, 1 minute", TimeFormatter.convertSecondsToTime(7260), "7260 secondes doit être converti en : 2 heures, 1 minute");
    }

    @Test
    void testConvertSecondsToTimeWith2HoursAnd6Mins() {
        Assertions.assertEquals("2 heures, 6 minutes", TimeFormatter.convertSecondsToTime(7560), "7560 secondes doit être converti en : 2 heures, 6 minutes");
    }

    @Test
    void testConvertSecondsToTimeWith12Hours() {
        Assertions.assertEquals("12 heures", TimeFormatter.convertSecondsToTime(43200), "43200 secondes doit être converti en : 12 heures");
    }

    // Test days

    @Test
    void testConvertSecondsToTimeWith1Day() {
        Assertions.assertEquals("1 jour", TimeFormatter.convertSecondsToTime(86400), "86400 secondes doit être converti en : 1 jour");
    }

    @Test
    void testConvertSecondsToTimeWith1DayAnd1Mins() {
        Assertions.assertEquals("1 jour, 1 minute", TimeFormatter.convertSecondsToTime(86460), "86460 secondes doit être converti en : 1 jour, 1 minute");
    }

    @Test
    void testConvertSecondsToTimeWith1DayAnd2Mins() {
        Assertions.assertEquals("1 jour, 2 minutes", TimeFormatter.convertSecondsToTime(86520), "86520 secondes doit être converti en : 1 jour, 2  minutes");
    }

    @Test
    void testConvertSecondsToTimeWith1DayAnd1HoursAnd1Mins() {
        Assertions.assertEquals("1 jour, 1 heure, 1 minute", TimeFormatter.convertSecondsToTime(90060), "86460 secondes doit être converti en : 1 jour, 1 heure, 1 minute");
    }

    @Test
    void testConvertSecondsToTimeWith1DayAnd2HoursAnd2Mins() {
        Assertions.assertEquals("1 jour, 2 heures, 2 minutes", TimeFormatter.convertSecondsToTime(93720), "86520 secondes doit être converti en : 1 jour, 2 heures, 2 minutes");
    }

    @Test
    void testConvertSecondsToTimeWith2Days() {
        Assertions.assertEquals("2 jours", TimeFormatter.convertSecondsToTime(172800), "172800 secondes doit être converti en : 2 jours");
    }

    @Test
    void testConvertSecondsToTimeWith2DaysAnd1Hour() {
        Assertions.assertEquals("2 jours, 1 heure", TimeFormatter.convertSecondsToTime(176400), "176400 secondes doit être converti en : 2 jours, 1 heure");
    }

    @Test
    void testConvertSecondsToTimeWith2DaysAnd2Hours() {
        Assertions.assertEquals("2 jours, 2 heures", TimeFormatter.convertSecondsToTime(180000), "180000 secondes doit être converti en : 2 jours, 2 heures");
    }

    @Test
    void testConvertSecondsToTimeWith2DaysAnd1HourAnd1Mins() {
        Assertions.assertEquals("2 jours, 1 heure, 1 minute", TimeFormatter.convertSecondsToTime(176460), "176460 secondes doit être converti en : 2 jours, 1 heure, 1 minute");
    }

    @Test
    void testConvertSecondsToTimeWith2DaysAnd1HourAnd2Mins() {
        Assertions.assertEquals("2 jours, 1 heure, 2 minutes", TimeFormatter.convertSecondsToTime(176520), "176520 secondes doit être converti en : 2 jours, 1 heure, 2 minutes");
    }

    @Test
    void testConvertSecondsToTimeWith2DaysAnd2HoursAnd1Mins() {
        Assertions.assertEquals("2 jours, 2 heures, 1 minute", TimeFormatter.convertSecondsToTime(180060), "180060 doit être converti en : 2 jours, 2 heures, 1 minutes");
    }

    @Test
    void testConvertSecondsToTimeWith2DaysAnd2HoursAnd2Mins() {
        Assertions.assertEquals("2 jours, 2 heures, 2 minutes", TimeFormatter.convertSecondsToTime(180120), "180120 doit être converti en : 2 jours, 2 heures, 2 minutes");
    }

    // Test weeks

    @Test
    void testConvertSecondsToTime1Week() {
        Assertions.assertEquals("1 semaine", TimeFormatter.convertSecondsToTime(604800));
    }

    @Test
    void testConvertSecondsToTime1Week1Day() {
        Assertions.assertEquals("1 semaine, 1 jour", TimeFormatter.convertSecondsToTime(691200));
    }

    @Test
    void testConvertSecondsToTime1Week2Days() {
        Assertions.assertEquals("1 semaine, 2 jours", TimeFormatter.convertSecondsToTime(777600));
    }

    @Test
    void testConvertSecondsToTime1Week1Day1Hour() {
        Assertions.assertEquals("1 semaine, 1 jour, 1 heure", TimeFormatter.convertSecondsToTime(694800));
    }

    @Test
    void testConvertSecondsToTime1Week1Day2Hours() {
        Assertions.assertEquals("1 semaine, 1 jour, 2 heures", TimeFormatter.convertSecondsToTime(698400));
    }

    @Test
    void testConvertSecondsToTime1Week1Day1Hour1Min() {
        Assertions.assertEquals("1 semaine, 1 jour, 1 heure", TimeFormatter.convertSecondsToTime(694860));
    }

    @Test
    void testConvertSecondsToTime1Week1Day1Hour2Mins() {
        Assertions.assertEquals("1 semaine, 1 jour, 1 heure", TimeFormatter.convertSecondsToTime(694920));
    }

    @Test
    void testConvertSecondsToTime2Week() {
        Assertions.assertEquals("2 semaines", TimeFormatter.convertSecondsToTime(1209600));
    }

    @Test
    void testConvertSecondsToTime2Weeks1Day() {
        Assertions.assertEquals("2 semaines, 1 jour", TimeFormatter.convertSecondsToTime(1296000));
    }

    @Test
    void testConvertSecondsToTime2Weeks2Days() {
        Assertions.assertEquals("2 semaines, 2 jours", TimeFormatter.convertSecondsToTime(1382400));
    }

    @Test
    void testConvertSecondsToTime2Weeks2Days1Hour() {
        Assertions.assertEquals("2 semaines, 2 jours, 1 heure", TimeFormatter.convertSecondsToTime(1386000));
    }

    @Test
    void testConvertSecondsToTime2Weeks2Days2Hours() {
        Assertions.assertEquals("2 semaines, 2 jours, 2 heures", TimeFormatter.convertSecondsToTime(1389600));
    }

    @Test
    void testConvertSecondsToTime2Weeks2Days2Hours1Min() {
        Assertions.assertEquals("2 semaines, 2 jours, 2 heures", TimeFormatter.convertSecondsToTime(1389660));
    }

    @Test
    void testConvertSecondsToTime2Weeks2Days2Hours2Mins() {
        Assertions.assertEquals("2 semaines, 2 jours, 2 heures", TimeFormatter.convertSecondsToTime(1389720));
    }

    // Test months

    @Test
    void testConvertSecondsToTime1Month() {
        Assertions.assertEquals("1 mois", TimeFormatter.convertSecondsToTime(2419200));
    }

    @Test
    void testConvertSecondsToTime1Month1Week() {
        Assertions.assertEquals("1 mois, 1 semaine", TimeFormatter.convertSecondsToTime(3024000));
    }

    @Test
    void testConvertSecondsToTime1Month1Week1Day() {
        Assertions.assertEquals("1 mois, 1 semaine, 1 jour", TimeFormatter.convertSecondsToTime(3110400));
    }

    @Test
    void testConvertSecondsToTime1Month1Week1Day1Hour() {
        Assertions.assertEquals("1 mois, 1 semaine, 1 jour, 1 heure", TimeFormatter.convertSecondsToTime(3114000));
    }

    @Test
    void testConvertSecondsToTime2Months() {
        Assertions.assertEquals("2 mois", TimeFormatter.convertSecondsToTime(4838400));
    }

    @Test
    void testConvertSecondsToTime2Months2Weeks() {
        Assertions.assertEquals("2 mois, 2 semaines", TimeFormatter.convertSecondsToTime(6048000));
    }

    @Test
    void testConvertSecondsToTime2Month2Weeks2Days() {
        Assertions.assertEquals("2 mois, 2 semaines, 2 jours", TimeFormatter.convertSecondsToTime(6220800));
    }

    @Test
    void testConvertSecondsToTime2Months2Weeks2Days2Hours() {
        Assertions.assertEquals("2 mois, 2 semaines, 2 jours, 2 heures", TimeFormatter.convertSecondsToTime(6228000));
    }
}
