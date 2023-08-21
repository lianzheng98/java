package com.pg.cc.sb.web.freq.utils;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @author lianzheng
 */
public class CollectionUtilsEx {
    public static <T, R> List<R> mapToList(Collection<T> collection, Function<T, R> mapper) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }
        return collection.stream().map(mapper).collect(java.util.stream.Collectors.toList());
    }
}