package destination;

import common.Player;

import java.util.List;

public interface Destination {

    void send(List<Player> players, List<String> messages);

}
