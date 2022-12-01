package ru.iq_soft;

import java.util.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

public class UserStoryesTest extends AbstractTest{
    JavascriptExecutor js = (JavascriptExecutor) getWebDriver();

    @Test
    @DisplayName("Тест-кейс №4: Выбор товара и добавление его в корзину, удаление его из корзины как позиции")
    public void testCase4() throws InterruptedException {
        new CommodPage(getWebDriver())
                .pressMainMenuItem()
                .pressCategoryItem()
                .pressCommodItem()
                .pressCommodVersionItem()
                .pressAddCommodToBacket()
                .pressBacketBtt();
        assertTrue(new BacketPage(getWebDriver()).getCommodText().equals("Trout Master Ridge Sbiro (12g Floating)"));
        String s = new BacketPage(getWebDriver())
                .pressDelCommod()
                .getPriceText();
        assertTrue(s.equals("0 р."));

        //результат теста
        logger.info("Тест-кейс №4 пройден");
    }

    @Test
    @DisplayName("Тест-кейс №6: Проверка работы формы ввода личных данных")
    public void testCase6()  throws InterruptedException {
        // тестовые действия
        getWebDriver().get("https://ribomaniya.ru");
        new MainPage(getWebDriver()).pressLoginBtt();
        new LoginPage(getWebDriver())
                .setLogin("solsol")
                .setPassword("solsol297")
                .pressInBtt();
        assertTrue(new MainPage(getWebDriver()).checkUser("Евгений"));
        logger.debug(" - тесткейс № 6");
        new MainPage(getWebDriver()).pressCabinetBtt();
        new CabinetPage(getWebDriver())
                .pressPersonlBtt()
                .setNameField("Иван")
                .setSecondNameField("Иванович")
                .setLastNameField("Иванов")
                .clearNewPasswordField()
                .pressApplyBtt();

        // проверка
        getWebDriver().get("https://ribomaniya.ru/cabinet/personal/?");
        assertTrue(new MainPage(getWebDriver()).checkUser("Иван"));
        logger.debug(" - тесткейс № 6 : пользователь сохранен");
        new CabinetPage(getWebDriver())
                .pressPersonlBtt()
                .setNameField("Александр")
                .setSecondNameField("Александрович")
                .setLastNameField("Александров")
                .clearNewPasswordField()
                .pressApplyBtt();
        getWebDriver().get("https://ribomaniya.ru/cabinet/personal/?");
        assertTrue(new MainPage(getWebDriver()).checkUser("Александр"));
        logger.debug(" - тесткейс № 6 : данные тостового пользователя восстановлены");

        logger.info("Тест-кейс №6 пройден");
    }
}