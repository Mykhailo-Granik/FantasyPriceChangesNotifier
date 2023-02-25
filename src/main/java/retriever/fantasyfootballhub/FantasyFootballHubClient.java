package retriever.fantasyfootballhub;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FantasyFootballHubClient {

    public String playerData() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.fantasyfootballhub.co.uk/api/player-data"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
