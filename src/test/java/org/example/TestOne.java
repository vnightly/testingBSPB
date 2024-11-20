package org.example;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.junit.Before;
import org.junit.After;

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
    public void vkladTest() throws InterruptedException {
        String vkladValue = "100000";
        String stavka = "21.3 %";
        String testDohod = convertValue("10562");
        WebDriverWait wait = webDriverPageWait(10, "https://www.bspb.ru/retail/deposits/osen?tab=docs");
        WebElement offlineButton = driver.findElement(By.xpath("//label[./input[@value='offline']]//span[contains(@class,'chakra-radio__label')]"));
        Actions actions = new Actions(driver);
        //Thread.sleep(3000);
        actions.moveToElement(offlineButton).click().perform();
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("window.scrollBy(0, 1500);");
        //wait.until(ExpectedConditions.elementToBeClickable(offlineButton));
        //offlineButton.click(); //Action.click(offllinebuitton);
        WebElement srok181 = driver.findElement(By.xpath("//*[@id=\"app-wrapper\"]/main/div/div[4]/div/div/div[1]/div[3]/div[2]/div/div/ul/li[3]/label/span[2]"));
        //js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", srok181);
        //js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", srok181);
        actions.moveToElement(srok181).perform();
        wait.until(ExpectedConditions.elementToBeClickable(srok181));
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
    @Test
    public void ipotekaTest(){
        String flatPrice = "12000000";
        String period = "120";
        String firstPay = " 1 400 000";
        WebDriverWait mainPageWait = webDriverPageWait(15 ,"https://www.bspb.ru/" );
        WebElement ipoteka = driver.findElement(By.xpath("//button[text()='Ипотека']"));
        mainPageWait.until(ExpectedConditions.elementToBeClickable(ipoteka));
        Actions actions = new Actions(driver);
        actions.moveToElement(ipoteka).click(ipoteka).perform();
        WebElement inFlatPrice = driver.findElement(By.xpath("//div[@class='css-pxyno3']/p[text()='Стоимость недвижимости']//following::input"));
        fillInput(inFlatPrice, flatPrice);

        WebElement inputPeriod = driver.findElement(By.xpath("//div[@class='css-pxyno3']/p[text()='Срок']//following::input"));
        //fillInput(inputPeriod, period);
        mainPageWait.until(ExpectedConditions.elementToBeClickable(inputPeriod));
        inputPeriod.clear();
        mainPageWait.until(ExpectedConditions.elementToBeClickable(inputPeriod));
        actions.sendKeys(inputPeriod, period).perform();
        WebElement inputFirstPay = driver.findElement(By.xpath("//div[@class='css-pxyno3']/p[text()='Первоначальный взнос']//following::input"));
        mainPageWait.until(ExpectedConditions.elementToBeClickable(inputFirstPay));
        inputFirstPay.clear();
        mainPageWait.until(ExpectedConditions.elementToBeClickable(inputFirstPay));
        actions.sendKeys(inputFirstPay,firstPay).perform();
    }

    private static void fillInput(WebElement input, String text) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        Actions actions = new Actions(driver);
//        wait.until(ExpectedConditions.elementToBeClickable(input));
//        actions.moveToElement(input).click(input).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).sendKeys(text).perform();
//        wait.until(ExpectedConditions.elementToBeClickable(input));
        input.clear();

        input.sendKeys(text);

    }

    private static WebDriverWait webDriverPageWait(int duration, String url) {
        driver.manage().window().maximize();
        driver.get(url);
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
//    @After
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();  // Закрываем браузер после выполнения теста
//        }
//    }



}
