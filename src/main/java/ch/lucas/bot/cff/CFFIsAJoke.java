package ch.lucas.bot.cff;

import ch.lucas.bot.cff.utils.cffapi.CFFApiUtils;
import ch.lucas.bot.cff.utils.config.Config;
import ch.lucas.bot.cff.utils.exceptions.TweetMaximumLengthExceedException;
import ch.lucas.bot.cff.utils.twitter.Twit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Principal class of the project. It run the process to tweet the information about disruption.
 *
 * @author Lucas-it@github
 */
public class CFFIsAJoke {
    private static final Logger LOGGER = LoggerFactory.getLogger(CFFIsAJoke.class);

    public static void main(String[] args) {
        LOGGER.info("Start the program");

        File currentJavaJarFile = new File(CFFIsAJoke.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String currentJavaJarFilePath = currentJavaJarFile.getAbsolutePath();
        String currentRootDirectoryPath = currentJavaJarFilePath.replace(currentJavaJarFile.getName(), "");

        LOGGER.info("Loading config file from {}", currentRootDirectoryPath);

        File configFile = new File(currentRootDirectoryPath + "/config.json");
        Config config = null;

        if(configFile.exists()) {
            LOGGER.info("Initialize configuration utilities");
            config = new Config(configFile);
            if(!config.isConfigIsValid()) {
                LOGGER.error("Config file is not valid. Can't continue.");
                System.exit(-1);
            }
        } else {
            LOGGER.error("The file config.json does not exist at location : {}", currentRootDirectoryPath);
            System.exit(-1);
        }

        LOGGER.info("Initialize Twitter utilities");
        Twit twit = new Twit(config);

        LOGGER.info("Start tweeting procedure");
        long start = System.currentTimeMillis();

        try {
            twit.tweet(new CFFApiUtils().getInformationFromAPI().getFormattedMessage());
            LOGGER.info("The Tweet has been posted");
        } catch (IOException | TweetMaximumLengthExceedException e) {
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info("Processing time : {} s", (System.currentTimeMillis() - start) / 1000);
    }
}
