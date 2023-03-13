package retriever.fplstatistics;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FPLStatisticsDataURLRetrieverTest {

    @Test
    public void shouldReturnRequestWithFPLPlayersData() {
        FPLStatisticsDataURLRetriever underTest = new FPLStatisticsDataURLRetriever(statisticsRetrieverWithData());
        assertTrue(underTest.dataURL().contains("fplstatistics.co.uk/Home/AjaxPrices"));
    }

    private TestingFPLStatisticsRequestsRetriever statisticsRetrieverWithData() {
        return new TestingFPLStatisticsRequestsRetriever(
                List.of(
                        "www.google.com",
                        "http://fplstatistics.co.uk/Home/AjaxPricesIHandler?q3YtPLu4Vk=-212&pyseltype=0&_=1678726361765",
                        "www.facebook.com"
                )
        );
    }

    @Test
    public void shouldThrowAnExceptionWhenDataURLIsNotPresent() {
        FPLStatisticsDataURLRetriever underTest = new FPLStatisticsDataURLRetriever(statisticsRetrieverWithoutData());
        assertThrows(IllegalStateException.class, underTest::dataURL);
    }

    private FPLStatisticsRequestsRetriever statisticsRetrieverWithoutData() {
        return new TestingFPLStatisticsRequestsRetriever(Collections.emptyList());
    }

    private static class TestingFPLStatisticsRequestsRetriever extends FPLStatisticsRequestsRetriever {

        private final List<String> requests;

        public TestingFPLStatisticsRequestsRetriever(List<String> requests) {
            super(null);
            this.requests = requests;
        }

        @Override
        public List<String> dataURL() {
            return requests;
        }
    }

}