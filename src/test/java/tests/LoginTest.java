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
import org.junit.Before;
import org.junit.Test;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

@Feature("Вход пользователя")
public class LoginTest extends BaseTest {

    private final UserApiClient userApiClient = new UserApiClient();
    private TestUser user;
    private String accessToken;

    @Before
    public void createUser() {
        user = TestUser.getRandomUser();

        Response response = userApiClient.createUser(user);
        accessToken = response.path("accessToken");
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            userApiClient.deleteUser(accessToken);
        }
    }

    @Test
    @Story("Вход по кнопке «Войти в аккаунт» на главной")
    @DisplayName("Вход по кнопке «Войти в аккаунт»")
    @Description("Проверяем, что пользователь может войти через кнопку «Войти в аккаунт» на главной странице")
    public void loginByLoginButtonOnMainPageShouldOpenMainPageForAuthorizedUser() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickLoginButton();
        loginPage.login(user);

        Assert.assertTrue("После входа должна отображаться кнопка «Оформить заказ»",
                mainPage.isOrderButtonDisplayed());
    }

    @Test
    @Story("Вход через кнопку «Личный кабинет»")
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверяем, что пользователь может войти через кнопку «Личный кабинет» в шапке сайта")
    public void loginByPersonalAccountButtonShouldOpenMainPageForAuthorizedUser() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickPersonalAccountButton();
        loginPage.login(user);

        Assert.assertTrue("После входа должна отображаться кнопка «Оформить заказ»",
                mainPage.isOrderButtonDisplayed());
    }

    @Test
    @Story("Вход через кнопку в форме регистрации")
    @DisplayName("Вход через форму регистрации")
    @Description("Проверяем, что пользователь может перейти ко входу со страницы регистрации и успешно авторизоваться")
    public void loginByLoginButtonOnRegistrationPageShouldOpenMainPageForAuthorizedUser() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.clickLoginLink();
        loginPage.login(user);

        Assert.assertTrue("После входа должна отображаться кнопка «Оформить заказ»",
                mainPage.isOrderButtonDisplayed());
    }

    @Test
    @Story("Вход через кнопку в форме восстановления пароля")
    @DisplayName("Вход через форму восстановления пароля")
    @Description("Проверяем, что пользователь может перейти ко входу со страницы восстановления пароля и успешно авторизоваться")
    public void loginByLoginButtonOnForgotPasswordPageShouldOpenMainPageForAuthorizedUser() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        mainPage.clickLoginButton();
        loginPage.clickForgotPasswordLink();
        forgotPasswordPage.clickLoginLink();
        loginPage.login(user);

        Assert.assertTrue("После входа должна отображаться кнопка «Оформить заказ»",
                mainPage.isOrderButtonDisplayed());
    }
}