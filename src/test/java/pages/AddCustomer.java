package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Const;
import utils.Waiters;

public class AddCustomer extends MainPage {

    public AddCustomer(WebDriver driver,WebDriverWait webDriverWait) {
        super(driver,webDriverWait);
    }

    /**
     * Кнопка <Add Customer> расположенная после(ниже) полей для текстового вода
     */
    @FindBy(xpath = "//button[contains(text(),'Add Customer')][@class='btn btn-default']")
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

    @Step("Создание клиента")
    public void creatNewCustomer(String firstName, String lastName, String postalCode) {
        setTextElementText(firstNameInput, firstName);
        setTextElementText(lastNameInput, lastName);
        setTextElementText(postalCodeInput, postalCode);
        clickButton(createAccountButton);
    }

    @Step("Получение текста alerta(модального окна)")
    public String giveMeAlertText(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    @Step("Создание нового клиента +  accept модального окна + проверка на создание")
    public boolean creatingCustomer(String name, String last, String postalCode, WebDriver driver, WebDriverWait wait) {
        Waiters.waitVisibilityElement(firstNameInput, wait);
        Waiters.waitVisibilityElement(lastNameInput, wait);
        Waiters.waitVisibilityElement(postalCodeInput, wait);
        creatNewCustomer(name, last, postalCode);
        Waiters.WaitingModalWindow(wait);
        Alert alert = driver.switchTo().alert();
        String textOnAlert = alert.getText();
        boolean added = textOnAlert.startsWith(Const.expectedTextAfterCreateNewCustomer);
        alert.accept();
        return added;
    }

}
