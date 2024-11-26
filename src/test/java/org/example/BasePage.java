package org.example;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.Duration;

public class BasePage {

    public WebDriverWait wait;
    public Actions actions;
    public WebDriver driver;
    public SoftAssertions softly = new SoftAssertions();

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        this.actions = new Actions(driver, Duration.ofSeconds(8));
    }
    public void isElementsVisible(){
        Field[] fields = this.getClass().getDeclaredFields();
        for(Field field : fields){
            if(Modifier.isStatic(field.getModifiers()) && field.getType().equals(By.class)){
                try{
                    field.setAccessible(true);
                    By by = (By) field.get(null);
                    isElementDisplayedSoftlyCheck(by, field.getName());
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
        softly.assertAll();
    }
    private void isElementDisplayedSoftlyCheck(By by, String elementName) {
        WebElement element = driver.findElement(by);
        softly.assertThat(element.isDisplayed())
                .as("проверка отображения элемента: %s", elementName)
                .isTrue();
    }

/*    public void isMainElementsDisplayed() {
        isElementDisplayedSoftlyCheck(INPUT_USERNAME, "ввода для логина");
        isElementDisplayedSoftlyCheck(INPUT_PASSWORD, "ввода для пароля");
        isElementDisplayedSoftlyCheck(BUTTON_LOGIN, "кнопки входа");
        softly.assertAll();
    }*/

}
