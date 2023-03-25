package destination.telegram;

import common.TestingApplicationProperties;
import common.TestingHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URLEncoder;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TelegramClientImplTest {

    public static final String TELEGRAM_TOKEN_KEY = "telegram.token";
    public static final String TELEGRAM_CHAT_ID_KEY = "telegram.chatId";
    public static final String TELEGRAM_TOKEN_VALUE = "1234567890:ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String TELEGRAM_CHAT_ID_VALUE = "123456789";
    private final TestingHttpClient httpClient = new TestingHttpClient();
    private TelegramClientImpl underTest;


    @BeforeEach
    void setUp() {
        underTest = new TelegramClientImpl(
                new TestingApplicationProperties(properties()),
                httpClient
        );
    }

    private Map<String, String> properties() {
        return Map.of(
                TELEGRAM_TOKEN_KEY, TELEGRAM_TOKEN_VALUE,
                TELEGRAM_CHAT_ID_KEY, TELEGRAM_CHAT_ID_VALUE
        );
    }

    @Test
    public void shouldCorrectlySendTelegramMessage() {
        String testMessage = "test message";
        underTest.sendMessage(testMessage);
        assertEquals(
                String.format(
                        "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
                        TELEGRAM_TOKEN_VALUE, TELEGRAM_CHAT_ID_VALUE, URLEncoder.encode(testMessage, UTF_8)
                ),
                httpClient.getLastSentRequest().uri().toString()
        );
    }

}