package ch.lucas.bot.cff.utils.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ConfigUtils {
    private final Logger LOGGER = LoggerFactory.getLogger(ConfigUtils.class);
    private File jsonConfigFile;
    private String twitterConsumerKey;
    private String twitterConsumerSecret;
    private String twitterAccessToken;
    private String twitterAccessTokenSecret;
    private boolean allowTweeting;
    private boolean configIsValid;

    public ConfigUtils(File jsonConfigFile) {
        this.jsonConfigFile = jsonConfigFile;

        try {
            JsonElement jsonParser = JsonParser.parseReader(new FileReader(jsonConfigFile));
            if(jsonParser.isJsonObject()) {
                JsonObject jsonConfig = jsonParser.getAsJsonObject();
                if(jsonConfig.has("twitterConsumerKey") && jsonConfig.has("twitterConsumerSecret") && jsonConfig.has("twitterAccessToken") && jsonConfig.has("twitterAccessTokenSecret") && jsonConfig.has("allowTweeting")) {
                    twitterConsumerKey = jsonConfig.get("twitterConsumerKey").getAsString();
                    twitterConsumerSecret = jsonConfig.get("twitterConsumerSecret").getAsString();
                    twitterAccessToken = jsonConfig.get("twitterAccessToken").getAsString();
                    twitterAccessTokenSecret = jsonConfig.get("twitterAccessTokenSecret").getAsString();
                    allowTweeting = jsonConfig.get("allowTweeting").getAsBoolean();
                    configIsValid = true;
                } else {
                    configIsValid = false;
                    LOGGER.error("The config file does not contain all needed parameters. See example.config.json in src/main/resources.");
                }
            } else {
                configIsValid = false;
                LOGGER.error("The config file is not correctly formatted.");
            }
        } catch (FileNotFoundException e) {
            configIsValid = false;
            LOGGER.error(e.getMessage(), e);
        }
    }

    public File getJsonConfigFile() { return jsonConfigFile; }

    public String getTwitterConsumerKey() { return twitterConsumerKey; }

    public String getTwitterConsumerSecret() { return twitterConsumerSecret; }

    public String getTwitterAccessToken() { return twitterAccessToken; }

    public String getTwitterAccessTokenSecret() { return twitterAccessTokenSecret; }

    public boolean isAllowTweeting() { return allowTweeting; }

    public boolean isConfigIsValid() { return configIsValid; }
}
