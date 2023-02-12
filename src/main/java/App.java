import destination.DummyDestination;
import filter.TargetFilter;
import retriever.DummyRetriever;
import sender.Sender;

import java.util.List;

public class App {

    public static void main(String[] args) {
        new Sender(
                new DummyRetriever(),
                new TargetFilter(99.6),
                List.of(new DummyDestination(), new DummyDestination())
        ).send();
    }
}
