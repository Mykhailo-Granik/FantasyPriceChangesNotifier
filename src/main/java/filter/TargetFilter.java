package filter;

import common.Player;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TargetFilter implements PlayerFilter {

    private final double threshold;

    @Override
    public boolean test(Player player) {
        return player.getTarget() >= threshold;
    }
}
