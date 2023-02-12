package sender;

import common.Player;
import destination.Destination;
import filter.PlayerFilter;
import lombok.RequiredArgsConstructor;
import retriever.Retriever;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Sender {

    private final Retriever retriever;
    private final PlayerFilter playerFilter;
    private final List<Destination> destinations;

    public void send() {
        destinations.forEach(d -> d.send(playersMatchingFilter()));
    }

    private List<Player> playersMatchingFilter() {
        return retriever.retrieve().stream()
                .filter(playerFilter)
                .collect(Collectors.toList());
    }

}
