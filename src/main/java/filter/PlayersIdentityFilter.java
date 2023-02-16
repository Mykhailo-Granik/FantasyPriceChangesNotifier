package filter;

import common.Player;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class PlayersIdentityFilter implements PlayerFilter {

    private final Set<Player> players;
    @Override
    public boolean test(Player player) {
        return isPlayerStored(player);
    }

    private boolean isPlayerStored(Player player) {
        return players.stream().anyMatch(storedPlayer -> arePlayersEqual(player, storedPlayer));
    }

    private boolean arePlayersEqual(Player player1, Player player2) {
        return player1.getName().equals(player2.getName()) && player1.getClub().equals(player2.getClub());
    }
}
