package ch.lucas.bot.cff.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageTest {
    private final String dateOfReport = "samedi 5 juin 2021";
    private final int nbrOfTravels = 100;
    private final int nbrOfDelayedTravels = 10;
    private final int nbrOfDeletedTravels = 5;
    private final double percentageOfDelayedTravels = 10.0;
    private final double percentageOfDeletedTravels = 5.0;
    private final String cumulatedDelay = "13 jours, 21 minutes";
    private final Message underTest = new Message(dateOfReport, nbrOfTravels, nbrOfDelayedTravels, nbrOfDeletedTravels, percentageOfDelayedTravels, percentageOfDeletedTravels, cumulatedDelay);

    @Test
    public void testGetDateOfReport() {
        assertEquals(dateOfReport, underTest.getDateOfReport());
    }

    @Test
    public void testGetNbrOfTravels() {
        assertEquals(nbrOfTravels, underTest.getNbrOfTravels());
    }

    @Test
    public void testGetNbrOfDelayedTravels() {
        assertEquals(nbrOfDelayedTravels, underTest.getNbrOfDelayedTravels());
    }

    @Test
    public void testGetNbrOfDeletedTravels() {
        assertEquals(nbrOfDeletedTravels, underTest.getNbrOfDeletedTravels());
    }

    @Test
    public void testGetPercentageOfDelayedTravels() {
        assertEquals(percentageOfDelayedTravels, underTest.getPercentageOfDelayedTravels());
    }

    @Test
    public void testGetPercentageOfDeletedTravels() {
        assertEquals(percentageOfDeletedTravels, underTest.getPercentageOfDeletedTravels());
    }

    @Test
    public void testGetCumulatedDelay() {
        assertEquals(cumulatedDelay, underTest.getCumulatedDelay());
    }

    @Test
    public void testGetFormattedMessage() {
        String underTest = this.underTest.getFormattedMessage();

        assertTrue(underTest.contains(nbrOfTravels + ""));
        assertTrue(underTest.contains(nbrOfDelayedTravels + ""));
        assertTrue(underTest.contains(nbrOfDeletedTravels + ""));
        assertTrue(underTest.contains(percentageOfDelayedTravels + ""));
        assertTrue(underTest.contains(percentageOfDeletedTravels + ""));
        assertTrue(underTest.contains(cumulatedDelay));
    }
}
