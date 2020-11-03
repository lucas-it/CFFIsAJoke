package ch.lucas.bot.cff;

import ch.lucas.bot.cff.utils.cffapi.CFFApiUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCFFIsAJoke {
    @Test
    public void testGetInformationFromApiMethodDontMakeException() {
        try {
            new CFFApiUtils().getInformationFromAPI().getFormattedMessage();
            Assertions.assertTrue(true);
        } catch(Exception e) {
            Assertions.fail();
        }
    }
}
