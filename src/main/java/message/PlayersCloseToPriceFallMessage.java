package message;

import common.Player;
import filter.PlayerFilter;
import filter.PlayersCloseToPriceFallFilter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sort.PlayersComparatorByTargetAscending;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class PlayersCloseToPriceFallMessage extends AbstractMessage {

    private final double threshold;

    @Override
    protected PlayerFilter playerFilter() {
        return new PlayersCloseToPriceFallFilter(threshold);
    }

    @Override
    protected Comparator<Player> playersOrderingComparator() {
        return new PlayersComparatorByTargetAscending();
    }

    @Override
    protected String messageForFilteredPlayers(List<Player> players) {
        if (players.isEmpty()) {
            return "No players close to price fall";
        }
        return "Players close to price fall  ⬇️⬇️⬇️️\n" + players.stream()
                .map(Player::toString)
                .collect(Collectors.joining("\n"));
    }

}
