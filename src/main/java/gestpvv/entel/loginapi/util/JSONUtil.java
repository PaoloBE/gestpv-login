package gestpvv.entel.loginapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONUtil {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(JSONUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static final String ERROR_PARSER = "error al parsear trama: ";

    private JSONUtil() {
    }

    public static String toJSON(Object object) throws Exception {
        try {
            return convertToJSONString(object);
        } catch (JsonProcessingException var2) {
            throw new Exception(var2);
        }
    }

    public static <T> T fromJson(String message, Class<T> clazz) {
        ObjectMapper objectMapper = getObjectMapper();

        try {
            return objectMapper.readValue(message, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String convertToJSONString(Object object) throws JsonProcessingException {
        ObjectMapper mapper = getObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    public static <T> T deserialize(String message, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = getObjectMapper();
        return objectMapper.readValue(message, clazz);
    }

    public static JsonNode getNodeReference(String nombre, JsonNode rootNode) {
        List<JsonNode> padres = rootNode.findParents(nombre);
        if (padres != null && !padres.isEmpty()) {
            Iterator<JsonNode> iterator = padres.iterator();

            while(iterator.hasNext()) {
                JsonNode parent = (JsonNode)iterator.next();
                JsonNode campo = parent.get(nombre);
                if (campo != null && !(campo instanceof MissingNode) && !(campo instanceof NullNode)) {
                    return campo;
                }
            }
        }

        return null;
    }

    public static String getCampo(String nombre, JsonNode rootNode) {
        List<JsonNode> padres = rootNode.findParents(nombre);
        if (padres != null && !padres.isEmpty()) {
            Iterator<JsonNode> iterator = padres.iterator();

            while(iterator.hasNext()) {
                JsonNode parent = (JsonNode)iterator.next();
                JsonNode campo = parent.get(nombre);
                if (campo != null && campo.isTextual()) {
                    return campo.textValue();
                }
            }
        }

        return "";
    }

    public static ObjectNode createObject() {
        return objectMapper.createObjectNode();
    }

    public static JsonNode getRootNode(String trama) {
        try {
            return objectMapper.readTree(trama);
        } catch (IOException var2) {
            log.error("error al parsear trama: " + var2.getMessage(), var2);
            return null;
        }
    }

    public static String getCampoTrama(String nombre, String trama) {
        try {
            JsonNode rootNode = objectMapper.readTree(trama);
            return getCampo(nombre, rootNode);
        } catch (IOException var4) {
            log.error("error al parsear trama: " + var4.getMessage(), var4);
            return "";
        }
    }
}
