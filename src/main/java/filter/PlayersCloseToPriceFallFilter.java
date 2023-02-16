package filter;

import common.Player;
import lombok.RequiredArgsConstructor;
import util.DoubleWithOnePlace;

@RequiredArgsConstructor
public class PlayersCloseToPriceFallFilter implements PlayerFilter {

    private final DoubleWithOnePlace threshold;

    @Override
    public boolean test(Player player) {
        return player.getTarget().compareTo(threshold) <= 0;
    }


}
