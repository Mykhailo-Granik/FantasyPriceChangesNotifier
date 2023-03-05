package destination.telegram;

import common.Player;
import message.DummyMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TelegramDestinationTest {

    private final DummyMessage message = new DummyMessage();
    private TelegramDestination underTest;
    private final TestingTelegramClient telegramClient = new TestingTelegramClient();

    @BeforeEach
    public void setup() {
        underTest = new TelegramDestination(message, telegramClient);
    }

    @Test
    public void shouldCorrectlySendMessageWhenPlayersAfterFilteringArePresent() {
        List<Player> players = List.of(
                new Player("player1", "club1"),
                new Player("player2", "club2")
        );
        underTest.send(players);
        assertEquals(message.createMessage(players), telegramClient.getMessage());
    }

    private static class TestingTelegramClient implements TelegramClient {

        private String message;

        @Override
        public void sendMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }


}