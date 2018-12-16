package com.thales.kafka.producer.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public final class JsonUtil {

    private JsonUtil(){}

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
    }

    public static String toJson(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("Error write json.", e);
            return null;
        }
    }

    public static <T> T toObject(String json, Class<T> objectType) {
        try {
            return MAPPER.readValue(json, objectType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("Error parsing json.", e);
            return null;
        }
    }

    public static <T> T toObject(Object json, Class<T> objectType) {
        if (json == null) {
            return null;
        }
        return toObject(json.toString(), objectType);
    }
}
