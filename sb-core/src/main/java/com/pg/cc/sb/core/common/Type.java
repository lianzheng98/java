package com.pg.cc.sb.core.common;

import com.pg.cc.sb.core.util.JSONUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum Type {
    INT(1, Integer.class),
    DOUBLE(2, Double.class),
    FlOAT(3, Float.class),
    BOOL(4, Boolean.class),
    CHAR(5, Character.class),
    STRING(6, String.class),
    LIST(7, List.class),
    MAP(8, Map.class);

    int val;

    Class cls;

    public static <T> T trans(Object obj, Class<T> clz) {
        return JSONUtils.readValue(JSONUtils.writeValue(obj), clz);
    }
}
