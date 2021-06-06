package ch.lucas.bot.cff.utils.cffapi;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCFFApiUtils {
    private CFFApiUtils cffApiUtils = new CFFApiUtils(null);

    @Test
    public void testParseDisruptionStatsFromJson() {
        System.out.println("_________________________________");
        InputStream disruptions = getClass().getResourceAsStream("/disruptions.json");
        JsonArray disruptionsJson = JsonParser.parseReader(new InputStreamReader(disruptions)).getAsJsonArray();

        DisruptionStats underTest = cffApiUtils.parseDisruptionStatsFromJson(disruptionsJson);

        assertEquals(9, underTest.getNumberOfDelayedTravels());
        assertEquals(7567000, underTest.getCumulativeLate());
    }

    @Test
    public void testParseDeletedTravelsFromJson() {
        InputStream deletedTravels = getClass().getResourceAsStream("/deletedTravels.json");
        JsonArray deletedTravelsJson = JsonParser.parseReader(new InputStreamReader(deletedTravels)).getAsJsonArray();

        int underTest = cffApiUtils.parseDeletedTravelsFromJson(deletedTravelsJson);

        assertEquals(8, underTest);
    }

    @Test
    public void testParseTotalTravelsFromJson() {
        InputStream travels = getClass().getResourceAsStream("/travels.json");
        JsonArray travelsJson = JsonParser.parseReader(new InputStreamReader(travels)).getAsJsonArray();

        int underTest = cffApiUtils.parseTotalTravelsFromJson(travelsJson);

        assertEquals(6, underTest);
    }
}
