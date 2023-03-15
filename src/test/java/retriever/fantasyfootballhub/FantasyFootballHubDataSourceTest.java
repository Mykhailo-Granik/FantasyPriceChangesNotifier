package retriever.fantasyfootballhub;

import org.junit.jupiter.api.Test;
import retriever.TestingHttpGetRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FantasyFootballHubDataSourceTest {

    @Test
    public void shouldSendRequestToCorrectUrl() {
        TestingHttpGetRequest httpGetRequest = new TestingHttpGetRequest();
        FantasyFootballHubDataSource underTest = new FantasyFootballHubDataSource(httpGetRequest);
        underTest.getData();
        assertEquals("https://www.fantasyfootballhub.co.uk/api/player-data", httpGetRequest.getLastUrl());
    }

    @Test
    public void shouldReturnExpectedData() {
        TestingHttpGetRequest httpGetRequest = new TestingHttpGetRequest();
        FantasyFootballHubDataSource underTest = new FantasyFootballHubDataSource(httpGetRequest);
        String result = underTest.getData();
        assertEquals(TestingHttpGetRequest.EXPECTED_DATA, result);
    }

}