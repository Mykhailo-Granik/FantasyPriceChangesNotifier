package destination;

import java.util.List;


public class DummyDestination implements Destination {

    @Override
    public void send(List<String> messages) {
        messages.forEach(System.out::println);
    }
}
