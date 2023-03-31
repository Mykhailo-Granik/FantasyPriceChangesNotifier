package retriever.fantasyfootballhub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import retriever.Retriever;
import retriever.datasource.DataSource;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
public class FantasyFootballHubRetriever implements Retriever {

    private final DataSource dataSource;

    private final static ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public List<Player> retrieve() {
        log.info("Retrieving player data from Fantasy Football Hub");
        try {
            return getFantasyFootballHubPlayers().stream()
                    .map(FantasyFootballHubPlayer::toPlayer)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (JsonProcessingException e) {
            log.error("Error retrieving player data from Fantasy Football Hub", e);
            throw new RuntimeException(e);
        }
    }

    private List<FantasyFootballHubPlayer> getFantasyFootballHubPlayers() throws JsonProcessingException {
        return objectMapper.readValue(
                dataSource.getData(), new TypeReference<>() {
                }
        );
    }
}
