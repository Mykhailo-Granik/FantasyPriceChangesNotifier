package retriever.fantasyfootballhub;

import http.HttpGetRequest;
import lombok.RequiredArgsConstructor;
import retriever.datasource.DataSource;

@RequiredArgsConstructor
public class FantasyFootballHubDataSource implements DataSource {

    private final HttpGetRequest httpGetRequest;

    @Override
    public String getData() {
        return httpGetRequest.send("https://www.fantasyfootballhub.co.uk/api/player-data");
    }
}
