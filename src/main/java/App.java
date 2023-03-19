import destination.telegram.TelegramClientImpl;
import destination.telegram.TelegramDestination;
import message.PlayersCloseToPriceFallMessage;
import message.PlayersCloseToPriceRiseMessage;
import properties.ApplicationProperties;
import properties.ApplicationPropertiesImpl;
import retriever.RetrieverFactory;
import sender.Sender;

import java.io.IOException;
import java.util.List;

public class App {

    public static void main(String[] args) throws IOException {
        ApplicationProperties applicationProperties = new ApplicationPropertiesImpl();
        new Sender(
                new RetrieverFactory(applicationProperties).create(),
                List.of(
                        new TelegramDestination(
                                List.of(
                                        new PlayersCloseToPriceRiseMessage(99.0),
                                        new PlayersCloseToPriceFallMessage(-99.0)
                                ),
                                new TelegramClientImpl(applicationProperties)
                        )
                )
        ).send();
    }
}
