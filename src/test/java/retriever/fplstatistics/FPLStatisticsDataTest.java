package retriever.fplstatistics;

import common.Player;
import common.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FPLStatisticsDataTest {

    @Test
    public void shouldCorrectlyReturnListOfZeroPlayers() {
        FPLStatisticsData underTest = new FPLStatisticsData(List.of());
        assertTrue(underTest.players().isEmpty());
    }

    @Test
    public void shouldCorrectlyReturnListOfOnePlayer() {
        FPLStatisticsData fplStatisticsData = new FPLStatisticsData(List.of(player1Data()));
        assertEquals(List.of(player1()), fplStatisticsData.players());
    }

    @Test
    public void shouldCorrectlyReturnListOfTwoPlayers() {
        FPLStatisticsData fplStatisticsData = new FPLStatisticsData(List.of(player2Data(), player1Data()));
        assertEquals(List.of(player2(), player1()), fplStatisticsData.players());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenDataIsNullWhenGettingPlayers() {
        assertThrows(NullPointerException.class, () -> new FPLStatisticsData(null).players());
    }

    private List<String> player1Data() {
        return List.of(
                "",
                "Leno",
                "Fulham",
                "G",
                "A",
                "3.2",
                "4.5",
                "£4.5m",
                "0",
                "---",
                "5607",
                "91.5",
                "91.5",
                "2",
                "2",
                "Leno",
                "Bournemouth(A) West Ham(H) Everton(A) Leeds(H) "
        );
    }

    private Player player1() {
        return new Player("Leno", "Fulham", Position.GK, 4.5, 91.5);
    }

    private List<String> player2Data() {
        return List.of(
                "",
                "Jesus",
                "Arsenal",
                "F",
                "A",
                "25.2",
                "7.9",
                "£7.9m",
                "0",
                "---",
                "130063",
                "-55.3",
                "-55.3",
                "1",
                "1",
                "Jesus",
                "Leeds(H) Liverpool(A) West Ham(A) Southampton(H) "
        );
    }

    private Player player2() {
        return new Player("Jesus", "Arsenal", Position.FWD, 7.9, -55.3);
    }

}