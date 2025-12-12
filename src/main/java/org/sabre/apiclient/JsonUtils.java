package org.sabre.apiclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Parse JSON string to Map
    public static Map<String, Object> toMap(String json) throws IOException {
        return objectMapper.readValue(json, Map.class);
    }

    // Parse JSON string to List
    public static List<Object> toList(String json) throws IOException {
        return objectMapper.readValue(json, List.class);
    }

    // Parse JSON string to JsonNode
    public static JsonNode toJsonNode(String json) throws JsonProcessingException {
        return objectMapper.readTree(json);
    }

    // Extract value by simple JSON path (dot notation, e.g., "user.address.city")
    public static String getValueByPath(String json, String path) throws JsonProcessingException {
        JsonNode node = toJsonNode(json);
        String[] parts = path.split("\\.");
        for (String part : parts) {
            if (node == null) return null;
            node = node.get(part);
        }
        return node != null ? node.asText() : null;
    }

    // Convert JSON to POJO
    public static <T> T toObject(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }
}

