package destination;

import common.Player;

import java.util.List;


public class DummyDestination implements Destination {

    @Override
    public void send(List<Player> players) {
        players.forEach(System.out::println);
    }
}
