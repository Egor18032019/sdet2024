package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.base.BasePage;

/**
 * Главная страница
 */
public class MainPage extends BasePage {

    public MainPage(WebDriver driver , WebDriverWait webDriverWait) {
        super(driver,webDriverWait);
    }

    /**
     * Кнопка <Add Customer>
     */
    @FindBy(xpath = "//button[@ng-class='btnClass1']")
    public WebElement addCustomerButton;
    /**
     * Кнопка <Open Account>
     */
    @FindBy(xpath = "//button[@ng-class='btnClass2']")
    public WebElement openAccountButton;
    /**
     * Кнопка <Customers>
     */
    @FindBy(xpath = "//button[@ng-class='btnClass3']")
    public WebElement customersButton;

    @Step("Нажатие на кнопку Add Customer для перехода на вкладку создание клиентов")
    public void clickButtonAddCustomer() {
        clickButton(addCustomerButton);
    }

    @Step("Нажатие на кнопку Open Customer для перехода на вкладку поиска клиентов")
    public void clickButtonOpenAccount() {
        clickButton(openAccountButton);
    }

    @Step("Нажатие на кнопку Customer для перехода на вкладку поиска клиентов")
    public void clickButtonCustomer() {
        clickButton(customersButton);
    }
}