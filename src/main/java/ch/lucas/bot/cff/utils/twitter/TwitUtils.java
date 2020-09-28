package ch.lucas.bot.cff.utils.twitter;

import ch.lucas.bot.cff.utils.config.ConfigUtils;
import ch.lucas.bot.cff.utils.exceptions.TweetMaximumLengthExceedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitUtils {
    private final Logger LOGGER = LoggerFactory.getLogger(TwitUtils.class);
    private final ConfigUtils configUtils;
    private final Twitter twitter;

    public TwitUtils(ConfigUtils configUtils) {
        this.configUtils = configUtils;

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(configUtils.getTwitterConsumerKey());
        configurationBuilder.setOAuthConsumerSecret(configUtils.getTwitterConsumerSecret());
        configurationBuilder.setOAuthAccessToken(configUtils.getTwitterAccessToken());
        configurationBuilder.setOAuthAccessTokenSecret(configUtils.getTwitterAccessTokenSecret());

        this.twitter = new TwitterFactory(configurationBuilder.build()).getInstance();
    }

    public Status tweet(String message) throws TweetMaximumLengthExceedException {
        LOGGER.info("tweet - Check if the message is not too long");
        if (message.length() > 280) {
            throw new TweetMaximumLengthExceedException();
        }

        LOGGER.info("tweet - Check if the bot is allowed to post tweet (config.json)");
        if(configUtils.isAllowTweeting()) {
            try {
                LOGGER.info("tweet - Post the Tweet (update status)");
                return twitter.updateStatus(message);
            } catch (TwitterException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            LOGGER.info("tweet - The bot is not allowed to post tweet (change config.json). The tweet is below :");
            LOGGER.info("\n{}", message);
        }

        return null;
    }

    public Twitter getTwitter() {
        return twitter;
    }
}
