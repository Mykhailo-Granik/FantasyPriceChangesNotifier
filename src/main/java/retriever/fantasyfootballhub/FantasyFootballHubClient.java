package retriever.fantasyfootballhub;

import lombok.extern.log4j.Log4j2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Log4j2
public class FantasyFootballHubClient {

    public String playerData() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.fantasyfootballhub.co.uk/api/player-data"))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Retrieved player data from Fantasy Football Hub with status code {}", response.statusCode());
            return response.body();
        } catch (Exception e) {
            log.error("Error retrieving player data from Fantasy Football Hub", e);
            throw new RuntimeException(e);
        }
    }

}
