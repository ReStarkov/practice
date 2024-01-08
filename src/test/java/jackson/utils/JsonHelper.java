package jackson.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonHelper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static  <T> T fromJsonFile(String path, Class <T> out) throws IOException {
       return mapper.readValue(new File(path), out);
    }

    public static String toStringFromObject(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    //для преобразования в определенный класс
    public static <T> T fromStringToClass(String objectAsString, Class <T> out) throws JsonProcessingException {
        return mapper.readValue(objectAsString, out);
    }

    //для преобразования в обект
    public static Object fromStringToObject(String objectAsString, Object object) throws JsonProcessingException {
        return mapper.readValue(objectAsString, (JavaType) object);
    }
}
