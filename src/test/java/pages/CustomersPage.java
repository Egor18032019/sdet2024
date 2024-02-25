package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.base.BasePage;
import utils.Waiters;

import java.util.Comparator;
import java.util.List;

/**
 * Страница Customers
 */
public class CustomersPage extends MainPage {

    public CustomersPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    /**
     * Таблица на вкладке Customers
     */
    @FindBy(xpath = "//table[@class='table table-bordered table-striped']")
    public WebElement table;
    /**
     * Строка таблицы из вкладки Customer
     */
    @FindBy(xpath = "//tr[@class='ng-scope']")
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
     * Получение все строк в таблице
     */
    @FindBys({@FindBy(xpath = "//tr[@class='ng-scope']")})
    public List<WebElement> rowsFromTableCustomer;

    @Step("Поиск клиента через строку поиска по {name}")
    public void searchCustomer(String name) {
        setTextElementText(searchCustomerInput, name);
    }

    @Step("Сортировка таблицы при нажатие на First name и получение списка клиентов с сайта")
    public List<WebElement> clickOnButtonForSortForFirstName(WebDriverWait webDriverWait) {
        clickButton(sortLinkFirsName);
        // Ждем именно когда появится строка. Таблица уже есть
        Waiters.waitVisibilityElement(row, webDriverWait);
        List<WebElement> listRowSortedAfterClickOnFirstName = rowsFromTableCustomer;
        return listRowSortedAfterClickOnFirstName;
    }

    @Step("Поиск клиентов содержащих в ФИО {searchString}")
    public List<WebElement> searchCustomer(String searchString, WebDriverWait waitD) {
        Waiters.waitVisibilityElement(searchCustomerInput, waitD);
        searchCustomer(searchString);
        return rowsFromTableCustomer;
    }

    @Step("получение отсортированного списка клиентов")
    public List<WebElement> sortCustomerFromPageOnList() {
        List<WebElement> listRowBeforeClickOnFirstName = rowsFromTableCustomer;
        int sizeList = listRowBeforeClickOnFirstName.size();
        if (sizeList == 0) {
            Assert.fail("Пустой список клиентов во вкладке Customers");
        }
        listRowBeforeClickOnFirstName.sort(
                Comparator.comparing(o -> o.getText().split(" ")[0]));
        return listRowBeforeClickOnFirstName;
    }

    @Step("Сортировка полученных данных {name}")
    public List<WebElement> checkListAfterFilterByName(String name) {
        List<WebElement> listAllNamesAfterDelete = rowsFromTableCustomer.stream()
                .filter(o -> o.getText().split(" ")[0].equals(name))
                .toList();

        return listAllNamesAfterDelete;
    }
    @Step("Нажали на кнопку Delete в строке где имя клиента ")
    public void clickOnDeleteButtonInRowCustomers(  WebElement webElement) {
        List<WebElement> deleteButton = webElement.findElements(By.tagName("button"));
        BasePage.clickButton(deleteButton.get(0));
    }
}
