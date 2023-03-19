package retriever.fplstatistics;

import common.Player;
import common.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FPLStatisticsRetrieverTest {

    @Test
    public void whenOnePlayerIsReturnedByDataSourceResultShouldContainOnePlayer() {
        FPLStatisticsRetriever fplStatisticsRetriever = new FPLStatisticsRetriever(
                new TestingFPLStatisticsDataSource(
                        "{\"sEcho\":\"1\",\"iTotalRecords\":750,\"iTotalDisplayRecords\":750,\"aaData\":[[\"\",\"Cédric\",\"Fulham\",\"D\",\"A\",\"0.1\",\"4.2\",\"£4.2m\",\"0\",\"20 Mar 07:19 PM, GMT\",\"66353\",\"0.0\",\"0.0\",\"0\",\"0\",\"Cedric\",\"Bournemouth(A) West Ham(H) Everton(A) Leeds(H) \"]]}"
                )
        );
        assertEquals(List.of(player1()), fplStatisticsRetriever.retrieve());
    }

    @Test
    public void whenTwoPlayersAreReturnedByDataSourceResultShouldContainTwoPlayers() {
        FPLStatisticsRetriever fplStatisticsRetriever = new FPLStatisticsRetriever(
                new TestingFPLStatisticsDataSource(
                        "{\"sEcho\":\"1\",\"iTotalRecords\":750,\"iTotalDisplayRecords\":750,\"aaData\":[[\"\",\"Cédric\",\"Fulham\",\"D\",\"A\",\"0.1\",\"4.2\",\"£4.2m\",\"0\",\"20 Mar 07:19 PM, GMT\",\"66353\",\"0.0\",\"0.0\",\"0\",\"0\",\"Cedric\",\"Bournemouth(A) West Ham(H) Everton(A) Leeds(H) \"]," +
                                "[\"\",\"Xhaka\",\"Arsenal\",\"M\",\"A\",\"2.5\",\"4.9\",\"£4.9m\",\"0\",\"---\",\"747\",\"-97.4\",\"-97.4\",\"-1\",\"-1\",\"Xhaka\",\"Leeds(H) Liverpool(A) West Ham(A) Southampton(H) \"]]}"
                )
        );
        assertEquals(List.of(player1(), player2()), fplStatisticsRetriever.retrieve());
    }

    private Player player1() {
        return new Player(
                "Cédric",
                "Fulham",
                Position.DEF,
                4.2,
                0.0
        );
    }

    private Player player2() {
        return new Player(
                "Xhaka",
                "Arsenal",
                Position.MID,
                4.9,
                -97.4
        );
    }

    @Test
    public void shouldThrowAnExceptionWhenIncorrectJsonIsPassed() {
        FPLStatisticsRetriever fplStatisticsRetriever = new FPLStatisticsRetriever(
                new TestingFPLStatisticsDataSource("{[")
        );
        assertThrows(RuntimeException.class, fplStatisticsRetriever::retrieve);
    }

    private static class TestingFPLStatisticsDataSource extends FPLStatisticsDataSource {

        private final String data;

        public TestingFPLStatisticsDataSource(String data) {
            super(null, null);
            this.data = data;
        }

        @Override
        public String getData() {
            return data;
        }
    }

}