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
public class FPLStatisticsRequestsRetriever {

    private final ApplicationProperties applicationProperties;
    private final List<String> requests = new ArrayList<>();

    public List<String> dataURL() {
        setChromeDriverPath();
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = createDevTools(driver);
        addRequestsListener(devTools);
        executeMainRequest(driver);
        closeDriver(driver);
        return requests;
    }

    private void setChromeDriverPath() {
        System.setProperty("webdriver.chrome.driver", applicationProperties.getString("webdriver.chrome.driver"));
    }

    private DevTools createDevTools(ChromeDriver driver) {
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        return devTools;
    }

    private void addRequestsListener(DevTools devTools) {
        devTools.addListener(Network.requestWillBeSent(),
                request -> requests.add(request.getRequest().getUrl()));
    }

    private void executeMainRequest(ChromeDriver driver) {
        driver.get("http://www.fplstatistics.co.uk/Home/IndexAndroid");
    }

    private void closeDriver(ChromeDriver driver) {
        driver.quit();
    }

}
