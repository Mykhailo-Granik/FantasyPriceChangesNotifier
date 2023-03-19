package retriever;

import http.HttpGetRequestImpl;
import lombok.RequiredArgsConstructor;
import properties.ApplicationProperties;
import retriever.fantasyfootballhub.FantasyFootballHubDataSource;
import retriever.fantasyfootballhub.FantasyFootballHubRetriever;
import retriever.fplstatistics.FPLStatisticsDataSource;
import retriever.fplstatistics.FPLStatisticsDataURLRetriever;
import retriever.fplstatistics.FPLStatisticsRequestsRetriever;
import retriever.fplstatistics.FPLStatisticsRetriever;

import java.net.http.HttpClient;

@RequiredArgsConstructor
public class RetrieverFactory {

    private final ApplicationProperties applicationProperties;

    public Retriever create() {
        return fplStatisticsRetriever();
    }

    private FPLStatisticsRetriever fplStatisticsRetriever() {
        return new FPLStatisticsRetriever(
                new FPLStatisticsDataSource(
                        new HttpGetRequestImpl(HttpClient.newHttpClient()),
                        new FPLStatisticsDataURLRetriever(
                                new FPLStatisticsRequestsRetriever(applicationProperties)
                        )
                )
        );
    }

    private FantasyFootballHubRetriever fantasyFootballHubRetriever() {
        return new FantasyFootballHubRetriever(
                new FantasyFootballHubDataSource(
                        new HttpGetRequestImpl(HttpClient.newHttpClient())
                )
        );
    }

}
