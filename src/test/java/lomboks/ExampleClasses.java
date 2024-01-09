package lomboks;

import lomboks.models.People;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExampleClasses {

    @Test
    public void exampleCreateLombokClasses(){
        //создание пустого экземляра класса и установка параметров через сетеры
        People emptyHuman = new People();
        emptyHuman.setName("Bob");
        emptyHuman.setAge(22);
        emptyHuman.setSurname("Johns");
        emptyHuman.setSex("male");

        //создание пустого экземляра класса и установка параметров билдер
        People builderHuman = People.builder()
                .name("Bob")
                .surname("Johns")
                .age(22)
                .sex("male")
                .build();

        //создание через конструктор
        People constructorHuman = new People("Bob", "Johns", 22, "male");

        Assertions.assertEquals(emptyHuman,builderHuman);
        Assertions.assertEquals(constructorHuman, builderHuman);
        Assertions.assertEquals(constructorHuman, emptyHuman);
    }
}
