package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Waiters;

import java.util.List;

/**
 * Страница Customers
 */
public class Customers extends MainPage {


    public Customers(WebDriver driver) {
        super(driver);
    }

    /**
     * Таблица на вкладке Customers
     */
    @FindBy(xpath = "//table[@class='table table-bordered table-striped']")
    public WebElement table;
    /**
     * Строка таблицы из вкладки Customer
     */
    @FindBy(css = ".ng-binding")
    public WebElement row;
    /**
     * Поле для текстового вода используемая для поиска клиента
     */
    @FindBy(xpath = "//input[@placeholder='Search Customer']")
    public WebElement searchCustomerInput;
    /**
     * Ячейка в таблице клиентов надписью "First Name"
     */
    @FindBy(linkText = "First Name")
    public WebElement sortLinkFirsName;
    /**
     * получение все строк в таблице
     */
    @FindBys({@FindBy(xpath = "//tr[@class='ng-scope']")})
    public List<WebElement> rowsFromTableCustomer;

    @Step("Поиск клиента {name}")
    public void searchCustomer(String name) {
        setTextElementText(searchCustomerInput, name);
    }

    @Step("Сортировка таблицы при нажатие на First name")
    public void sortForFirstName() {
        clickButton(sortLinkFirsName);
    }

    @Step("Поиск клиентов содержащих в ФИО {searchString}")
    public List<WebElement> searchCustomer(String searchString, WebDriverWait wait) {
        Waiters.waitVisibilityElement(searchCustomerInput, wait);
        searchCustomer(searchString);
        return rowsFromTableCustomer;
    }

}
