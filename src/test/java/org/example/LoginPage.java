package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public static final By INPUT_USERNAME = By.xpath("//input[@name='username']");
    public static final By INPUT_PASSWORD = By.xpath("//input[@name='password']");
    public static final By BUTTON_LOGIN = By.cssSelector("div[class='login-button-home'] button");
    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
