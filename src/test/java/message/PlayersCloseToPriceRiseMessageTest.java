package message;

import common.Player;
import common.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayersCloseToPriceRiseMessageTest {

    private PlayersCloseToPriceRiseMessage underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PlayersCloseToPriceRiseMessage(96.0);
    }

    @Test
    public void shouldReturnCorrectMessageWhenSomePlayersPassFilter() {
        Player player2 = new Player("Player 2", "club2", Position.GK, 5.0, 96.1);
        Player player3 = new Player("Player 3", "club3", Position.GK, 4.0, 100.0);
        String message = underTest.createMessage(
                List.of(
                        new Player("Player 1", "club1", Position.GK, 10.0, 95.9),
                        player2,
                        player3
                )
        );
        assertEquals(
                "Players close to price rise ⬆️⬆️⬆️\n" + player2 + "\n" + player3,
                message
        );
    }

    @Test
    public void shouldReturnCorrectMessageWhenNoPlayersPassFilter() {
        String message = underTest.createMessage(
                List.of(
                        new Player("Player 1", "club1", Position.GK, 10.0, 95.9),
                        new Player("Player 2", "club2", Position.GK, 5.0, 95.8),
                        new Player("Player 3", "club3", Position.GK, 4.0, 94.0)
                )
        );
        assertEquals(
                "No players close to price rise",
                message
        );
    }

}