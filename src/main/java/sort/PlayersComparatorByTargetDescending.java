package sort;

import common.Player;

public class PlayersComparatorByTargetDescending implements PlayersComparator {
    @Override
    public int compare(Player o1, Player o2) {
        return o2.getTarget().compareTo(o1.getTarget());
    }
}
