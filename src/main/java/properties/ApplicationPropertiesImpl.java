package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ApplicationPropertiesImpl implements ApplicationProperties {

    private final Properties props;

    public ApplicationPropertiesImpl() throws IOException {
        props = new Properties();
        FileInputStream propertiesInputStream = new FileInputStream("src/main/resources/application.properties");
        props.load(propertiesInputStream);
        propertiesInputStream.close();
    }

    @Override
    public String getString(String key) {
        return props.getProperty(key);
    }
}
