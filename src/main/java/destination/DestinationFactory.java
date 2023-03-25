package destination;

import destination.telegram.TelegramClientImpl;
import destination.telegram.TelegramDestination;
import lombok.RequiredArgsConstructor;
import message.PlayersCloseToPriceFallMessage;
import message.PlayersCloseToPriceRiseMessage;
import properties.ApplicationProperties;

import java.util.List;

@RequiredArgsConstructor
public class DestinationFactory {

    public static final String PLAYERS_CLOSE_TO_PRICE_CHANGE_THRESHOLD_KEY = "playersCloseToPriceChangeThreshold";
    private final ApplicationProperties applicationProperties;

    public List<Destination> create() {
        return List.of(
                new TelegramDestination(
                        List.of(
                                new PlayersCloseToPriceFallMessage(-playersCloseToPriceChangeThreshold()),
                                new PlayersCloseToPriceRiseMessage(playersCloseToPriceChangeThreshold())
                        ),
                        new TelegramClientImpl(applicationProperties)
                )
        );
    }

    private double playersCloseToPriceChangeThreshold() {
        return Double.parseDouble(applicationProperties.getString(PLAYERS_CLOSE_TO_PRICE_CHANGE_THRESHOLD_KEY));
    }

}
