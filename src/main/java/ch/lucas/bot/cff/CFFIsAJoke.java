package ch.lucas.bot.cff;

import ch.lucas.bot.cff.utils.cffapi.CFFApiUtils;
import ch.lucas.bot.cff.utils.config.ConfigUtils;
import ch.lucas.bot.cff.utils.exceptions.TweetMaximumLengthExceedException;
import ch.lucas.bot.cff.utils.twitter.TwitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class CFFIsAJoke {
    private static final Logger LOGGER = LoggerFactory.getLogger(CFFIsAJoke.class);

    public static void main(String[] args) {
        LOGGER.info("Start the program");

        File currentJavaJarFile = new File(CFFIsAJoke.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String currentJavaJarFilePath = currentJavaJarFile.getAbsolutePath();
        String currentRootDirectoryPath = currentJavaJarFilePath.replace(currentJavaJarFile.getName(), "");

        LOGGER.info("Loading config file from {}", currentRootDirectoryPath);

        File configFile = new File(currentRootDirectoryPath + "/config.json");
        ConfigUtils configUtils = null;

        if(configFile.exists()) {
            LOGGER.info("Initialize configuration utilities");
            configUtils = new ConfigUtils(configFile);
            if(!configUtils.isConfigIsValid()) {
                LOGGER.error("Config file is not valid. Can't continue.");
                System.exit(-1);
            }
        } else {
            LOGGER.error("The file config.json does not exist at location : {}", currentRootDirectoryPath);
            System.exit(-1);
        }

        LOGGER.info("Initialize Twitter utilities");
        TwitUtils twitUtils = new TwitUtils(configUtils);

        LOGGER.info("Start tweeting procedure");
        long start = System.currentTimeMillis();

        try {
            twitUtils.tweet(new CFFApiUtils().getInformationsFromAPI().getFormattedMessage());
            LOGGER.info("The Tweet has been posted");
        } catch (IOException | TweetMaximumLengthExceedException e) {
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info("Processing time : {} s", (System.currentTimeMillis() - start) / 1000);
    }
}
