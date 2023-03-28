package splitter;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TelegramMessageSplitter implements MessageSplitter {

    private final int maxMessageLength;

    public TelegramMessageSplitter(int maxMessageLength) {
        this.maxMessageLength = maxMessageLength;
    }

    @Override
    public List<String> split(String message) {
        TelegramMessagesIterator iterator = new TelegramMessagesIterator(message, maxMessageLength);
        List<String> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }
}
