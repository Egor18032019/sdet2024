package pages.base;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
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

    public BasePage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        webDriver = driver;
        webDriverWait = wait;
    }

    /**
     * Нажатие на кнопку
     *
     * @param button то на что надо нажать
     */
    public static void clickButton(WebElement button) {
        button.click();
    }

    /**
     * Ввод текста в текстовое поле
     *
     * @param textElement куда вводить
     * @param value       что вводить
     */
    @Step("Ввод данных {value}")
    public static void setTextElementText(WebElement textElement, String value) {
        textElement.sendKeys(value);
    }

    public static void scrollWithJavaScript(WebElement element, WebDriver webDriver) {
        String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
        ((JavascriptExecutor) webDriver).executeScript(mouseOverScript, element);
    }
}
