package retriever.fplstatistics;

import http.HttpGetRequest;
import lombok.RequiredArgsConstructor;
import retriever.datasource.DataSource;

@RequiredArgsConstructor
public class FPLStatisticsDataSource implements DataSource {

    private final HttpGetRequest httpGetRequest;
    private final FPLStatisticsDataURLRetriever fplStatisticsDataURLRetriever;

    @Override
    public String getData() {
        return httpGetRequest.send(fplStatisticsDataURLRetriever.dataURL());
    }
}
