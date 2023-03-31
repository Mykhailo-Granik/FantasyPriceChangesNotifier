package retriever.fplstatistics;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
public class FPLStatisticsDataURLRetriever {

    private final FPLStatisticsRequestsRetriever fplStatisticsRequestsRetriever;

    public String dataURL() {
        log.info("Retrieving FPL Statistics data URL");
        return fplStatisticsRequestsRetriever.dataURL().stream()
                .filter(url -> url.contains("fplstatistics.co.uk/Home/AjaxPrices"))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("FPL Statistics data URL not found"));
    }

}
