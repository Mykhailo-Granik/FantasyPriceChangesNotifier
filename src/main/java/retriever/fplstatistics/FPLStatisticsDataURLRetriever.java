package retriever.fplstatistics;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FPLStatisticsDataURLRetriever {

    private final FPLStatisticsRequestsRetriever fplStatisticsRequestsRetriever;

    public String dataURL() {
        return fplStatisticsRequestsRetriever.dataURL().stream()
                .filter(url -> url.contains("fplstatistics.co.uk/Home/AjaxPrices"))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("FPL Statistics data URL not found"));
    }

}
