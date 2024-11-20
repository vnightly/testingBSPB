package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MainPage extends BasePage {
    public static final By CREDIT_CASH = By.cssSelector("section a[href$='cash']");
    public static final By CREDIT_MENU = By.xpath("//button[./p[text()='Кредиты']]");

    public MainPage(WebDriver driver){
        super(driver);
    }

    public void open(){
       // driver.manage().window().maximize();
        driver.get("https://www.bspb.ru/");
    }

    public void goToCredits() {
        WebElement creditMenu = driver.findElement(CREDIT_MENU);
        actions.moveToElement(creditMenu).perform();
    }

    public CreditCashPage goToCashCredit() {
        WebElement creditCash = driver.findElement(CREDIT_CASH);
        actions.moveToElement(creditCash).click().perform();
        return new CreditCashPage(driver);
    }

}
