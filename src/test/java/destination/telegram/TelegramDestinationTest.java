package destination.telegram;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import splitter.MessageSplitter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TelegramDestinationTest {
    private TelegramDestination underTest;
    private final TestingTelegramClient telegramClient = new TestingTelegramClient();

    @BeforeEach
    public void setup() {
        underTest = new TelegramDestination(telegramClient, new TestingDummyMessageSplitter());
    }

    @Test
    public void shouldCorrectlySendOneMessage() {
        String message = "message";
        underTest.send(List.of(message));
        assertEquals(message, telegramClient.getMessages().get(0));
    }

    @Test
    public void shouldCorrectlySendMessageForTwoDestinations() {
        String message1 = "message1";
        String message2 = "message2";
        underTest.send(List.of(message1, message2));
        assertEquals(List.of(message1, message2), telegramClient.getMessages());
    }

    @Test
    public void whenMessageSplitterReturnsSinglePartShouldSendOneMessage() {
        String message = "message";
        underTest = new TelegramDestination(telegramClient, new TestingDummyMessageSplitter());
        underTest.send(List.of(message));
        assertEquals(List.of(message), telegramClient.getMessages());
    }

    @Test
    public void whenMessageSplitterReturnsTwoPartsShouldSendTwoMessages() {
        String message = "message";
        underTest = new TelegramDestination(telegramClient, new EqualPartsSplitter(2));
        underTest.send(List.of(message));
        assertEquals(List.of("mess", "age"), telegramClient.getMessages());
    }

    @Test
    public void whenMessageSplitterReturnsThreePartsShouldSendThreeMessages() {
        String message = "message";
        underTest = new TelegramDestination(telegramClient, new EqualPartsSplitter(3));
        underTest.send(List.of(message));
        assertEquals(List.of("mes", "sag", "e"), telegramClient.getMessages());
    }

    private static class TestingDummyMessageSplitter implements MessageSplitter {
        @Override
        public List<String> split(String message) {
            return List.of(message);
        }
    }

    @RequiredArgsConstructor
    private static class EqualPartsSplitter implements MessageSplitter {

        private final int parts;

        @Override
        public List<String> split(String message) {
            List<String> result = new ArrayList<>();
            int partSize = (message.length() + parts - 1) / parts;
            for (int i = 0; i < parts; i++) {
                result.add(message.substring(i * partSize, Math.min((i + 1) * partSize, message.length())));
            }
            return result;
        }
    }

    private static class TestingTelegramClient implements TelegramClient {

        private final List<String> messages = new ArrayList<>();

        @Override
        public void sendMessage(String message) {
            this.messages.add(message);
        }

        public List<String> getMessages() {
            return messages;
        }
    }


}