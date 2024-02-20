package tests;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.AddCustomer;
import pages.MainPage;
import utils.Const;
import utils.Waiters;

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
        mainPage = new MainPage(driver);
        Waiters.waitVisibilityElement(mainPage.addCustomerButton, webDriverWait);
        mainPage.clickButtonAddCustomer();
        addCustomer = new AddCustomer(driver);
        System.out.println(giveMePostCode());
        long postalCode = giveMePostCode();

        Waiters.waitVisibilityElement(addCustomer.createAccountButton, webDriverWait);

        addCustomer.creatingCustomer(Const.firstName, Const.lastName, Const.postalCode, driver, webDriverWait);
    }
}