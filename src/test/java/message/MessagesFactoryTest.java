package message;

import common.TestingApplicationProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MessagesFactoryTest {

    public static final String THRESHOLD = "97.0";
    private MessagesFactory underTest;

    @BeforeEach
    public void setup() {
        underTest = new MessagesFactory(new TestingApplicationProperties(properties()));
    }

    private Map<String, String> properties() {
        return Map.of("playersCloseToPriceChangeThreshold", THRESHOLD);
    }

    @Test
    public void createdMessagesShouldContainPlayersCloseToPriceRiceChallenge() {
        PlayersCloseToPriceFallMessage message = getFirstMessageOfType(PlayersCloseToPriceFallMessage.class);
        assertNotNull(message);
        assertEquals(Double.valueOf(THRESHOLD), message.getThreshold());
    }

    private <T> T getFirstMessageOfType(Class<T> type) {
        return underTest.create().stream()
                .filter(type::isInstance)
                .map(type::cast)
                .findFirst()
                .orElse(null);
    }

    @Test
    public void createdMessagesShouldContainPlayersCloseToPriceRiceChallenge2() {
        PlayersCloseToPriceRiseMessage message = getFirstMessageOfType(PlayersCloseToPriceRiseMessage.class);
        assertNotNull(message);
        assertEquals(Double.valueOf(THRESHOLD), message.getThreshold());
    }

}