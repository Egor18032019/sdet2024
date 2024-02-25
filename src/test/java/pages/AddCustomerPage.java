package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Const;
import utils.Waiters;

import static utils.Helpers.giveMeFirstName;
import static utils.Helpers.giveMePostCode;

public class AddCustomerPage extends MainPage {

    public AddCustomerPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    /**
     * Кнопка <Add Customer> расположенная после(ниже) полей для текстового вода
     */
    @FindBy(xpath = "//button[@class='btn btn-default']")
    public WebElement createAccountButton;
    /**
     * Поле для текстового вода First name
     */
    @FindBy(xpath = "//input[@ng-model='fName']")
    public WebElement firstNameInput;
    /**
     * Поле для текстового вода Last name
     */
    @FindBy(xpath = "//input[@placeholder='Last Name']")
    public WebElement lastNameInput;
    /**
     * Поле для текстового вода Post Code
     */
    @FindBy(xpath = "//input[@placeholder='Post Code']")
    public WebElement postalCodeInput;

    @Step("Получение текста alerta(модального окна)")
    public String giveMeAlertText(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    @Step("Создание нового клиента +  accept модального окна + проверка на создание")
    public boolean creatingCustomer(String last, WebDriver driver, WebDriverWait wait) {
        long postalCode = giveMePostCode();
        String name = giveMeFirstName(postalCode);
        Waiters.waitVisibilityElement(this.firstNameInput, wait);
        Waiters.waitVisibilityElement(this.lastNameInput, wait);
        Waiters.waitVisibilityElement(this.postalCodeInput, wait);
        createNewCustomer(name, last, String.valueOf(postalCode));
        Waiters.WaitingModalWindow(wait);
        Alert alert = driver.switchTo().alert();
        String textOnAlert = alert.getText();
        boolean added = textOnAlert.startsWith(Const.expectedTextAfterCreateNewCustomer);
        alert.accept();
        return added;
    }

    @Step("Создание клиента")
    public void createNewCustomer(String firstName, String lastName, String postalCode) {
        fillFirstName(firstName)
                .fillLastName(lastName)
                .fillPostCode(postalCode)
                .createAccount(this.createAccountButton);
    }

    @Step(" 1. Заполнить поле First Name значением {firstName}")
    public AddCustomerPage fillFirstName(String firstName) {
        setTextElementText(firstNameInput, firstName);
        return this;
    }

    @Step(" 2. Заполнить поле Last Name значением {lastName}")
    public AddCustomerPage fillLastName(String lastName) {
        setTextElementText(lastNameInput, lastName);
        return this;
    }

    @Step(" 3. Заполнить поле Post Code значением {postalCode}")
    public AddCustomerPage fillPostCode(String postalCode) {
        setTextElementText(postalCodeInput, postalCode);
        return this;
    }

    @Step("Нажали на кнопку создание клиента.")
    public void createAccount(WebElement createAccountButton) {
        clickButton(createAccountButton);
    }
}

