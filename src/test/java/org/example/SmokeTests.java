package org.example;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class SmokeTests {

    WebDriver driver = DriverFactory.createDriverChrome();

    @Test
    public void creditTest() {
        driver.get("https://www.bspb.ru/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        Actions actions = new Actions(driver, Duration.ofSeconds(8));
        WebElement creditMenu = driver.findElement(By.xpath("//button[./p[text()='Кредиты']]"));
        actions.moveToElement(creditMenu).perform();
        WebElement creditCash = driver.findElement(By.cssSelector("section a[href$='cash']")); //section a[href$='cash']
        actions.moveToElement(creditCash).click().perform();
        WebElement oformitCredit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'chakra-container')]/div/div/a[contains(@href,'loanapplication')]")));
        wait.until(ExpectedConditions.elementToBeClickable(oformitCredit));
        actions.moveToElement(oformitCredit).click().perform();
        WebElement loginInput = driver.findElement(By.xpath("//input[@name='username']")); //section a[href$='cash']
        assertTrue("Элемента нет", loginInput.isDisplayed());
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
        assertTrue("Элемент для ввода пароля не найден", passwordInput.isDisplayed());
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='login-button-home'] button")));
        assertTrue("Кнопка входа не найдена", loginButton.isDisplayed());
    }

    @Test
    public void creditTestSoft() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.goToCredits();
        CreditCashPage creditCashPage = mainPage.goToCashCredit();
        LoginPage loginPage = creditCashPage.goToLoginPage();
        loginPage.isElementsVisible();
        //TODO (идея)можно добавить промежуточную базовую страницу, которая позволяет пользоваться меню главной страницы
    }





    /*@Test
    public void familyIpotekaTest() {
        FamilyIpotekaPage familyIpotekaPage = new FamilyIpotekaPage(driver);
        familyIpotekaPage.open();
        SoftAssertions softly = new SoftAssertions();
        isElementDisplayed(FamilyIpotekaPage.ZAYAVKA_BUTTON,"проверка отображения кнопки заявки", softly);
        isElementDisplayed(FamilyIpotekaPage.PARAMETERS_BUTTON,"проверка отображения кнопки параметры", softly);
        familyIpotekaPage.clickParameters();
        isElementDisplayed(FamilyIpotekaPage.PARAMETRS_TABLE, "проверка отображения таблицы", softly);
        softly.assertAll();

    }*/
}
