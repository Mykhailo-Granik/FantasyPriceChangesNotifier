package retriever.fplstatistics;

import org.junit.jupiter.api.Test;
import properties.ApplicationPropertiesImpl;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class FPLStatisticsRequestsRetrieverTest {

    @Test
    public void shouldReturnRequestWithFPLPlayersData() throws IOException {
        FPLStatisticsRequestsRetriever underTest = new FPLStatisticsRequestsRetriever(new ApplicationPropertiesImpl());
        assertFalse(underTest.dataURL().isEmpty());
    }

}