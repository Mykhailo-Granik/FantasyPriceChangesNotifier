package message;

import common.Player;
import filter.PlayerFilter;
import filter.PlayersCloseToPriceRiseFilter;

import java.util.List;
import java.util.stream.Collectors;

public class PlayersCloseToPriceRiseMessage extends AbstractMessage {

    private final double threshold;

    public PlayersCloseToPriceRiseMessage(double threshold) {
        this.threshold = threshold;
    }

    @Override
    protected PlayerFilter playerFilter() {
        return new PlayersCloseToPriceRiseFilter(threshold);
    }

    @Override
    protected String messageForFilteredPlayers(List<Player> players) {
        if (players.isEmpty()) {
            return "No players close to price rise";
        }
        return "Players close to price rise ⬆️⬆️⬆️\n" + players.stream()
                .map(Player::toString)
                .collect(Collectors.joining("\n"));
    }
}
