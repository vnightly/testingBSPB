package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FamilyIpotekaPage extends BasePage{

    public static final By ZAYAVKA_BUTTON = By.cssSelector("div[role='tablist'] + div a");
    public static final By PARAMETERS_BUTTON = By.cssSelector("div[role='tablist']>button+button");
    public static final By PARAMETRS_TABLE = By.xpath("//div[./h2[text()='Параметры кредита']]/div/table");

    public FamilyIpotekaPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.bspb.ru/retail/mortgage/family");
    }

    public void clickParameters() {
        WebElement parameters = driver.findElement(PARAMETERS_BUTTON);
        actions.moveToElement(parameters).click().perform();
    }



}
