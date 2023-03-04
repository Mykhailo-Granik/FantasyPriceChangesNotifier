package destination.telegram;

import common.Player;
import filter.AllMatchFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TelegramDestinationTest {

    private TelegramDestination underTest;
    private final TestingTelegramClient telegramClient = new TestingTelegramClient();

    @BeforeEach
    public void setup() {
        underTest = new TelegramDestination(new AllMatchFilter(), telegramClient);
    }

    @Test
    public void shouldCorrectlySendMessageWhenPlayersAfterFilteringArePresent() {
        Player player1 = new Player("player1", "club1");
        Player player2 = new Player("player2", "club2");
        underTest.send(List.of(player1, player2));
        assertEquals(String.format("%s\n%s", player1, player2), telegramClient.getMessage());
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