package retriever.fantasyfootballhub;

import common.Player;
import common.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FantasyFootballHubPlayerTest {

    public static final String NAME = "Name";
    public static final String CLUB = "Man City";

    @Test
    public void testCreationOfAGoalKeeper() {
        FantasyFootballHubPlayer fantasyFootballHubPlayer = new FantasyFootballHubPlayer(
                NAME,
                new FantasyFootballHubPlayer.Team(CLUB),
                new FantasyFootballHubPlayer.Data(1, priceInfo(5.5, 90.3))
        );
        Player player = new Player(
                NAME, CLUB, Position.GK, 5.5, 90.3
        );
        assertEquals(player, fantasyFootballHubPlayer.toPlayer());
    }

    private FantasyFootballHubPlayer.PriceInfo priceInfo(double value, double target) {
        return new FantasyFootballHubPlayer.PriceInfo(value, target);
    }

    @Test
    public void testCreationOfADefender() {
        FantasyFootballHubPlayer fantasyFootballHubPlayer = new FantasyFootballHubPlayer(
                NAME,
                new FantasyFootballHubPlayer.Team(CLUB),
                new FantasyFootballHubPlayer.Data(2, priceInfo(5.0, 95.421))
        );
        Player player = new Player(
                NAME, CLUB, Position.DEF, 5.0, 95.4
        );
        assertEquals(player, fantasyFootballHubPlayer.toPlayer());
    }

    @Test
    public void testCreationOfAMidfielder() {
        FantasyFootballHubPlayer fantasyFootballHubPlayer = new FantasyFootballHubPlayer(
                NAME,
                new FantasyFootballHubPlayer.Team(CLUB),
                new FantasyFootballHubPlayer.Data(3, priceInfo(12.0, 70.56))
        );
        Player player = new Player(
                NAME, CLUB, Position.MID, 12.0, 70.6
        );
        assertEquals(player, fantasyFootballHubPlayer.toPlayer());
    }

    @Test
    public void testCreationOfAForward() {
        FantasyFootballHubPlayer fantasyFootballHubPlayer = new FantasyFootballHubPlayer(
                NAME,
                new FantasyFootballHubPlayer.Team(CLUB),
                new FantasyFootballHubPlayer.Data(4, priceInfo(9.2, 50.18))
        );
        Player player = new Player(
                NAME, CLUB, Position.FWD, 9.2, 50.2
        );
        assertEquals(player, fantasyFootballHubPlayer.toPlayer());
    }

}