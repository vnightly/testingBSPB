package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreditCashPage extends BasePage{

    public static final By OFORMLENIE_CREDITA = By.xpath("//div[contains(@class,'chakra-container')]/div/div/a[contains(@href,'loanapplication')]");

    public CreditCashPage(WebDriver driver){
        super(driver);
    }
    public void open() {
        driver.get("https://www.bspb.ru/retail/consumer-loan/cash");
    }

    public LoginPage goToLoginPage(){
        WebElement oformitCredit = wait.until(ExpectedConditions.visibilityOfElementLocated(OFORMLENIE_CREDITA));
        wait.until(ExpectedConditions.elementToBeClickable(oformitCredit));
        actions.moveToElement(oformitCredit).click().perform();
        return new LoginPage(driver);
    }

}
