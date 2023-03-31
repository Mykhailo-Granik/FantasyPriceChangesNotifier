package destination.telegram;

import destination.Destination;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import splitter.MessageSplitter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Log4j2
public class TelegramDestination implements Destination {

    private final TelegramClient telegramClient;
    private final MessageSplitter messageSplitter;

    @Override
    public void send(List<String> messages) {
        log.info("Sending messages to Telegram destination");
        messages.forEach(this::sendInParts);
    }

    private void sendInParts(String message) {
        messageSplitter.split(message).forEach(telegramClient::sendMessage);
    }
}
