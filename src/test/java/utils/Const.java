package utils;

public class Const {
    /**
     * Основная страница
     */
    public static final String urlMain = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager";
    /**
     * путь к chromedriver
     */
    public static final String pathWindowDriver = "src/test/resources/chromedriver.exe";
    public static final String pathLinuxDriver = "src/test/resources/chromedriver";

    public static final String firstName = "First";
    public static final String lastName = "Last";
    public static final String postalCode = "620024";
    /**
     * ожидаемый текст алерта после создание нового клиента
     */
    public static final String expectedTextAfterCreatNewCustomer = "Customer added successfully with customer id";
}
