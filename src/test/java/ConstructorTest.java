import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Assert;
import org.junit.Test;

@Feature("Раздел Конструктор")
public class ConstructorTest extends BaseTest {

    @Test
    @Story("Переход к разделу «Булки»")
    public void clickBunsTabShouldActivateBunsSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();

        Assert.assertTrue("После клика должен быть активен раздел «Булки»",
                mainPage.isBunsTabActive());
    }

    @Test
    @Story("Переход к разделу «Соусы»")
    public void clickSaucesTabShouldActivateSaucesSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesTab();

        Assert.assertTrue("После клика должен быть активен раздел «Соусы»",
                mainPage.isSaucesTabActive());
    }

    @Test
    @Story("Переход к разделу «Начинки»")
    public void clickFillingsTabShouldActivateFillingsSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickFillingsTab();

        Assert.assertTrue("После клика должен быть активен раздел «Начинки»",
                mainPage.isFillingsTabActive());
    }
}