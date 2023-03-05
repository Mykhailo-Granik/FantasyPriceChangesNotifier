package sender;

import common.Player;
import destination.Destination;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import retriever.Retriever;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SenderTest {

    public static final List<Player> PLAYERS = List.of(
            Player.createPlayerWithTarget(90.0),
            Player.createPlayerWithTarget(95.0)
    );

    @Test
    public void allDesntinationsShouldReceiveRetrievedPlayers() {
        // given
        TestingRetriever retriever = new TestingRetriever();
        TestingDestination destination1 = new TestingDestination();
        TestingDestination destination2 = new TestingDestination();
        Sender sender = new Sender(retriever, List.of(destination1, destination2));

        // when
        sender.send();

        // then
        assertEquals(PLAYERS, destination1.receivedPlayers);
        assertEquals(PLAYERS, destination2.receivedPlayers);
    }

    @Test
    public void shouldNotFailWhenThereAreNoDestinations() {
        // given
        TestingRetriever retriever = new TestingRetriever();
        Sender sender = new Sender(retriever, Collections.emptyList());

        // when
        sender.send();

        // then
        // no exception
    }

    private static class TestingRetriever implements Retriever {

        @Override
        public List<Player> retrieve() {
            return PLAYERS;
        }
    }

    @Getter
    private static class TestingDestination implements Destination {

        private List<Player> receivedPlayers;

        @Override
        public void send(List<Player> players) {
            this.receivedPlayers = players;
        }
    }


}