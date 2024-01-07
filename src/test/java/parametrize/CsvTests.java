package parametrize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class CsvTests {

    @DisplayName("Проверка возраста нескольких экземляров тестовых данных при помощи CsvSource. Успешно")
    @ParameterizedTest
    @CsvSource({"Bob, 12, male", "Mike, 12, male"})
    public void checkAgeSuccess(String name, String age, String sex) {
        assertEquals("12", age);
    }

    @DisplayName("Проверка возраста нескольких экземляров тестовых данных при помощи CsvSource. Ожидается ошибка")
    @ParameterizedTest
    @CsvSource({"Bob, 12, male", "Mike, 13, male"})
    public void checkAgeFailed(String name, String age, String sex) {
        assertEquals("12", age);
    }

    @DisplayName("Проверка возраста нескольких экземляров тестовых данных при помощи файла csv.")
    @ParameterizedTest
    @CsvFileSource(resources = "/people.csv", delimiter = ',')
    public void checkAgeFromFile(String name, String age, String sex) {
        assertEquals("12", age);
    }
}
