package retriever.fplstatistics;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.network.Network;
import properties.ApplicationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class FPLStatisticsURLRetriever {

    private final ApplicationProperties applicationProperties;

    public List<String> allRequests() {
        System.setProperty("webdriver.chrome.driver", applicationProperties.getString("webdriver.chrome.driver"));
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        List<String> requests = new ArrayList<>();
        devTools.addListener(Network.requestWillBeSent(),
                request -> requests.add(request.getRequest().getUrl()));
        driver.get("http://www.fplstatistics.co.uk/Home/IndexAndroid");
        driver.quit();
        return requests;
    }

}
