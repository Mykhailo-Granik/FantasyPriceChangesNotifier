package filter;

import common.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TargetFilterTest {

    @Test
    public void testPlayerWithTargetLessThanThreshold() {
        TargetFilter filter = new TargetFilter(10.0);
        Player player = Player.createPlayerWithTarget(9.0);
        assertFalse(filter.test(player));
    }

    @Test
    public void testPlayerWithTargetEqualToThreshold() {
        TargetFilter filter = new TargetFilter(10.0);
        Player player = Player.createPlayerWithTarget(10.0);
        assertTrue(filter.test(player));
    }

    @Test
    public void testPlayerWithTargetGreaterThanThreshold() {
        TargetFilter filter = new TargetFilter(10.0);
        Player player = Player.createPlayerWithTarget(11.0);
        assertTrue(filter.test(player));
    }




}