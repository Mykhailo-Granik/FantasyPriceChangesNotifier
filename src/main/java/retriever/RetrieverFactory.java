package retriever;

import retriever.fantasyfootballhub.FantasyFootballHubClient;
import retriever.fantasyfootballhub.FantasyFootballHubRetriever;

public class RetrieverFactory {

    public Retriever create() {
        return new FantasyFootballHubRetriever(
                new FantasyFootballHubClient()
        );
    }

}
