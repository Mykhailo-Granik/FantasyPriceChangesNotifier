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
    private final Destination destination;

    public void send() {
        destination.send(playersMatchingFilter());
    }

    private List<Player> playersMatchingFilter() {
        return retriever.retrieve().stream()
                .filter(playerFilter)
                .collect(Collectors.toList());
    }

}
