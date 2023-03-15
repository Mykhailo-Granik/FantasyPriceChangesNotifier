package retriever;

import http.HttpGetRequest;
import lombok.Getter;

@Getter
public class TestingHttpGetRequest implements HttpGetRequest {

    public static final String EXPECTED_DATA = "expected data";
    private String lastUrl;

    @Override
    public String send(String url) {
        lastUrl = url;
        return EXPECTED_DATA;
    }
}
