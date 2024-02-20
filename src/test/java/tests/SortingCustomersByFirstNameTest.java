package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.Customers;
import pages.MainPage;

import utils.Waiters;

import java.util.Comparator;
import java.util.List;


/**
 * Тест кейс T2
 * Сортировка клиентов по имени (First Name)
 */

 public class SortingCustomersByFirstNameTest extends BaseCase {
    MainPage mainPage;
    Customers customersPage;

    @Test
    @Issue("T2")
    @Description("Сортировка клиентов по имени (First Name)")
     public void sortingCustomersByFirstNameTest() {
        mainPage = new MainPage(driver);
        Waiters.waitVisibilityElement(mainPage.customersButton, webDriverWait);
        mainPage.clickButtonCustomer();
        customersPage = new Customers(driver);
        Waiters.waitVisibilityElement(customersPage.customersButton, webDriverWait);
        customersPage = new Customers(driver);
        Waiters.waitVisibilityElement(customersPage.table, webDriverWait);
        List<WebElement> listRowBeforeClickOnFirstName = customersPage.rowsFromTableCustomer;
        int sizeList = listRowBeforeClickOnFirstName.size();
        if (sizeList == 0) {
           System.out.println( "Пустой список клиентов после перехода на вкладку Customers");
        }
        listRowBeforeClickOnFirstName.sort(Comparator.comparing(o -> o.getText().split(" ")[0]));
        customersPage.sortForFirstName();
        // Ждем именно когда появится строка. Таблица уже есть
        Waiters.waitVisibilityElement(customersPage.row, webDriverWait);
        List<WebElement> listRowSortedAfterClickOnFirstName = customersPage.rowsFromTableCustomer;

        boolean isSorted = listRowSortedAfterClickOnFirstName.equals(listRowBeforeClickOnFirstName);
       System.out.println(isSorted +"Сортировка списка клиентов не соответствует ожиданием ");
    }
}
