package retriever.fplstatistics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import retriever.Retriever;

import java.util.List;


@RequiredArgsConstructor
@Log4j2
public class FPLStatisticsRetriever implements Retriever {

    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final FPLStatisticsDataSource fplStatisticsDataSource;

    @Override
    public List<Player> retrieve() {
        try {
            log.info("Retrieving player data from FPL Statistics");
            FPLStatisticsData fplStatisticsData =
                    objectMapper.readValue(fplStatisticsDataSource.getData(), FPLStatisticsData.class);
            return fplStatisticsData.players();
        } catch (JsonProcessingException e) {
            log.error("Error retrieving player data from FPL Statistics", e);
            throw new RuntimeException(e);
        }
    }


}
