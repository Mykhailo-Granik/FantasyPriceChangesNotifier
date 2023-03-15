package retriever.fplstatistics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retriever.TestingHttpGetRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FPLStatisticsDataSourceTest {

    public static final String DATA_URL = "https://fplstatistics.co.uk/Home/AjaxPrices";
    private TestingHttpGetRequest httpGetRequest;
    private FPLStatisticsDataSource underTest;

    @BeforeEach
    public void setup() {
        httpGetRequest = new TestingHttpGetRequest();
        underTest = new FPLStatisticsDataSource(
                httpGetRequest,
                new TestingFPLDataURLRetriever(null)
        );
    }

    @Test
    public void shouldSendRequestToProvidedURL() {
        underTest.getData();
        assertEquals(DATA_URL, httpGetRequest.getLastUrl());
    }

    @Test
    public void shouldReturnProvidedData() {
        assertEquals(TestingHttpGetRequest.EXPECTED_DATA, underTest.getData());
    }

    private static class TestingFPLDataURLRetriever extends FPLStatisticsDataURLRetriever {
        public TestingFPLDataURLRetriever(FPLStatisticsRequestsRetriever fplStatisticsRequestsRetriever) {
            super(fplStatisticsRequestsRetriever);
        }

        @Override
        public String dataURL() {
            return DATA_URL;
        }
    }

}