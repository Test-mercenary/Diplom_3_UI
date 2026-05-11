package tests;

import api.TestUser;
import api.UserApiClient;
import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

@Feature("Регистрация пользователя")
public class RegistrationTest extends BaseTest {

    private final UserApiClient userApiClient = new UserApiClient();
    private TestUser registeredUser;

    @After
    public void deleteUser() {
        if (registeredUser != null) {
            Response loginResponse = userApiClient.loginUser(registeredUser);
            String accessToken = loginResponse.path("accessToken");

            if (accessToken != null) {
                userApiClient.deleteUser(accessToken);
            }
        }
    }

    @Test
    @Story("Успешная регистрация")
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверяем, что пользователь может успешно зарегистрироваться и после регистрации открывается страница входа")
    public void registerNewUserShouldOpenLoginPage() {
        registeredUser = TestUser.getRandomUser();

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();

        registerPage.register(
                registeredUser.name,
                registeredUser.email,
                registeredUser.password
        );

        Assert.assertTrue("После успешной регистрации должна открыться страница входа",
                loginPage.isLoginPageOpened());
    }

    @Test
    @Story("Ошибка при некорректном пароле")
    @DisplayName("Ошибка при регистрации с коротким паролем")
    @Description("Проверяем, что при вводе пароля короче 6 символов отображается ошибка «Некорректный пароль»")
    public void registerUserWithShortPasswordShouldShowError() {
        TestUser user = new TestUser(
                "ui-test-user-" + System.currentTimeMillis() + "@yandex.ru",
                "12345",
                "Test User"
        );

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();

        registerPage.register(
                user.name,
                user.email,
                user.password
        );

        Assert.assertTrue("Для пароля меньше 6 символов должна отображаться ошибка",
                registerPage.isIncorrectPasswordErrorDisplayed());
    }
}