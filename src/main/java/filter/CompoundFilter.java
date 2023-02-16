package filter;

import common.Player;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CompoundFilter implements PlayerFilter {

    private final List<PlayerFilter> filters;
    @Override
    public boolean test(Player player) {
        return filters.stream().anyMatch(filter -> filter.test(player));
    }
}
