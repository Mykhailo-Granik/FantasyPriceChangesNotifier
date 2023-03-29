package sort;

import common.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayersComparatorByTargetDescendingTest {

    private PlayersComparatorByTargetDescending underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PlayersComparatorByTargetDescending();
    }

    @Test
    public void shouldReturnOneWhenFirstPlayerHasLowerTarget() {
        assertEquals(1, underTest.compare(new Player(91.0), new Player(92.0)));
    }

    @Test
    public void shouldReturnZeroWhenBothPlayersHaveSameTarget() {
        assertEquals(0, underTest.compare(new Player(-90.0), new Player(-90.0)));
    }

    @Test
    public void shouldReturnMinusOneWhenFirstPlayerHasHigherTarget() {
        assertEquals(-1, underTest.compare(new Player(92.0), new Player(91.0)));
    }

}