package retriever.fplstatistics;

import org.junit.jupiter.api.Test;
import properties.ApplicationPropertiesImpl;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FPLStatisticsURLRetrieverTest {

    @Test
    public void shouldReturnRequestWithFPLPlayersData() throws IOException {
        // given
        FPLStatisticsURLRetriever underTest = new FPLStatisticsURLRetriever(new ApplicationPropertiesImpl());

        // when
        List<String> requests = underTest.allRequests();

        // then
        boolean containsReRequestWithFPLPlayersData =
                requests.stream()
                        .anyMatch(request -> request.contains("www.fplstatistics.co.uk/Home/AjaxPricesEHandler"));
        assertTrue(containsReRequestWithFPLPlayersData);
    }

}