package http;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
public class HttpGetRequestImpl implements HttpGetRequest {

    private final HttpClient httpClient;

    @Override
    public String send(String url) {
        HttpRequest request = createRequest(url);
        HttpResponse<String> response;
        try {
            response = sendRequest(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (requestWasSuccessful(response)) {
            return response.body();
        } else {
            throw new RuntimeException(
                    String.format("Request failed with status code %d, url = %s", response.statusCode(), url)
            );
        }
    }

    private HttpRequest createRequest(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }

    private HttpResponse<String> sendRequest(HttpRequest request) throws IOException, InterruptedException {
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private boolean requestWasSuccessful(HttpResponse<String> response) {
        return response.statusCode() >= 200 && response.statusCode() < 300;
    }
}
