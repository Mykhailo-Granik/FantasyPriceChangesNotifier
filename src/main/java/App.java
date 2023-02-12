import destination.DummyDestination;
import filter.TargetFilter;
import retriever.DummyRetriever;
import sender.Sender;

public class App {

    public static void main(String[] args) {
        new Sender(
                new DummyRetriever(),
                new TargetFilter(99.6),
                new DummyDestination()
        ).send();
    }
}
