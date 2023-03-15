package retriever.fantasyfootballhub;

import http.HttpGetRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FantasyFootballHubDataSourceTest {

    @Test
    public void shouldSendRequestToCorrectUrl() {
        TestingHttpGetRequest httpGetRequest = new TestingHttpGetRequest();
        FantasyFootballHubDataSource underTest = new FantasyFootballHubDataSource(httpGetRequest);
        underTest.getData();
        assertEquals("https://www.fantasyfootballhub.co.uk/api/player-data", httpGetRequest.lastUrl);
    }

    @Test
    public void shouldReturnExpectedData() {
        TestingHttpGetRequest httpGetRequest = new TestingHttpGetRequest();
        FantasyFootballHubDataSource underTest = new FantasyFootballHubDataSource(httpGetRequest);
        String result = underTest.getData();
        assertEquals(TestingHttpGetRequest.EXPECTED_DATA, result);
    }

    private static class TestingHttpGetRequest implements HttpGetRequest {

        public static final String EXPECTED_DATA = "expected data";
        private String lastUrl;

        @Override
        public String send(String url) {
            lastUrl = url;
            return EXPECTED_DATA;
        }
    }

}