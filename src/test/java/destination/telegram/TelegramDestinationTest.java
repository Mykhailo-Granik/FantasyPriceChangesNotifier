package destination.telegram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TelegramDestinationTest {
    private TelegramDestination underTest;
    private final TestingTelegramClient telegramClient = new TestingTelegramClient();

    @BeforeEach
    public void setup() {
        underTest = new TelegramDestination(telegramClient, null);
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