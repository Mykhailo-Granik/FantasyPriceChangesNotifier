package sender;

import common.Player;
import destination.Destination;
import lombok.RequiredArgsConstructor;
import message.Message;
import retriever.Retriever;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Sender {

    private final Retriever retriever;
    private final List<Message> messages;
    private final List<Destination> destinations;

    public void send() {
        List<Player> players = retriever.retrieve();
        List<String> messagesAsString = messagesAsString(players);
        destinations.forEach(d -> d.send(players, messagesAsString));
    }

    private List<String> messagesAsString(List<Player> players) {
        return messages.stream()
                .map(m -> m.createMessage(players))
                .collect(Collectors.toList());
    }

}
