import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By profileLink = By.xpath("//a[text()='Профиль']");
    private final By logoutButton = By.xpath("//button[text()='Выход']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Проверить, что открыта страница личного кабинета")
    public boolean isAccountPageOpened() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(profileLink)).isDisplayed();
    }

    @Step("Нажать кнопку «Выход»")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
}