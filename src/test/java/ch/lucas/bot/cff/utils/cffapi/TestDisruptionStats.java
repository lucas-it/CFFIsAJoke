package ch.lucas.bot.cff.utils.cffapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDisruptionStats {
    private final int nbrOfDelayedTravels = 200;
    private final long cumulativeLate = 100000L;
    private final DisruptionStats disruptionStats = new DisruptionStats(nbrOfDelayedTravels, cumulativeLate);

    @Test
    public void testDisruptionStatsNbrOfDeletedTravels() {
        Assertions.assertEquals(nbrOfDelayedTravels, disruptionStats.getNumberOfDelayedTravels());
    }

    @Test
    public void testDisruptionStatsCumulativeLate() {
        Assertions.assertEquals(cumulativeLate, disruptionStats.getCumulativeLate());
    }
}
