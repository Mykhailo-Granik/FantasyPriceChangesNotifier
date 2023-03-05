package message;

import common.Player;
import filter.AllMatchFilter;
import filter.PlayerFilter;

import java.util.List;

public class DummyMessage extends AbstractMessage {
    @Override
    protected PlayerFilter playerFilter() {
        return new AllMatchFilter();
    }

    @Override
    protected String messageForFilteredPlayers(List<Player> players) {
        return "This is dummy message";
    }
}
