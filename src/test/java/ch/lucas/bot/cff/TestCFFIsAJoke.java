package ch.lucas.bot.cff;

import ch.lucas.bot.cff.utils.cffapi.CFFApiUtils;
import ch.lucas.bot.cff.utils.config.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TestCFFIsAJoke {
    @Test
    public void testGetInformationFromApiMethodDontMakeException() {
        try {
            new CFFApiUtils(new Config(new File(this.getClass().getResource("config.json").getFile()))).getInformationFromAPI().getFormattedMessage();
            Assertions.assertTrue(true);
        } catch(Exception e) {
            Assertions.fail();
        }
    }
}
