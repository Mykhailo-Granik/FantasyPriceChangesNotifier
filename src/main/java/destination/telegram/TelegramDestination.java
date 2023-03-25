package destination.telegram;

import common.Player;
import destination.Destination;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import message.Message;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class TelegramDestination implements Destination {

    private final List<Message> messages;
    private final TelegramClient telegramClient;

    @Override
    public void send(List<Player> players, List<String> messages) {
        messages.forEach(telegramClient::sendMessage);
    }
}
