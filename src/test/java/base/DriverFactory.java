package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DriverFactory {

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        if ("yandex".equals(browser)) {
            options.setBinary(getYandexBrowserPath());

            WebDriverManager.chromedriver()
                    .browserVersion(System.getProperty("yandex.browser.version", "146"))
                    .setup();

            return new ChromeDriver(options);
        }

        WebDriverManager.chromedriver()
                .clearDriverCache()
                .setup();

        return new ChromeDriver(options);
    }

    private static String getYandexBrowserPath() {
        String customPath = System.getProperty("yandex.binary");

        if (customPath != null && !customPath.isBlank()) {
            return customPath;
        }

        List<String> possiblePaths = List.of(
                System.getenv("LOCALAPPDATA") + "\\Yandex\\YandexBrowser\\Application\\browser.exe",
                "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe",
                "C:\\Program Files (x86)\\Yandex\\YandexBrowser\\Application\\browser.exe"
        );

        for (String path : possiblePaths) {
            if (path != null && Files.exists(Path.of(path))) {
                return path;
            }
        }

        throw new RuntimeException("Не найден Яндекс Браузер. Передай путь через -Dyandex.binary=\"путь_к_browser.exe\"");
    }
}