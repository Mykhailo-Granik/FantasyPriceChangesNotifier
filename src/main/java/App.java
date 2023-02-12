import destination.DummyDestination;
import filter.TargetFilter;
import retriever.DummyRetriever;
import sender.Sender;

import java.util.List;

public class App {

    public static void main(String[] args) {
        new Sender(
                new DummyRetriever(),
                List.of(new DummyDestination(new TargetFilter(99.6)), new DummyDestination(new TargetFilter(99.5)))
        ).send();
    }
}
