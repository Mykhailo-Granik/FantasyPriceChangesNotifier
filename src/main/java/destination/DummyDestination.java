package destination;

import common.Player;

import java.util.List;

public class DummyDestination implements Destination {

    @Override
    public void send(List<Player> players) {
        System.out.println("DummyDestination.send");
        players.forEach(System.out::println);
    }
}
