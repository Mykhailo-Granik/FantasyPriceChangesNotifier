package destination.telegram;

import common.Player;
import message.DummyMessage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TelegramDestinationTest {
    private TelegramDestination underTest;
    private final TestingTelegramClient telegramClient = new TestingTelegramClient();

    @Test
    public void shouldCorrectlySendMessageForOneDestination() {
        DummyMessage message = new DummyMessage("message");
        underTest = new TelegramDestination(List.of(message), telegramClient);
        List<Player> players = List.of(
                new Player("player1", "club1"),
                new Player("player2", "club2")
        );
        underTest.send(players);
        assertEquals(message.createMessage(players), telegramClient.getMessages().get(0));
    }

    @Test
    public void shouldCorrectlySendMessageForTwoDestinations() {
        DummyMessage message1 = new DummyMessage("message1");
        DummyMessage message2 = new DummyMessage("message2");
        underTest = new TelegramDestination(List.of(message1, message2), telegramClient);
        List<Player> players = List.of(
                new Player("player1", "club1"),
                new Player("player2", "club2")
        );
        underTest.send(players);
        assertEquals(
                List.of(message1.createMessage(players), message2.createMessage(players)),
                telegramClient.getMessages()
        );
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