package retriever;

import common.TestingApplicationProperties;
import org.junit.jupiter.api.Test;
import properties.ApplicationProperties;
import retriever.fantasyfootballhub.FantasyFootballHubRetriever;
import retriever.fplstatistics.FPLStatisticsRetriever;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RetrieverFactoryTest {

    @Test
    public void shouldCreateFPLStatisticsRetriever() {
        ApplicationProperties applicationProperties = new TestingApplicationProperties(
                Map.of("retriever", "fplstatistics")
        );
        RetrieverFactory underTest = new RetrieverFactory(applicationProperties);
        assertTrue(underTest.create() instanceof FPLStatisticsRetriever);
    }

    @Test
    public void shouldCreateFantasyFootballHubRetriever() {
        ApplicationProperties applicationProperties = new TestingApplicationProperties(
                Map.of("retriever", "fantasyfootballhub")
        );
        RetrieverFactory underTest = new RetrieverFactory(applicationProperties);
        assertTrue(underTest.create() instanceof FantasyFootballHubRetriever);
    }

    @Test
    public void shouldThrowExceptionWhenRetrieverIsNotSupported() {
        ApplicationProperties applicationProperties = new TestingApplicationProperties(
                Map.of("retriever", "notsupported")
        );
        RetrieverFactory underTest = new RetrieverFactory(applicationProperties);
        assertThrows(IllegalArgumentException.class, underTest::create);
    }

}