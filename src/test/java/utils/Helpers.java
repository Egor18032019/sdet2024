package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Helpers {
    @Step("Проверка все ли элементы списка содержат {searchString}")
    public static boolean isContainsSearchString(String searchString, List<WebElement> listRows) {
        //если лист пустой, то тест не пройдет
        boolean cellHaveFirstName = false;
        for (WebElement element : listRows) {
            String row = element.getText();
            cellHaveFirstName = row.contains(searchString);
            if (!cellHaveFirstName) break;
        }
        return cellHaveFirstName;
    }

    public static boolean isWindows() {
        String os = System.getProperty("os.name").toLowerCase();
        return (os.contains("win"));

    }

    public static long giveMePostCode() {
        long min = 1000000000; // Минимальное число для диапазона
        long max = 9999999999L; // Максимальное число для диапазона

        return (long) (Math.random() * ++max) + min;
    }

}
