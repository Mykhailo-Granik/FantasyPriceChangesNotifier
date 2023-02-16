package filter;

import common.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayersCloseToPriceRiseFilterTest {

    @Test
    public void shouldSayPlayerIsNotCloseToPriceRiseWhenItsTargetIsSmallerThanThreshold() {
        PlayersCloseToPriceRiseFilter filter = new PlayersCloseToPriceRiseFilter(95.0);
        Player player = Player.createPlayerWithTarget(94.9);
        assertFalse(filter.test(player));
    }

    @Test
    public void shouldSayPlayerIsNotCloseToPriceRiseWhenItsTargetIsEqualToThreshold() {
        PlayersCloseToPriceRiseFilter filter = new PlayersCloseToPriceRiseFilter(95.0);
        Player player = Player.createPlayerWithTarget(95.0);
        assertTrue(filter.test(player));
    }

    @Test
    public void shouldSayPlayerIsCloseToPriceRiseWhenItsTargetIsLargerThanThreshold() {
        PlayersCloseToPriceRiseFilter filter = new PlayersCloseToPriceRiseFilter(95.0);
        Player player = Player.createPlayerWithTarget(95.1);
        assertTrue(filter.test(player));
    }




}