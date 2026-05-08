import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver driver;

    protected static final String BASE_URL = "https://stellarburgers.education-services.ru/";

    @Before
    public void setUp() {
        driver = DriverFactory.createDriver();
        driver.get(BASE_URL);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}