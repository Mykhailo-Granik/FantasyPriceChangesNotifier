package filter;

import common.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompoundFilterTest {

    @Test
    public void shouldSayPlayerMatchesFilterIfItMatchesAnyOfTheFilters() {
        CompoundFilter filter = new CompoundFilter(
                List.of(
                        new AlwaysTruePlayerFilter(),
                        new AlwaysFalsePlayerFilter()

                )
        );
        assertTrue(filter.test(new Player("name", "club")));
    }

    @Test
    public void shouldSayPlayerDoesNotMatchFilterIfItDoesNotMatchAnyOfTheFilters() {
        CompoundFilter filter = new CompoundFilter(
                List.of(
                        new AlwaysFalsePlayerFilter(),
                        new AlwaysFalsePlayerFilter()

                )
        );
        assertFalse(filter.test(new Player("name", "club")));
    }

    private static class AlwaysTruePlayerFilter implements PlayerFilter {
        @Override
        public boolean test(Player player) {
            return true;
        }
    }

    private static class AlwaysFalsePlayerFilter implements PlayerFilter {
        @Override
        public boolean test(Player player) {
            return false;
        }
    }

}