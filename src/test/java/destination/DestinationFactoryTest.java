package destination;

import common.TestingApplicationProperties;
import destination.telegram.TelegramDestination;
import message.PlayersCloseToPriceFallMessage;
import message.PlayersCloseToPriceRiseMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import properties.ApplicationProperties;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static destination.DestinationFactory.PLAYERS_CLOSE_TO_PRICE_CHANGE_THRESHOLD_KEY;
import static org.junit.jupiter.api.Assertions.*;

public class DestinationFactoryTest {

    public static final String PLAYERS_CLOSE_TO_PRICE_CHANGE_THRESHOLD_VALUE = "99.0";
    private DestinationFactory underTest;
    private ApplicationProperties applicationProperties;

    @BeforeEach
    public void setUp() {
        applicationProperties = new TestingApplicationProperties(properties());
        underTest = new DestinationFactory(applicationProperties);
    }

    private Map<String, String> properties() {
        return Map.of(PLAYERS_CLOSE_TO_PRICE_CHANGE_THRESHOLD_KEY, PLAYERS_CLOSE_TO_PRICE_CHANGE_THRESHOLD_VALUE);
    }

    @Test
    public void shouldCreateTelegramDestination() {
        assertTrue(
                underTest.create().stream()
                        .anyMatch(destination -> destination instanceof TelegramDestination)
        );
    }

    @Test
    public void playersCloseToPriceFallMessageInTelegramDestinationShouldContainCorrectThreshold() {
        List<PlayersCloseToPriceFallMessage> playersCloseToPriceFallMessages = messages(PlayersCloseToPriceFallMessage.class);
        assertEquals(1, playersCloseToPriceFallMessages.size());
        assertEquals(-Double.parseDouble(PLAYERS_CLOSE_TO_PRICE_CHANGE_THRESHOLD_VALUE), playersCloseToPriceFallMessages.get(0).getThreshold());
    }

    private <T> List<T> messages(Class<T> clazz) {
        return underTest.create().stream()
                .filter(TelegramDestination.class::isInstance)
                .map(TelegramDestination.class::cast)
                .flatMap(destination -> destination.getMessages().stream())
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .collect(Collectors.toList());
    }

    @Test
    public void playersCloseToPriceRiseMessageInTelegramDestinationShouldContainCorrectThreshold() {
        List<PlayersCloseToPriceRiseMessage> playersCloseToPriceRiseMessages = messages(PlayersCloseToPriceRiseMessage.class);
        assertEquals(1, playersCloseToPriceRiseMessages.size());
        assertEquals(Double.parseDouble(PLAYERS_CLOSE_TO_PRICE_CHANGE_THRESHOLD_VALUE), playersCloseToPriceRiseMessages.get(0).getThreshold());
    }

    @Test
    public void shouldCorrectlyCreateTelegramClient() {
        List<TelegramDestination> telegramDestinations = underTest.create().stream()
                .filter(TelegramDestination.class::isInstance)
                .map(TelegramDestination.class::cast)
                .collect(Collectors.toList());
        assertNotNull(telegramDestinations.get(0).getTelegramClient());
    }

}