package parametrize;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class ValueSourceTets {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    public void checkNumbers(int number){
        List<Integer> numbersList = List.of(1,2,3,4,5);
        Assertions.assertTrue(numbersList.contains(number));
    }
}
