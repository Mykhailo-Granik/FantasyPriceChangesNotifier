package destination;

import destination.telegram.TelegramClientImpl;
import destination.telegram.TelegramDestination;
import lombok.RequiredArgsConstructor;
import properties.ApplicationProperties;
import splitter.TelegramMessageSplitter;

import java.util.List;

@RequiredArgsConstructor
public class DestinationFactory {

    public static final String PLAYERS_CLOSE_TO_PRICE_CHANGE_THRESHOLD_KEY = "playersCloseToPriceChangeThreshold";
    public static final String TELEGRAM_MAX_MESSAGE_LENGTH = "telegram.maxMessageLength";
    private final ApplicationProperties applicationProperties;

    public List<Destination> create() {
        return List.of(
                new TelegramDestination(
                        new TelegramClientImpl(applicationProperties),
                        new TelegramMessageSplitter(maxMessageLength())
                )
        );
    }

    private int maxMessageLength() {
        return Integer.parseInt(applicationProperties.getString(TELEGRAM_MAX_MESSAGE_LENGTH));
    }

}
