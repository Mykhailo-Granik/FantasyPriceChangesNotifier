import destination.telegram.TelegramClientImpl;
import destination.telegram.TelegramDestination;
import message.PlayersCloseToPriceFallMessage;
import properties.DummyApplicationProperties;
import retriever.RetrieverFactory;
import sender.Sender;

import java.util.List;

public class App {

    public static void main(String[] args) {
        new Sender(
                new RetrieverFactory().create(),
                List.of(
                        new TelegramDestination(
                                List.of(new PlayersCloseToPriceFallMessage(-95.0)),
                                new TelegramClientImpl(new DummyApplicationProperties())
                        )
                )
        ).send();
    }
}
