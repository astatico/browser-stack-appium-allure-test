package test.task.managers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private static final String BROWSERSTACK_USERNAME = "BROWSERSTACK_USERNAME";
    private static final String BROWSERSTACK_ACCESS_KEY = "BROWSERSTACK_ACCESS_KEY";

    private static Map<String, Object> browserStackYamlMap;

    private static AppiumDriver driver;

    public DriverManager() {
        var file = new File(System.getProperty("user.dir") + "/browserstack.yml");
        browserStackYamlMap = convertYamlFileToMap(file, new HashMap<>());
    }

    public static AppiumDriver getDriver() {
        return DriverManager.driver;
    }

    @SneakyThrows
    private Map<String, Object> convertYamlFileToMap(File yamlFile, Map<String, Object> map) {
        @Cleanup InputStream inputStream = Files.newInputStream(yamlFile.toPath());
        Map<String, Object> config = new Yaml().load(inputStream);
        map.putAll(config);
        return map;
    }

    @SneakyThrows
    public AppiumDriver createDriver() {
        if (driver == null) {
            var options = new UiAutomator2Options();
            options.setCapability("app", "bs://4f67766f14de8757fa4d6320b0d8394cfe38a01d");

            options.setCapability("deviceName", "Samsung Galaxy S22 Ultra");
            options.setCapability("platformVersion", "12.0");

            String userName = System.getenv(BROWSERSTACK_USERNAME) != null
                    ? System.getenv(BROWSERSTACK_USERNAME)
                    : (String) browserStackYamlMap.get("userName");

            String accessKey = System.getenv(BROWSERSTACK_ACCESS_KEY) != null
                    ? System.getenv(BROWSERSTACK_ACCESS_KEY)
                    : (String) browserStackYamlMap.get("accessKey");

            return driver = new AndroidDriver(
                    new URL(String.format("https://%s:%s@hub.browserstack.com/wd/hub", userName, accessKey)), options);

        } else return driver;
    }

    public void closeDriver() {
        driver.quit();
    }
}
