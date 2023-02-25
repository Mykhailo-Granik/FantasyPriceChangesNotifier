package retriever.fantasyfootballhub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Player;
import lombok.RequiredArgsConstructor;
import retriever.Retriever;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class FantasyFootballHubRetriever implements Retriever {

    private final FantasyFootballHubClient fantasyFootballHubClient;

    private final static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public List<Player> retrieve() {
        try {
            return getFantasyFootballHubPlayers().stream()
                    .map(FantasyFootballHubPlayer::toPlayer)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<FantasyFootballHubPlayer> getFantasyFootballHubPlayers() throws JsonProcessingException {
        return objectMapper.readValue(
                fantasyFootballHubClient.playerData(), new TypeReference<>() {}
        );
    }
}
