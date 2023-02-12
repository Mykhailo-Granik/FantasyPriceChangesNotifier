package sender;

import common.Player;
import destination.Destination;
import filter.TargetFilter;
import org.junit.jupiter.api.Test;
import retriever.Retriever;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SenderIntegrationTest {

    @Test
    public void shouldSendAllPlayersWhenTargetIsLowerThanThreshold() {
        // given
        TestingRetriever retriever = new TestingRetriever();
        TestingDestination destination = new TestingDestination();
        Sender sender = new Sender(retriever, new TargetFilter(89.9), List.of(destination));

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
        TestingDestination destination = new TestingDestination();
        Sender sender = new Sender(retriever, new TargetFilter(92.5), List.of(destination));

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
        TestingDestination destination = new TestingDestination();
        Sender sender = new Sender(retriever, new TargetFilter(95.1), List.of(destination));

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
        TestingDestination destination = new TestingDestination();
        Sender sender = new Sender(retriever, new TargetFilter(90.0), List.of(destination));

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
        TestingDestination destination = new TestingDestination();
        Sender sender = new Sender(retriever, new TargetFilter(95.0), List.of(destination));

        // when
        sender.send();

        // then
        List<Player> receivedPlayers = destination.getReceivedPlayers();
        assertEquals(1, receivedPlayers.size());
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

    private class TestingDestination implements Destination {

        private List<Player> receivedPlayers;

        @Override
        public void send(List<Player> players) {
            receivedPlayers = players;
        }

        public List<Player> getReceivedPlayers() {
            return receivedPlayers;
        }
    }


}