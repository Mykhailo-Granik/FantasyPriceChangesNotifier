package common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    @Test
    public void shouldCorrectlyConvertToString() {
        Player player = new Player("player1", "club1", Position.FWD, 1.0, 2.0);
        assertEquals("player1(club1), 1.0£, target=2.0", player.toString());
    }

}