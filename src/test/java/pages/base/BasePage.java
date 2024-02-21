package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Родительский класс для все страниц
 */
public class BasePage {

     WebDriver webDriver;
     WebDriverWait webDriverWait;

    public BasePage(WebDriver driver,WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        webDriver = driver;
        webDriverWait = wait;
    }

    /**
     * Нажатие на кнопку
     * @param button то на что надо нажать
     */
    public static void clickButton(WebElement button) {
        button.click();
    }

    /**
     * Ввод текста в текстовое поле
     * @param textElement куда вводить
     * @param value       что вводить
     */
    public static void setTextElementText(WebElement textElement, String value) {
        textElement.sendKeys(value);
    }
}
