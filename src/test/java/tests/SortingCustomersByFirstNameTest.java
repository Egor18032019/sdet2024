package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CustomersPage;
import pages.MainPage;

import tests.base.BaseCase;
import utils.Waiters;

import java.util.List;


/**
 * Тест кейс T2
 * Сортировка клиентов по имени (First Name)
 */
public class SortingCustomersByFirstNameTest extends BaseCase {
    MainPage mainPage;
    CustomersPage customersPage;

    @Test
    @Issue("T2")
    @Description("Сортировка клиентов по имени (First Name)")
    public void sortingCustomersByFirstNameTest() {
        mainPage = new MainPage(driver, webDriverWait);
        Waiters.waitVisibilityElement(mainPage.customersButton, webDriverWait);
        mainPage.clickButtonCustomer();

        customersPage = new CustomersPage(driver, webDriverWait);
        Waiters.waitVisibilityElement(customersPage.table, webDriverWait);

        List<WebElement> listRowBeforeClickOnFirstName = customersPage.sortCustomerFromPageOnList();
        var listRowSortedAfterClickOnFirstName = customersPage.clickOnButtonForSortForFirstName(webDriverWait);

        boolean isSorted = listRowSortedAfterClickOnFirstName.equals(listRowBeforeClickOnFirstName);
        Assert.assertTrue(isSorted, "Сортировка списка клиентов не соответствует ожиданием ");
        //сортирует в обратном порядке
    }
}
