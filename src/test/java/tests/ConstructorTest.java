package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import pages.MainPage;

@Feature("Раздел Конструктор")
public class ConstructorTest extends BaseTest {

    @Test
    @Story("Переход к разделу «Булки»")
    @DisplayName("Переход к разделу «Булки»")
    @Description("Проверяем, что после клика по разделу «Булки» он становится активным")
    public void clickBunsTabShouldActivateBunsSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();

        Assert.assertTrue("После клика должен быть активен раздел «Булки»",
                mainPage.isBunsTabActive());
    }

    @Test
    @Story("Переход к разделу «Соусы»")
    @DisplayName("Переход к разделу «Соусы»")
    @Description("Проверяем, что после клика по разделу «Соусы» он становится активным")
    public void clickSaucesTabShouldActivateSaucesSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesTab();

        Assert.assertTrue("После клика должен быть активен раздел «Соусы»",
                mainPage.isSaucesTabActive());
    }

    @Test
    @Story("Переход к разделу «Начинки»")
    @DisplayName("Переход к разделу «Начинки»")
    @Description("Проверяем, что после клика по разделу «Начинки» он становится активным")
    public void clickFillingsTabShouldActivateFillingsSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickFillingsTab();

        Assert.assertTrue("После клика должен быть активен раздел «Начинки»",
                mainPage.isFillingsTabActive());
    }
}