package filter;

import common.Player;
import org.junit.jupiter.api.Test;
import util.DoubleWithOnePlace;

import static org.junit.jupiter.api.Assertions.*;

public class PlayersCloseToPriceFallFilterTest {

    @Test
    public void shouldSayPlayerIsCloseToPriceFallWhenItsTargetIsLessThanThreshold() {
        PlayersCloseToPriceFallFilter filter = new PlayersCloseToPriceFallFilter(new DoubleWithOnePlace(-95.0));
        Player player = Player.createPlayerWithTarget(-95.1);
        assertTrue(filter.test(player));
    }

    @Test
    public void shouldSayPlayerIsCloseToPriceFallWhenItsTargetIsEqualToThreshold() {
        PlayersCloseToPriceFallFilter filter = new PlayersCloseToPriceFallFilter(new DoubleWithOnePlace(-95.0));
        Player player = Player.createPlayerWithTarget(-95.0);
        assertTrue(filter.test(player));
    }

    @Test
    public void shouldSayPlayerIsNotCloseToPriceFallWhenItsTargetIsGreaterThanThreshold() {
        PlayersCloseToPriceFallFilter filter = new PlayersCloseToPriceFallFilter(new DoubleWithOnePlace(-95.0));
        Player player = Player.createPlayerWithTarget(-94.9);
        assertFalse(filter.test(player));
    }

}