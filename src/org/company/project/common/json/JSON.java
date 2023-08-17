package org.company.project.common.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON {
    private JSON(){}
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static String getJson (Object o) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(o);
    }
}
