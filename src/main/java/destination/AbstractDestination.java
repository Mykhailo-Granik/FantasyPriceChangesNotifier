package destination;

import common.Player;
import filter.PlayerFilter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractDestination implements Destination {

    private final PlayerFilter playerFilter;

    @Override
    public void send(List<Player> players) {
        List<Player> filteredPlayers = players.stream().filter(playerFilter).collect(Collectors.toList());
        sendFilteredPlayers(filteredPlayers);
    }

    protected abstract void sendFilteredPlayers(List<Player> players);

}
