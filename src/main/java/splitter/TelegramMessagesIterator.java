package splitter;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TelegramMessagesIterator implements Iterator<String> {

    private final List<String> parts;
    private final int maxMessageLength;
    private int index;

    public TelegramMessagesIterator(String message, int maxMessageLength) {
        this.maxMessageLength = maxMessageLength;
        index = 0;
        parts = Arrays.stream(message.split("\n"))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasNext() {
        return index < parts.size();
    }

    @Override
    public String next() {
        StringBuilder result = new StringBuilder();
        while (index < parts.size()) {
            if (sizeAfterAddition(result) > maxMessageLength) {
                break;
            }
            appendLine(result);
            index++;
        }
        return result.toString();
    }

    private void appendLine(StringBuilder currentMessage) {
        currentMessage.append(parts.get(index));
        if (shouldAppendLineBreak()) {
            currentMessage.append("\n");
        }
    }

    private int sizeAfterAddition(StringBuilder result) {
        int sizeAfterAddition = result.length() + parts.get(index).length();
        if (shouldAppendLineBreak()) {
            sizeAfterAddition++;
        }
        return sizeAfterAddition;
    }

    private boolean shouldAppendLineBreak() {
        return index + 1 < parts.size();
    }
}
