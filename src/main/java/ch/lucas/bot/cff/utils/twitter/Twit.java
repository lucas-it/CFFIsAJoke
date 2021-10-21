package ch.lucas.bot.cff.utils.twitter;

import ch.lucas.bot.cff.utils.config.Config;
import ch.lucas.bot.cff.utils.exceptions.TweetMaximumLengthExceedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Arrays;

/**
 * This class contains utility methods for posting tweet.
 *
 * @author Lucas-it@github
 */
public class Twit {
    private static final Logger LOGGER = LoggerFactory.getLogger(Twit.class);
    private final Config config;
    private final Twitter twitter;

    /**
     * Create a new Twit object.
     *
     * @param config
     */
    public Twit(Config config) {
        this.config = config;

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(config.getTwitterConsumerKey());
        configurationBuilder.setOAuthConsumerSecret(config.getTwitterConsumerSecret());
        configurationBuilder.setOAuthAccessToken(config.getTwitterAccessToken());
        configurationBuilder.setOAuthAccessTokenSecret(config.getTwitterAccessTokenSecret());

        this.twitter = new TwitterFactory(configurationBuilder.build()).getInstance();
    }

    /**
     * Post a new tweet.
     *
     * @param message the content of the tweet
     * @return the twitter status
     * @throws TweetMaximumLengthExceedException if the tweet is longer than 280 characters.
     */
    public Status tweet(String message) throws TweetMaximumLengthExceedException {
        LOGGER.info("tweet - Check if the message is not too long");
        if (message.length() > 280) {
            throw new TweetMaximumLengthExceedException();
        }

        LOGGER.info("tweet - Check if the bot is allowed to post tweet (config.json)");
        if (config.isAllowTweeting()) {
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

    /**
     * Post a new tweet.
     *
     * @param parentMessage the tweet which contain the thread
     * @param messages      the messages of the thread
     * @throws TweetMaximumLengthExceedException if the tweet is longer than 280 characters.
     */
    public void tweet(String parentMessage, String... messages) throws TweetMaximumLengthExceedException {
        LOGGER.info("tweet - Check if the message is not too long");
        if (parentMessage.length() > 280 || Arrays.stream(messages).anyMatch(m -> m.length() > 280)) {
            throw new TweetMaximumLengthExceedException();
        }

        LOGGER.info("tweet - Check if the bot is allowed to post tweet (config.json)");
        if (config.isAllowTweeting()) {
            long parentStatusId;

            try {
                LOGGER.info("tweet - Post the Tweet (update status)");
                parentStatusId = twitter.updateStatus(parentMessage).getId();
            } catch (TwitterException e) {
                LOGGER.error(e.getMessage(), e);
                return;
            }

            Arrays.stream(messages).forEach(m -> {
                StatusUpdate su = new StatusUpdate(m);
                su.setInReplyToStatusId(parentStatusId);

                try {
                    LOGGER.info("tweet - Post the a new message under the Thread (update status)");
                    twitter.updateStatus(su);
                } catch (TwitterException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            });
        } else {
            String tweets = String.join("\n", messages);
            LOGGER.info("tweet - The bot is not allowed to post tweet (change config.json). The tweet is below :");
            LOGGER.info("\n{} \n {}", parentMessage, tweets);
        }
    }

    /**
     * Get the twitter object.
     *
     * @return the twitter object
     */
    public Twitter getTwitter() {
        return twitter;
    }
}
