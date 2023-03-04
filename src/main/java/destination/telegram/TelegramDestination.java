package destination.telegram;

import common.Player;
import destination.AbstractDestination;
import filter.PlayerFilter;

import java.util.List;
import java.util.stream.Collectors;

public class TelegramDestination extends AbstractDestination {

    private final TelegramClient telegramClient;

    public TelegramDestination(PlayerFilter playerFilter, TelegramClient telegramClient) {
        super(playerFilter);
        this.telegramClient = telegramClient;
    }

    @Override
    protected void sendFilteredPlayers(List<Player> players) {
        telegramClient.sendMessage(message(players));
    }

    private String message(List<Player> players) {
        return players.stream()
                .map(Player::toString)
                .collect(Collectors.joining("\n"));
    }
}
