package ch.lucas.bot.cff.utils.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTweetMaximumLengthExceedException {
    @Test
    public void testConstructor() {
        TweetMaximumLengthExceedException e = new TweetMaximumLengthExceedException("message");
        Assertions.assertEquals("message", e.getMessage());
    }
}
