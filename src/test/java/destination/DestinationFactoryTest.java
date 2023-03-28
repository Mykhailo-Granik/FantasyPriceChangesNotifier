package destination;

import common.TestingApplicationProperties;
import destination.telegram.TelegramDestination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import properties.ApplicationProperties;
import splitter.TelegramMessageSplitter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static destination.DestinationFactory.PLAYERS_CLOSE_TO_PRICE_CHANGE_THRESHOLD_KEY;
import static destination.DestinationFactory.TELEGRAM_MAX_MESSAGE_LENGTH;
import static org.junit.jupiter.api.Assertions.*;

public class DestinationFactoryTest {

    public static final String PLAYERS_CLOSE_TO_PRICE_CHANGE_THRESHOLD_VALUE = "99.0";
    public static final String MAX_MESSAGE_LENGTH = "4000";
    private DestinationFactory underTest;

    @BeforeEach
    public void setUp() {
        ApplicationProperties applicationProperties = new TestingApplicationProperties(properties());
        underTest = new DestinationFactory(applicationProperties);
    }

    private Map<String, String> properties() {
        return Map.of(
                PLAYERS_CLOSE_TO_PRICE_CHANGE_THRESHOLD_KEY, PLAYERS_CLOSE_TO_PRICE_CHANGE_THRESHOLD_VALUE,
                TELEGRAM_MAX_MESSAGE_LENGTH, MAX_MESSAGE_LENGTH
        );
    }

    @Test
    public void shouldCreateTelegramDestination() {
        assertTrue(
                underTest.create().stream()
                        .anyMatch(destination -> destination instanceof TelegramDestination)
        );
    }

    @Test
    public void shouldCorrectlyCreateTelegramClient() {
        List<TelegramDestination> telegramDestinations = underTest.create().stream()
                .filter(TelegramDestination.class::isInstance)
                .map(TelegramDestination.class::cast)
                .collect(Collectors.toList());
        assertNotNull(telegramDestinations.get(0).getTelegramClient());
    }

    @Test
    public void shouldCorrectlyCreateTelegramMessageSplitter() {
        List<TelegramDestination> telegramDestinations = underTest.create().stream()
                .filter(TelegramDestination.class::isInstance)
                .map(TelegramDestination.class::cast)
                .collect(Collectors.toList());
        assertEquals(
                Integer.parseInt(MAX_MESSAGE_LENGTH),
                ((TelegramMessageSplitter) telegramDestinations.get(0).getMessageSplitter()).getMaxMessageLength()
        );
    }

}