package message;

import lombok.RequiredArgsConstructor;
import properties.ApplicationProperties;

import java.util.List;

@RequiredArgsConstructor
public class MessagesFactory {

    private final ApplicationProperties applicationProperties;

    public List<Message> create() {
        return List.of(
                new PlayersCloseToPriceFallMessage(-getPlayersCloseToPriceChangeThreshold()),
                new PlayersCloseToPriceRiseMessage(getPlayersCloseToPriceChangeThreshold())
        );
    }

    private double getPlayersCloseToPriceChangeThreshold() {
        return Double.parseDouble(applicationProperties.getString("playersCloseToPriceChangeThreshold"));
    }

}
