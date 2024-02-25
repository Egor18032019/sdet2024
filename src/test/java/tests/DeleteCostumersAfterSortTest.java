package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CustomersPage;
import pages.MainPage;
import tests.base.BaseCase;
import utils.Helpers;
import utils.Waiters;

import java.util.List;

import static pages.base.BasePage.scrollWithJavaScript;

public class DeleteCostumersAfterSortTest extends BaseCase {

    MainPage mainPage;
    CustomersPage customersPage;

    @Test
    @Issue("T3")
    @Description("Удаление клиента после поиска через строку поиска ")
    public void deleteCustomersByFirstNameAfterSearch() {
        mainPage = new MainPage(driver, webDriverWait);
        Waiters.waitVisibilityElement(mainPage.customersButton, webDriverWait);
        mainPage.clickButtonCustomer();

        customersPage = new CustomersPage(driver, webDriverWait);
        Waiters.waitVisibilityElement(customersPage.table, webDriverWait);
        List<WebElement> listAllNames = customersPage.rowsFromTableCustomer;
        Assert.assertFalse(listAllNames.isEmpty(), "Пустой список клиентов после перехода на вкладку Customers");
        String customerNameForDelete = Helpers.giveMeAverageName(listAllNames);

        customersPage.searchCustomer(customerNameForDelete);
        // todo а если после поиска больше чем один ?
        List<WebElement> listAllNamesAfterSearchAndAfterFilter = customersPage.checkListAfterFilterByName(customerNameForDelete);
        boolean empty = listAllNamesAfterSearchAndAfterFilter.isEmpty();
        Assert.assertFalse(empty, "После поиска ничего не нашли");
        WebElement webElement = listAllNamesAfterSearchAndAfterFilter.get(0);

        customersPage.clickOnDeleteButtonInRowCustomers(webElement);
        boolean delete = customersPage.checkListAfterFilterByName(customerNameForDelete).isEmpty();
        Assert.assertTrue(delete, "Удаление не прошло.");
    }


    @Test
    @Issue("T3.1")
    @Description("Удаление клиента в ручную  ")
    public void sortingCustomersByFirstNameTest() {
        mainPage = new MainPage(driver, webDriverWait);
        Waiters.waitVisibilityElement(mainPage.customersButton, webDriverWait);
        mainPage.clickButtonCustomer();

        customersPage = new CustomersPage(driver, webDriverWait);
        Waiters.waitVisibilityElement(customersPage.table, webDriverWait);
        List<WebElement> listAllNames = customersPage.rowsFromTableCustomer;
        Assert.assertFalse(listAllNames.isEmpty(), "Пустой список клиентов после перехода на вкладку Customers");
        String customerNameForDelete = Helpers.giveMeAverageName(listAllNames);
        //получили
        WebElement elementForDelete = customersPage.checkListAfterFilterByName(customerNameForDelete).get(0);
        //прокрутили
        scrollWithJavaScript(elementForDelete, driver);
        customersPage.clickOnDeleteButtonInRowCustomers(elementForDelete);
        boolean delete = customersPage.checkListAfterFilterByName(customerNameForDelete).isEmpty();
        Assert.assertTrue(delete, "Удаление не прошло.");
    }


}
