package message;

import common.Player;
import filter.PlayerFilter;

import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class AbstractMessage implements Message {
    @Override
    public String createMessage(List<Player> allPlayers) {
        return messageForFilteredPlayers(filteredPlayers(allPlayers));
    }

    private List<Player> filteredPlayers(List<Player> allPlayers) {
        return allPlayers.stream().filter(playerFilter()).collect(toList());
    }

    protected abstract PlayerFilter playerFilter();

    protected abstract String messageForFilteredPlayers(List<Player> players);
}
