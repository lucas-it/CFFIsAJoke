package ch.lucas.bot.cff.utils.config;

import ch.lucas.bot.cff.CFFIsAJoke;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TestConfig {

    private final File configFileOK = new File(getClass().getResource("/config-ok.json").getPath());
    private final File configFileBadFormat = new File(getClass().getResource("/config-bad-format.json").getPath());
    private final File configFileMissingParameters = new File(getClass().getResource("/config-missing-parameters.json").getPath());

    @Test
    public void testConfigOK() {
        Config config = new Config(configFileOK);

        Assertions.assertEquals("123", config.getTwitterConsumerKey());
        Assertions.assertEquals("456", config.getTwitterConsumerSecret());
        Assertions.assertEquals("789", config.getTwitterAccessToken());
        Assertions.assertEquals("abc", config.getTwitterAccessTokenSecret());
        Assertions.assertFalse(config.isAllowTweeting());
        Assertions.assertEquals(configFileOK.getPath(), config.getJsonConfigFile().getPath());
    }

    @Test
    public void testConfigBadFormat() {
        try {
            Config config = new Config(configFileBadFormat);
            if(!config.isConfigIsValid()) Assertions.assertTrue(true);
            Assertions.fail();
        } catch(Exception e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void testConfigMissingParameters() {
        try {
            Config config = new Config(configFileMissingParameters);
            if(!config.isConfigIsValid()) Assertions.assertTrue(true);
            Assertions.fail();
        } catch(Exception e) {
            Assertions.assertTrue(true);
        }
    }

}
