package sender;

import common.Player;
import destination.Destination;
import lombok.Getter;
import message.Message;
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
    public void allMessagesShouldReceiveRetrievedPlayers() {
        // given
        TestingRetriever retriever = new TestingRetriever();
        TestingMessage message1 = new TestingMessage();
        TestingMessage message2 = new TestingMessage();
        Sender sender = new Sender(retriever, List.of(message1, message2), Collections.emptyList());

        // when
        sender.send();

        // then
        assertEquals(PLAYERS, message1.receivedPlayers);
        assertEquals(PLAYERS, message2.receivedPlayers);
    }


    @Test
    public void destinationsShouldReceiveCorrectMessages() {
        TestingMessage message1 = new TestingMessage("message1");
        TestingMessage message2 = new TestingMessage("message2");
        TestingDestination destination1 = new TestingDestination();
        Sender sender = new Sender(new TestingRetriever(), List.of(message1, message2), List.of(destination1));
        sender.send();
        assertEquals(List.of("message1", "message2"), destination1.receivedMessages);
    }

    @Test
    public void shouldNotFailWhenThereAreNoDestinations() {
        // given
        TestingRetriever retriever = new TestingRetriever();
        Sender sender = new Sender(retriever, Collections.emptyList(), Collections.emptyList());

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


    private static class TestingMessage implements Message {

        private List<Player> receivedPlayers;
        private final String message;

        public TestingMessage() {
            this.message = "test message";
        }

        public TestingMessage(String message) {
            this.message = message;
        }

        @Override
        public String createMessage(List<Player> allPlayers) {
            receivedPlayers = allPlayers;
            return message;
        }
    }

    @Getter
    private static class TestingDestination implements Destination {

        private List<String> receivedMessages;

        @Override
        public void send(List<Player> players, List<String> messages) {
            this.receivedMessages = messages;
        }
    }


}