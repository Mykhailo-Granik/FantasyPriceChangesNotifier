import destination.DummyDestination;
import filter.PlayersCloseToPriceRiseFilter;
import retriever.DummyRetriever;
import sender.Sender;

import java.util.List;

public class App {

    public static void main(String[] args) {
        new Sender(
                new DummyRetriever(),
                List.of(new DummyDestination(new PlayersCloseToPriceRiseFilter(99.6)), new DummyDestination(new PlayersCloseToPriceRiseFilter(99.5)))
        ).send();
    }
}
