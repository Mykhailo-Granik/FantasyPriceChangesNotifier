package message;

import common.Player;
import filter.PlayerFilter;
import filter.PlayersCloseToPriceRiseFilter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sort.PlayersComparatorByTargetDescending;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class PlayersCloseToPriceRiseMessage extends AbstractMessage {

    private final double threshold;

    @Override
    protected PlayerFilter playerFilter() {
        return new PlayersCloseToPriceRiseFilter(threshold);
    }

    @Override
    protected Comparator<Player> playersOrderingComparator() {
        return new PlayersComparatorByTargetDescending();
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
