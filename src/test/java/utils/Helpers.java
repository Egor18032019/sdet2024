package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

import java.util.Comparator;
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

    @Step("Генерация Post code")
    public static long giveMePostCode() {
        long min = 1000000000; // Минимальное число для диапазона
        long max = 9999999999L; // Максимальное число для диапазона
        long generatedLong = max + (long) (Math.random() * (min - max));
        return generatedLong;
    }

    @Step("Генерация First name из Post code")
    public static String giveMeFirstName(long number) {
        String line = String.valueOf(number);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < line.length(); i = i + 2) {
            int foo = Integer.parseInt(line.substring(i, i + 2));
            String bar = giveMeLetterFromNumber(foo);
            str.append(bar);
        }

        return str.toString();
    }

    protected static String giveMeLetterFromNumber(int digit) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int rightIndex = digit % 26;
        String answer = String.valueOf(alphabet.charAt(rightIndex));
        return answer;
    }

    @Step("Получение имени пользователя близкое к среднеарифметическому.")
    public static String giveMeAverageName(List<WebElement> listAllNames) {

        List<String> sortNameCustomers =
                listAllNames.stream()
                        .map(o -> o.getText().split(" ")[0])
                        .sorted(Comparator.comparing(String::length))
                        .toList();
        int length = sortNameCustomers.size();
        return sortNameCustomers.get(length / 2);
    }

}
