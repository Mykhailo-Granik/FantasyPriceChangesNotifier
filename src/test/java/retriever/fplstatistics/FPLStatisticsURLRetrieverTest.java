package retriever.fplstatistics;

import org.junit.jupiter.api.Test;
import properties.ApplicationPropertiesImpl;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FPLStatisticsURLRetrieverTest {

    @Test
    public void shouldReturnRequestWithFPLPlayersData() throws IOException {
        FPLStatisticsURLRetriever underTest = new FPLStatisticsURLRetriever(new ApplicationPropertiesImpl());
        String url = underTest.dataURL();
        assertTrue(url.contains("www.fplstatistics.co.uk/Home/AjaxPricesEHandler"));
    }

}