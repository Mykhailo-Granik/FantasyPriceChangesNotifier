package message;

import common.Player;
import filter.AllMatchFilter;
import filter.PlayerFilter;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class DummyMessage extends AbstractMessage {

    private final String message;

    @Override
    protected PlayerFilter playerFilter() {
        return new AllMatchFilter();
    }

    @Override
    protected Comparator<Player> playersOrderingComparator() {
        return null;
    }

    @Override
    protected String messageForFilteredPlayers(List<Player> players) {
        return message;
    }
}
