package message;

import common.Player;

import java.util.List;

public interface Message {

    String createMessage(List<Player> allPlayers);

}
