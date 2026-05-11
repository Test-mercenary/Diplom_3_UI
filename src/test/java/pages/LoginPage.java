package pages;

import api.TestUser;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginTitle = By.xpath("//h2[text()='Вход']");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By forgotPasswordLink = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Заполнить email")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
    }

    @Step("Заполнить пароль")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
    }

    @Step("Нажать кнопку «Войти»")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Войти в аккаунт")
    public void login(TestUser user) {
        setEmail(user.email);
        setPassword(user.password);
        clickLoginButton();
    }

    @Step("Нажать ссылку «Зарегистрироваться»")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Нажать ссылку «Восстановить пароль»")
    public void clickForgotPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
    }

    @Step("Проверить, что открыта страница входа")
    public boolean isLoginPageOpened() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginTitle)).isDisplayed();
    }
}