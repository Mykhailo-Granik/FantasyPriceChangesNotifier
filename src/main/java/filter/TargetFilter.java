package filter;

import common.Player;
import lombok.RequiredArgsConstructor;
import util.DoubleWithOnePlace;

public class TargetFilter implements PlayerFilter {

    private final DoubleWithOnePlace threshold;

    public TargetFilter(double threshold) {
        this.threshold = new DoubleWithOnePlace(threshold);
    }

    @Override
    public boolean test(Player player) {
        return player.getTarget().compareTo(threshold) >= 0;
    }
}
