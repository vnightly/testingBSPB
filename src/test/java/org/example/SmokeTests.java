package org.example;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class SmokeTests {

    WebDriver driver = DriverFactory.createDriverChrome();

    @Test
    public void creditTestSoft() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.goToCredits();
        CreditCashPage creditCashPage = mainPage.goToCashCredit();
        LoginPage loginPage = creditCashPage.goToLoginPage();
        loginPage.isElementsVisible();
    }

}
