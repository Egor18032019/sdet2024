package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Родительский класс для все страниц
 */
public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Нажатие на кнопку
     * @param button то на что надо нажать
     */
    protected static void clickButton(WebElement button) {
        button.click();
    }

    /**
     * Ввод текста в текстовое поле
     * @param textElement куда вводить
     * @param value       что вводить
     */
    protected static void setTextElementText(WebElement textElement, String value) {
        textElement.sendKeys(value);
    }
}
