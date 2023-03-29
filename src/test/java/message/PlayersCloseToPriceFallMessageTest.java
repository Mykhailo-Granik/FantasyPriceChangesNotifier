package message;

import common.Player;
import common.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayersCloseToPriceFallMessageTest {

    private PlayersCloseToPriceFallMessage underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PlayersCloseToPriceFallMessage(-95.0);
    }

    @Test
    public void shouldReturnCorrectMessageWhenSomePlayersPassFilter() {
        Player player1 = new Player("Player 1", "club1", Position.GK, 10.0, -95.9);
        Player player2 = new Player("Player 2", "club2", Position.GK, 5.0, -94.1);
        Player player3 = new Player("Player 3", "club3", Position.GK, 4.0, -101.0);
        String message = underTest.createMessage(List.of(player1, player2, player3));
        assertEquals(
                "Players close to price fall  ⬇️⬇️⬇️️\n" + player3 + "\n" + player1,
                message
        );
    }

    @Test
    public void shouldReturnCorrectMessageWhenNoPlayersPassFilter() {
        String message = underTest.createMessage(
                List.of(
                        new Player("Player 1", "club1", Position.GK, 10.0, -94.9),
                        new Player("Player 2", "club2", Position.GK, 5.0, -93.8),
                        new Player("Player 3", "club3", Position.GK, 4.0, -92.0)
                )
        );
        assertEquals(
                "No players close to price fall",
                message
        );
    }

}