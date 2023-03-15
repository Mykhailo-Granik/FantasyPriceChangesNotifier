package retriever;

import http.HttpGetRequestImpl;
import retriever.fantasyfootballhub.FantasyFootballHubDataSource;
import retriever.fantasyfootballhub.FantasyFootballHubRetriever;

import java.net.http.HttpClient;

public class RetrieverFactory {

    public Retriever create() {
        return new FantasyFootballHubRetriever(
                new FantasyFootballHubDataSource(
                        new HttpGetRequestImpl(HttpClient.newHttpClient())
                )
        );
    }

}
