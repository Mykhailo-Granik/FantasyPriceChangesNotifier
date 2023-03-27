import destination.DestinationFactory;
import message.MessagesFactory;
import properties.ApplicationProperties;
import properties.ApplicationPropertiesImpl;
import retriever.RetrieverFactory;
import sender.Sender;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        ApplicationProperties applicationProperties = new ApplicationPropertiesImpl();
        new Sender(
                new RetrieverFactory(applicationProperties).create(),
                new MessagesFactory(applicationProperties).create(),
                new DestinationFactory(applicationProperties).create()
        ).send();
    }
}
