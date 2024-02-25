package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddCustomerPage;
import pages.MainPage;
import tests.base.BaseCase;
import utils.Const;
import utils.Waiters;


/**
 * Тест кейс T1
 * Создание клиента (Customer)
 */
public class AddCustomerTest extends BaseCase {
    MainPage mainPage;
    AddCustomerPage addCustomerPage;

    @Test
    @Description("Создание клиента (Customer)")
    public void creatingCustomerTest() {
        mainPage = new MainPage(driver, webDriverWait);
        Waiters.waitVisibilityElement(mainPage.addCustomerButton, webDriverWait);
        mainPage.clickButtonAddCustomer();
        addCustomerPage = new AddCustomerPage(driver, webDriverWait);

        Waiters.waitVisibilityElement(addCustomerPage.createAccountButton, webDriverWait);
        boolean added = addCustomerPage.creatingCustomer(Const.lastName, driver, webDriverWait);
        Assert.assertTrue(added, "New customer not added");
    }


}