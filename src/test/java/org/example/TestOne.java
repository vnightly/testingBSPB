package org.example;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.PublicKey;
import java.time.Duration;

import static org.junit.Assert.assertThat;

public class TestOne {
    static WebDriver driver = new ChromeDriver();

    @Test
    public void testone(){
        int x = 2;
        Assert.assertTrue(x >= 0);
    }
    @Test
    public void logPageTest(){
        driver.get("https://www.bspb.ru/");
        driver.getTitle();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        WebElement menuButton = driver.findElement(By.xpath("//*[@id=\"header-menu-submenu_height\"]/button"));
        menuButton.click();
        WebElement logButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/section/div[4]/button"));
        logButton.click();
        WebElement refWB = driver.findElement(By.xpath("/html/body/div[2]/div/div[4]/div/section/div/div[2]/a"));
        String ssilka = refWB.getAttribute("href");
        driver.get(ssilka);
    }

    @Test
    public void vkladTest(){
        String vkladValue = "100000";
        String stavka = "21.3 %";
        String testDohod = convertValue("10562");
        WebDriverWait wait = getWebDriverWaitVklad(8);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id=\"app-wrapper\"]/main/div/div[4]/div/div/div[2]/div[1]/ul/li[3]/span")));
        WebElement oformlenie = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app-wrapper\"]/main/div/div[4]/div/div/div[1]/div[1]/div/div/li[2]/label/span[2]")));
        oformlenie.click();
        WebElement srok181 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"app-wrapper\"]/main/div/div[4]/div/div/div[1]/div[3]/div[2]/div/div/ul/li[3]/label/span[2]")));
        srok181.click();
        Assert.assertEquals(stavka, driver.findElement(By.xpath("//*[@id=\"app-wrapper\"]/main/div/div[4]/div/div/div[2]/div[1]/div/div[2]/h2")).getText());
        WebElement summaVlkada = driver.findElement(By.xpath("//*[@id=\"app-wrapper\"]/main/div/div[4]/div/div/div[1]/div[3]/div[1]/div[1]/input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", summaVlkada);
        WebElement inputElement = wait.until(ExpectedConditions.elementToBeClickable(summaVlkada));
        inputElement.clear();
        inputElement.sendKeys(vkladValue);
        wait.until(ExpectedConditions.textToBe(By.xpath("/html/body/div[1]/div[3]/main/div/div[4]/div/div/div[2]/div[1]/div/div[1]/h2"), testDohod));

        WebElement dohod =  driver.findElement(By.xpath("/html/body/div[1]/div[3]/main/div/div[4]/div/div/div[2]/div[1]/div/div[1]/h2"));

        Assert.assertEquals(testDohod, dohod.getText());


    }

    private static WebDriverWait getWebDriverWaitVklad(int duration) {
        driver.get("https://www.bspb.ru/retail/deposits/osen?tab=docs");
        driver.getTitle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
        return wait;
    }

    @Test
    public void methodTest(){
        convertValue("2132133");
    }
    public String convertValue(String input){
        String formattedAmount = input.replaceAll("(\\d)(?=(\\d{3})+$)", "$1 ");
        formattedAmount += " ₽";
        System.out.println(formattedAmount);
        return formattedAmount;
    }



}
