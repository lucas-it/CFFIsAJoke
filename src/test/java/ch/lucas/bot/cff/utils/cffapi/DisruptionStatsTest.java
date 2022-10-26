package ch.lucas.bot.cff.utils.cffapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DisruptionStatsTest {
    private final int nbrOfDelayedTravels = 200;
    private final long cumulativeLate = 100000L;
    private final DisruptionStats disruptionStats = new DisruptionStats(nbrOfDelayedTravels, cumulativeLate);

    @Test
    public void testDisruptionStatsNbrOfDeletedTravels() {
        disruptionStats.setNumberOfDelayedTravels(nbrOfDelayedTravels);
        Assertions.assertEquals(nbrOfDelayedTravels, disruptionStats.getNumberOfDelayedTravels());
    }

    @Test
    public void testDisruptionStatsCumulativeLate() {
        disruptionStats.setCumulativeLate(cumulativeLate);
        Assertions.assertEquals(cumulativeLate, disruptionStats.getCumulativeLate());
    }

    @Test
    public void testDisruptionsStatsNbrOfDeletedTravelsSetMethod() {
        disruptionStats.setNumberOfDelayedTravels(123456);
        Assertions.assertEquals(123456, disruptionStats.getNumberOfDelayedTravels());
    }
    @Test
    public void testDisruptionsStatsCumulativeLateSetMethod() {
        disruptionStats.setCumulativeLate(789123);
        Assertions.assertEquals(789123, disruptionStats.getCumulativeLate());
    }
}
