import destination.DummyDestination;
import filter.PlayersCloseToPriceRiseFilter;
import retriever.DummyRetriever;
import retriever.RetrieverFactory;
import sender.Sender;

import java.util.List;

public class App {

    public static void main(String[] args) {
        new Sender(
                new RetrieverFactory().create(),
                List.of(new DummyDestination(new PlayersCloseToPriceRiseFilter(90.0)), new DummyDestination(new PlayersCloseToPriceRiseFilter(95.0)))
        ).send();
    }
}
