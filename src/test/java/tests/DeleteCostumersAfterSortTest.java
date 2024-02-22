package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Customers;
import pages.MainPage;
import pages.base.BasePage;
import tests.base.BaseCase;
import utils.Waiters;

import java.util.Comparator;
import java.util.List;

import static pages.base.BasePage.scrollWithJavaScript;

public class DeleteCostumersAfterSortTest extends BaseCase {

    MainPage mainPage;
    Customers customersPage;

    @Test
    @Issue("T3")
    @Description("Удаление клиента после поиска  ")
    public void deleteCustomersByFirstNameAfterSearch() {
        mainPage = new MainPage(driver, webDriverWait);
        Waiters.waitVisibilityElement(mainPage.customersButton, webDriverWait);
        mainPage.clickButtonCustomer();

        customersPage = new Customers(driver, webDriverWait);
        Waiters.waitVisibilityElement(customersPage.table, webDriverWait);
        List<WebElement> listAllNames = customersPage.rowsFromTableCustomer;

        if (listAllNames.isEmpty()) {
            Assert.fail("Пустой список клиентов после перехода на вкладку Customers");
        }

        List<String> sortNameCustomers =
                listAllNames.stream()
                        .map(o -> o.getText().split(" ")[0])
                        .sorted(Comparator.comparing(String::length))
                        .toList();
        String customerForDelete = giveMeAverageName(sortNameCustomers);

        customersPage.searchCustomer(customerForDelete);

        List<WebElement> listAllNamesAfterSearchAndAfterFilter = checkListAfterFilterByName(customerForDelete);

        boolean empty = listAllNamesAfterSearchAndAfterFilter.isEmpty();
        Assert.assertFalse(empty, "После поиска ничего не нашли");

        WebElement webElement = listAllNamesAfterSearchAndAfterFilter.get(0);
        List<WebElement> deleteButton = webElement.findElements(By.tagName("button"));
        BasePage.clickButton(deleteButton.get(0));


        boolean delete = checkListAfterFilterByName(customerForDelete).isEmpty();
        Assert.assertTrue(delete, "Удаление не прошло.");
    }


    @Test
    @Issue("T3.1")
    @Description("Удаление клиента в ручную  ")
    public void sortingCustomersByFirstNameTest() {
        mainPage = new MainPage(driver, webDriverWait);
        Waiters.waitVisibilityElement(mainPage.customersButton, webDriverWait);
        mainPage.clickButtonCustomer();

        customersPage = new Customers(driver, webDriverWait);
        Waiters.waitVisibilityElement(customersPage.table, webDriverWait);
        List<WebElement> listAllNames = customersPage.rowsFromTableCustomer;

        if (listAllNames.isEmpty()) {
            Assert.fail("Пустой список клиентов после перехода на вкладку Customers");
        }

        List<String> sortNameCustomers =
                listAllNames.stream()
                        .map(o -> o.getText().split(" ")[0])
                        .sorted(Comparator.comparing(String::length))
                        .toList();
        String customerNameForDelete = giveMeAverageName(sortNameCustomers);

//получили
        WebElement elementForDelete = checkListAfterFilterByName(customerNameForDelete).get(0);
        //прокрутили
        scrollWithJavaScript(elementForDelete, driver);
        //нашли кнопку
        List<WebElement> deleteButton = elementForDelete.findElements(By.tagName("button"));
        // удалили
        BasePage.clickButton(deleteButton.get(0));
        boolean delete = checkListAfterFilterByName(customerNameForDelete).isEmpty();
        Assert.assertTrue(delete, "Удаление не прошло.");
    }

    @Step("Получение имени пользователя близкое к среднеарифметическому.")
    protected String giveMeAverageName(List<String> sortNameCustomers) {
        int length = sortNameCustomers.size();

        return sortNameCustomers.get(length / 2);
    }

    @Step("Сортировка полученных данных {name}")
    private List<WebElement> checkListAfterFilterByName(String name) {
        List<WebElement> listAllNamesAfterDelete = customersPage.rowsFromTableCustomer.stream()
                .filter(o -> o.getText().split(" ")[0].equals(name))
                .toList();

        return listAllNamesAfterDelete;
    }
}
