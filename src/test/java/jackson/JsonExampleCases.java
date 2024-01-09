package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import jackson.models.Cat;
import jackson.utils.JsonHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JsonExampleCases {

    @DisplayName("Преобразование объекта класса к строке")
    @Test
    public void fromObjectToString() throws JsonProcessingException {
        Cat cat = new Cat("Barsik", "Egyptian");
        String result = JsonHelper.toStringFromObject(cat);
        Assertions.assertTrue(result.contains("Barsik"));
    }

    @DisplayName("Преобразование строки к объекту класса")
    @Test
    public void fromStringToClass() throws JsonProcessingException {
        String catAsString = "{\"name\":\"Barsik\",\"breed\":\"Egyptian\"}";
        Cat cat = JsonHelper.fromStringToClass(catAsString, Cat.class);
        Assertions.assertEquals("Barsik", cat.getName());
    }

    @DisplayName("Преобразование файла json к классу")
    @Test
    public void fromSJsonFileToClass() throws IOException {
        Cat cat = JsonHelper.fromJsonFileToClass("src/test/resources/cat.json", Cat.class);
        Assertions.assertEquals("Barsik", cat.getName());
    }

    @DisplayName("Преобразование файла json к объекту JsonNode")
    @Test
    public void fromSJsonFileToObject() throws IOException {
        JsonNode result = JsonHelper.fromJsonFileToJsonNode("src/test/resources/cat.json");
        Assertions.assertTrue(result.has("name"));
    }

    @DisplayName("Преобразование строки к объекту JsonNode")
    @Test
    public void fromStringToObject() throws IOException {
        JsonNode result = JsonHelper.fromStringToJsonNode("{\"name\":\"Barsik\",\"breed\":\"Egyptian\"}");
        Assertions.assertTrue(result.has("name"));
    }
}
