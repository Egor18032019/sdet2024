package tests.base;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.Const;
import utils.Helpers;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Collections;


/**
 * Родительский класс в котором прописано открытие браузера и закрытие браузера
 * + инициализация WebDriver и WebDriverWait
 */
public class BaseCase {
    public WebDriver driver;
    public WebDriverWait webDriverWait;


    static {
        System.out.println("Test start time:" + LocalTime.now());
    }

    @BeforeMethod
    @Step("Открытие браузера и переход на страницу")
    public void openURL() {
        long id = Thread.currentThread().getId();
        System.out.println("Before test-class. Thread id is:  " + id);


        System.setProperty("webdriver.http.factory", "jdk-http-client");
        if (Helpers.isWindows()) {
            System.setProperty("webdriver.chrome.driver", Const.pathWindowDriver);
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("start-maximized");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(3));
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(1));
        driver.navigate().to(Const.urlMain);
    }

    @AfterMethod
    @Step("Чистка кэша и закрытие браузера.")
    public void closeBrowser() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.sessionStorage.clear()");
        driver.manage().deleteAllCookies();
        driver.quit();

        System.out.println( "Test end time:" + LocalTime.now());

        long id = Thread.currentThread().getId();
        System.out.println("After test-class. Thread id is: "+ id);
    }
    }

