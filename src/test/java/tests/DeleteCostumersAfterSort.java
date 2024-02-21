package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Customers;
import pages.MainPage;
import tests.base.BaseCase;
import utils.Waiters;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DeleteCostumersAfterSort extends BaseCase {

    MainPage mainPage;
    Customers customersPage;

    @Test
    @Issue("T3")
    @Description("Удаление клиента после сортировки")
    public void sortingCustomersByFirstNameTest() {
        mainPage = new MainPage(driver);
        Waiters.waitVisibilityElement(mainPage.customersButton, webDriverWait);
        mainPage.clickButtonCustomer();

        customersPage = new Customers(driver);
        Waiters.waitVisibilityElement(customersPage.table, webDriverWait);
        List<WebElement> listRowBeforeClickOnFirstName = customersPage.rowsFromTableCustomer;
        int sizeList = listRowBeforeClickOnFirstName.size();
        if (sizeList == 0) {
            Assert.fail("Пустой список клиентов после перехода на вкладку Customers");
        }
        //todo написать отдельную функцию на сортировку ? билдер ?

        List<String> sortNameCustomers =
                listRowBeforeClickOnFirstName.stream()
                        .map(o -> o.getText().split(" ")[0])
                        .sorted(Comparator.comparing(String::length))
                        .toList();
        String customerForDelete = giveMeAverageName(sortNameCustomers);
        customersPage.searchCustomer(customerForDelete);
        // нашли теперь удалить

    }

    protected String giveMeAverageName(List<String> sortNameCustomers) {
        int length = sortNameCustomers.size();

        return sortNameCustomers.get(length / 2);
    }
}
