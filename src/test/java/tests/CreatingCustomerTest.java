package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.AddCustomer;
import pages.MainPage;
import tests.BaseCase;
import utils.Const;
import utils.Waiters;


/**
 * Тест кейс T1
 * Создание клиента (Customer)
 */


public class CreatingCustomerTest extends BaseCase {
    MainPage mainPage;
    AddCustomer addCustomer;

    @Test
    @Description("Создание клиента (Customer)")
     public void creatingCustomerTest() {
        mainPage = new MainPage(driver);
        Waiters.waitVisibilityElement(mainPage.addCustomerButton, webDriverWait);
        mainPage.clickButtonAddCustomer();
        addCustomer = new AddCustomer(driver);
        Waiters.waitVisibilityElement(addCustomer.createAccountButton, webDriverWait);
        addCustomer.creatingCustomer(Const.firstName, Const.lastName, Const.postalCode, driver, webDriverWait);
    }
}