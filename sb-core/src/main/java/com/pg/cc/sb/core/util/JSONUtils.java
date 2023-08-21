package com.pg.cc.sb.core.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lianzhengbj
 */
@Slf4j
public class JSONUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String writeValue(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
        return null;
    }

    public static <T> T readValue(String s, Class<T> clz) {
        try {
            return mapper.readValue(s, clz);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
        return null;
    }
}