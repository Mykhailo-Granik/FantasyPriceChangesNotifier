package properties;

import java.util.Map;

public class DummyApplicationProperties implements ApplicationProperties {

    private static final Map<String, String> properties =
            Map.of(
                    "telegram.token", "6133902016:AAGBCD5NLmG9v4JH6yJhOFaAkUGndTyZNzY",
                    "telegram.chatId", "372848213"
            );

    @Override
    public String getString(String key) {
        return properties.get(key);
    }

}
