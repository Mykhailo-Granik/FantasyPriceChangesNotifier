package retriever.fplstatistics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Player;
import lombok.RequiredArgsConstructor;
import retriever.Retriever;

import java.util.List;


@RequiredArgsConstructor
public class FPLStatisticsRetriever implements Retriever {

    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final FPLStatisticsDataSource fplStatisticsDataSource;

    @Override
    public List<Player> retrieve() {
        try {
            FPLStatisticsData fplStatisticsData =
                    objectMapper.readValue(fplStatisticsDataSource.getData(), FPLStatisticsData.class);
            return fplStatisticsData.players();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
