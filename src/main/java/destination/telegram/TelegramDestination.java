package destination.telegram;

import destination.Destination;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import splitter.MessageSplitter;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class TelegramDestination implements Destination {

    private final TelegramClient telegramClient;
    private final MessageSplitter messageSplitter;

    @Override
    public void send(List<String> messages) {
        messages.forEach(telegramClient::sendMessage);
    }
}
