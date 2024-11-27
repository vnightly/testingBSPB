package org.example;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    public static final By INPUT_USERNAME = By.xpath("//input[@name='username']");
    public static final By INPUT_PASSWORD = By.xpath("//input[@name='password']");
    public static final By BUTTON_LOGIN = By.cssSelector("div[class='login-button-home'] button");
    SoftAssertions softly = new SoftAssertions();
    public void open() {
        driver.get("https://i.bspb.ru/auth?response_type=code&client_id=1&redirect_uri=https%3A%2F%2Fi.bspb.ru%2Flogin%2" +
                "Fsuccess&prefetch_uri=https%3A%2F%2Fi.bspb.ru%2Flogin%2Fprefetch&force_new_session=true&state=%2Floanap" +
                "plications%2Fpersonal");
    }
    public void isMainElementsDisplayed() {
        isElementDisplayedSoftlyCheck(INPUT_USERNAME, "ввода для логина");
        isElementDisplayedSoftlyCheck(INPUT_PASSWORD, "ввода для пароля");
        isElementDisplayedSoftlyCheck(BUTTON_LOGIN, "кнопки входа");
        softly.assertAll();
    }

    private void isElementDisplayedSoftlyCheck(By by, String elementName) {
        WebElement element = driver.findElement(by);
        softly.assertThat(element.isDisplayed())
                .as("проверка отображения элемента: %s", elementName)
                .isTrue();
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
