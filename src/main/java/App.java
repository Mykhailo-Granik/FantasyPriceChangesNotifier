import destination.telegram.TelegramClientImpl;
import destination.telegram.TelegramDestination;
import message.PlayersCloseToPriceFallMessage;
import message.PlayersCloseToPriceRiseMessage;
import properties.ApplicationPropertiesImpl;
import retriever.RetrieverFactory;
import sender.Sender;

import java.io.IOException;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {
        new Sender(
                new RetrieverFactory().create(),
                List.of(
                        new TelegramDestination(
                                List.of(
                                        new PlayersCloseToPriceRiseMessage(95.0),
                                        new PlayersCloseToPriceFallMessage(-95.0)
                                ),
                                new TelegramClientImpl(new ApplicationPropertiesImpl())
                        )
                )
        ).send();
    }
}
