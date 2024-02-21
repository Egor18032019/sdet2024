package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddCustomer;
import pages.MainPage;
import tests.base.BaseCase;
import utils.Const;
import utils.Waiters;

import static utils.Helpers.giveMeFirstName;
import static utils.Helpers.giveMePostCode;


/**
 * Тест кейс T1
 * Создание клиента (Customer)
 */
public class AddCustomerTest extends BaseCase {
    MainPage mainPage;
    AddCustomer addCustomer;

    @Test
    @Description("Создание клиента (Customer)")
    public void creatingCustomerTest() {
        mainPage = new MainPage(driver,webDriverWait);

        Waiters.waitVisibilityElement(mainPage.addCustomerButton, webDriverWait);
        mainPage.clickButtonAddCustomer();
        addCustomer = new AddCustomer(driver,webDriverWait);
        long postalCode = giveMePostCode();
        String firstName = giveMeFirstName(postalCode);
        String postalCodeForString = String.valueOf(postalCode);
        Waiters.waitVisibilityElement(addCustomer.createAccountButton, webDriverWait);
        boolean added = addCustomer.creatingCustomer(firstName, Const.lastName, postalCodeForString, driver, webDriverWait);
        Assert.assertTrue(added, "New customer not added");
    }
}