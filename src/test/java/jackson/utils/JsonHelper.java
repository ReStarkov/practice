package jackson.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonHelper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static  <T> T fromJsonFileToClass(String path, Class <T> out) throws IOException {
       return mapper.readValue(new File(path), out);
    }

    public static JsonNode fromJsonFileToJsonNode(String path) throws IOException {
        return mapper.readValue(new File(path), JsonNode.class);
    }

    public static String toStringFromObject(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static <T> T fromStringToClass(String objectAsString, Class <T> out) throws JsonProcessingException {
        return mapper.readValue(objectAsString, out);
    }

    public static JsonNode fromStringToJsonNode(String objectAsString) throws JsonProcessingException {
        return mapper.readValue(objectAsString, JsonNode.class);
    }
}
