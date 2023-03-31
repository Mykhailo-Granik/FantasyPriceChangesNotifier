package destination.telegram;

import lombok.extern.log4j.Log4j2;
import properties.ApplicationProperties;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.nio.charset.StandardCharsets.UTF_8;

@Log4j2
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
        log.info("Sending message to Telegram: {}...", message.substring(0, Math.min(message.length(), 100)));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(buildUri(message)))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Telegram response: {}", response.body());
        } catch (Exception e) {
            log.error("Error sending message to Telegram", e);
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
