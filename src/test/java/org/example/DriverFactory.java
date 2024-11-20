package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {
    public static WebDriver createDriverChrome() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "latest");
        capabilities.setCapability("acceptInsecureCerts", true);
        //TODO добавить строчку про отлюкчение зумов в браузере

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Устанавливаем разрешение
        options.addArguments("--disable-notifications"); // Отключаем уведомления
        options.merge(capabilities);

        return new ChromeDriver(options);
    }
}
