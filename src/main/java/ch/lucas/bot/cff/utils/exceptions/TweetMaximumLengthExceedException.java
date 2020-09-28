package ch.lucas.bot.cff.utils.exceptions;

public class TweetMaximumLengthExceedException extends Exception {

    public TweetMaximumLengthExceedException() {
        this("The Tweet exceed the maximum length of 280 characters.", null);
    }

    public TweetMaximumLengthExceedException(String message) {
        this(message, null);
    }

    public TweetMaximumLengthExceedException(String message, Throwable cause) {
        super(message, cause);
    }

}
