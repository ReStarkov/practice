package parametrize;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import parametrize.models.People;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodSourceTests {

    public static Stream<Arguments> generatePeople(){
        return Stream.of(
                Arguments.of(new People("stas", 12, "male")),
                Arguments.of(new People("vlad", 12, "male")));
    }

    @DisplayName("Проверка возраста нескольких экземляров тестовых данных при помощи создания объектов класса")
    @ParameterizedTest
    @MethodSource("generatePeople")
    public void checkAgeSuccess(People people) {
        assertEquals(12, people.getAge());
    }
}
