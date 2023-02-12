package sender;

import common.Player;
import destination.AbstractDestination;
import filter.PlayerFilter;
import filter.TargetFilter;
import org.junit.jupiter.api.Test;
import retriever.Retriever;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SenderIntegrationTest {

    @Test
    public void shouldSendAllPlayersWhenTargetIsLowerThanThreshold() {
        // given
        TestingRetriever retriever = new TestingRetriever();
        TestingDestination destination = new TestingDestination(new TargetFilter(89.9));
        Sender sender = new Sender(retriever, List.of(destination));

        // when
        sender.send();

        // then
        List<Player> receivedPlayers = destination.getReceivedPlayers();
        assertEquals(2, receivedPlayers.size());
    }

    @Test
    public void shouldSendOnePlayerWhenTargetIsInTheMiddle() {
        // given
        TestingRetriever retriever = new TestingRetriever();
        TestingDestination destination = new TestingDestination(new TargetFilter(92.5));
        Sender sender = new Sender(retriever, List.of(destination));

        // when
        sender.send();

        // then
        List<Player> receivedPlayers = destination.getReceivedPlayers();
        assertEquals(1, receivedPlayers.size());
    }

    @Test
    public void shouldReturnNoPlayersWhenTargetIsHigh() {
        // given
        TestingRetriever retriever = new TestingRetriever();
        TestingDestination destination = new TestingDestination(new TargetFilter(95.1));
        Sender sender = new Sender(retriever, List.of(destination));

        // when
        sender.send();

        // then
        List<Player> receivedPlayers = destination.getReceivedPlayers();
        assertEquals(0, receivedPlayers.size());
    }

    @Test
    public void shouldSendAllPlayersWhenThresholdEqualsToLowerTarget() {
        // given
        TestingRetriever retriever = new TestingRetriever();
        TestingDestination destination = new TestingDestination(new TargetFilter(90.0));
        Sender sender = new Sender(retriever, List.of(destination));

        // when
        sender.send();

        // then
        List<Player> receivedPlayers = destination.getReceivedPlayers();
        assertEquals(2, receivedPlayers.size());
    }

    @Test
    public void shouldSendOnePlayerWhenThresholdEqualsToHigherTarget() {
        // given
        TestingRetriever retriever = new TestingRetriever();
        TestingDestination destination = new TestingDestination(new TargetFilter(95.0));
        Sender sender = new Sender(retriever, List.of(destination));

        // when
        sender.send();

        // then
        List<Player> receivedPlayers = destination.getReceivedPlayers();
        assertEquals(1, receivedPlayers.size());
    }

    @Test
    public void shouldCorrectlyHandleMultipleDestinations() {
        // given
        TestingRetriever retriever = new TestingRetriever();
        TestingDestination destination1 = new TestingDestination(new TargetFilter(90.0));
        TestingDestination destination2 = new TestingDestination(new TargetFilter(96.0));
        Sender sender = new Sender(retriever, List.of(destination1, destination2));

        // when
        sender.send();

        // then
        assertEquals(2, destination1.receivedPlayers.size());
        assertEquals(0, destination2.receivedPlayers.size());
    }

    private class TestingRetriever implements Retriever {

        @Override
        public List<Player> retrieve() {
            return List.of(
                    Player.createPlayerWithTarget(90.0),
                    Player.createPlayerWithTarget(95.0)
            );
        }
    }

    private class TestingDestination extends AbstractDestination {

        private List<Player> receivedPlayers;

        public TestingDestination(PlayerFilter playerFilter) {
            super(playerFilter);
        }

        public List<Player> getReceivedPlayers() {
            return receivedPlayers;
        }

        @Override
        protected void sendFilteredPlayers(List<Player> players) {
            receivedPlayers = players;
        }
    }


}