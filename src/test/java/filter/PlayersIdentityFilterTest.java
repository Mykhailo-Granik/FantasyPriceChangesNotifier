package filter;

import common.Player;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PlayersIdentityFilterTest {

    @Test
    public void shouldSayPlayerMatchesFilterIfItIsStoredInTheFilter() {
        PlayersIdentityFilter filter = new PlayersIdentityFilter(
                Set.of(
                        new Player("name1", "club1"),
                        new Player("name2", "club2")
                )
        );
        assertTrue(filter.test(new Player("name1", "club1")));
    }

    @Test
    public void shouldSayPlayerDoesNotMatchFilterIfItIsNotStoredInTheFilter() {
        PlayersIdentityFilter filter = new PlayersIdentityFilter(
                Set.of(
                        new Player("name1", "club1"),
                        new Player("name2", "club2")
                )
        );
        assertFalse(filter.test(new Player("name3", "club3")));
    }

    @Test
    public void shouldSayPlayerDoesNotMatchFilterIfItIsStoredInTheFilterButHasDifferentClub() {
        PlayersIdentityFilter filter = new PlayersIdentityFilter(
                Set.of(
                        new Player("name1", "club1"),
                        new Player("name2", "club2")
                )
        );
        assertFalse(filter.test(new Player("name1", "club3")));
    }

    @Test
    public void shouldSayPlayerDoesNotMatchFilterIfItIsStoredInTheFilterButHasDifferentName() {
        PlayersIdentityFilter filter = new PlayersIdentityFilter(
                Set.of(
                        new Player("name1", "club1"),
                        new Player("name2", "club2")
                )
        );
        assertFalse(filter.test(new Player("name3", "club1")));
    }

}