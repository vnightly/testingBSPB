package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    public static final By INPUT_USERNAME = By.xpath("//input[@name='username']");
    public static final By INPUT_PASSWORD = By.xpath("//input[@name='password']");
    public static final By BUTTON_LOGIN = By.cssSelector("div[class='login-button-home'] button");

    public void open() {
        //driver.manage().window().maximize();
        driver.get("https://i.bspb.ru/auth?response_type=code&client_id=1&redirect_uri=https%3A%2F%2Fi.bspb.ru%2Flogin%2" +
                "Fsuccess&prefetch_uri=https%3A%2F%2Fi.bspb.ru%2Flogin%2Fprefetch&force_new_session=true&state=%2Floanap" +
                "plications%2Fpersonal");
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
