package destination;

import common.Player;
import filter.PlayerFilter;

import java.util.List;


public class DummyDestination extends AbstractDestination {

    public DummyDestination(PlayerFilter playerFilter) {
        super(playerFilter);
    }

    @Override
    protected void sendFilteredPlayers(List<Player> players) {
        players.forEach(System.out::println);
    }
}
