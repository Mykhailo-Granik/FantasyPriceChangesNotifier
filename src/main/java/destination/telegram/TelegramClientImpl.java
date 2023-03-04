package destination.telegram;

import properties.ApplicationProperties;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.nio.charset.StandardCharsets.*;

public class TelegramClientImpl implements TelegramClient {

    private final ApplicationProperties applicationProperties;
    private final HttpClient client;

    public TelegramClientImpl(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        this.client = HttpClient.newHttpClient();
    }

    public TelegramClientImpl(ApplicationProperties applicationProperties, HttpClient client) {
        this.applicationProperties = applicationProperties;
        this.client = client;
    }

    @Override
    public void sendMessage(String message) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(buildUri(message)))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private String buildUri(String message) {
        return String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
                applicationProperties.getString("telegram.token"),
                applicationProperties.getString("telegram.chatId"),
                URLEncoder.encode(message, UTF_8)
        );
    }


}
