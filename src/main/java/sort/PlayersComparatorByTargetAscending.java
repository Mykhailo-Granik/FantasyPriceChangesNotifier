package sort;

import common.Player;

public class PlayersComparatorByTargetAscending implements PlayersComparator {
    @Override
    public int compare(Player o1, Player o2) {
        return o1.getTarget().compareTo(o2.getTarget());
    }
}
