import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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