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
    private final ApplicationProperties applicationProperties;

    public List<Destination> create() {
        return List.of(
                new TelegramDestination(
                        new TelegramClientImpl(applicationProperties),
                        new TelegramMessageSplitter(0)
                )
        );
    }

}
