package filter;

import common.Player;

public class AllMatchFilter implements PlayerFilter {

    @Override
    public boolean test(Player player) {
        return true;
    }

}
