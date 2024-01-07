package parametrize.peopleModels;

import io.restassured.specification.Argument;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class People {

    private String name;
    private Integer age;
    private String sex;

    public People(String name, Integer age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public People() {
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

}
