package filter;

import common.Player;
import util.DoubleWithOnePlace;

public class PlayersCloseToPriceRiseFilter implements PlayerFilter {

    private final DoubleWithOnePlace threshold;

    public PlayersCloseToPriceRiseFilter(double threshold) {
        this.threshold = new DoubleWithOnePlace(threshold);
    }

    @Override
    public boolean test(Player player) {
        return player.getTarget().compareTo(threshold) >= 0;
    }
}
