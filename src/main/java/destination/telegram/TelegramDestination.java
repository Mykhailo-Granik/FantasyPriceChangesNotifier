package destination.telegram;

import common.Player;
import destination.Destination;
import lombok.RequiredArgsConstructor;
import message.Message;

import java.util.List;

@RequiredArgsConstructor
public class TelegramDestination implements Destination {

    private final List<Message> messages;
    private final TelegramClient telegramClient;

    @Override
    public void send(List<Player> players) {
        messages.forEach(m -> telegramClient.sendMessage(m.createMessage(players)));
    }
}
