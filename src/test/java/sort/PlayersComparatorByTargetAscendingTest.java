package sort;

import common.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayersComparatorByTargetAscendingTest {

    private PlayersComparatorByTargetAscending underTest;

    @BeforeEach
    public void setup() {
        underTest = new PlayersComparatorByTargetAscending();
    }

    @Test
    public void shouldReturnOneWhenFirstPlayerHasHigherTarget() {
        assertEquals(1, underTest.compare(new Player(99.5), new Player(99.4)));
    }

    @Test
    public void shouldReturnZeroWhenPlayersHaveSameTarget() {
        assertEquals(0, underTest.compare(new Player(99.5), new Player(99.5)));
    }

    @Test
    public void shouldReturnMinusOneWhenFirstPlayerHasLowerTarget() {
        assertEquals(-1, underTest.compare(new Player(99.4), new Player(99.5)));
    }

}