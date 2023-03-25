package common;

import lombok.RequiredArgsConstructor;
import properties.ApplicationProperties;

import java.util.Map;

@RequiredArgsConstructor
public class TestingApplicationProperties implements ApplicationProperties {

    private final Map<String, String> properties;

    @Override
    public String getString(String key) {
        return properties.get(key);
    }
}
