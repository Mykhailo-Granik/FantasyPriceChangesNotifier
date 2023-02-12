package sender;

import common.Player;
import destination.Destination;
import filter.PlayerFilter;
import lombok.RequiredArgsConstructor;
import retriever.Retriever;

import java.util.List;

@RequiredArgsConstructor
public class Sender {

    private final Retriever retriever;
    private final List<Destination> destinations;

    public void send() {
        List<Player> players = retriever.retrieve();
        destinations.forEach(d -> d.send(players));
    }

}
