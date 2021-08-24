/**
 * @(#)RegistryImpl.java, 8月 24, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.pg633.registry;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lianzhengbj
 */
@Slf4j
public class FileRegistryImpl implements Registry {

    private String file;

    private ConcurrentHashMap<String, List<Info>> map;

    public FileRegistryImpl(String file) {
        this.file = file;
        map = JSONObject.parseObject(read(), new TypeReference<ConcurrentHashMap<String, List<Info>>>() {

        });
        if (ObjectUtils.isEmpty(map)) {
            map = new ConcurrentHashMap<>();
        }
    }

    @Override
    public void register(String service, Info info) {
        if (map.containsKey(service)) {
            if (map.get(service).stream().anyMatch(info::equals)) {
                return;
            }
            map.get(service).add(info);
        } else {
            map.put(service, new LinkedList<>(Collections.singletonList(info)));
        }
        write();
    }

    private String read() {
        FileReader fileReader = new FileReader(file);
        return fileReader.readString();
    }

    private void write() {
        FileWriter writer = new FileWriter(file);
        writer.write(JSONObject.toJSONString(map));
    }

    @Override
    public void deregister(String service, Info info) {
        map.getOrDefault(service, Collections.emptyList()).remove(info);
        return;
    }

    @Override
    public List<Info> getServiceInfos(String service) {
        return map.getOrDefault(service, Collections.emptyList());
    }

    @Override
    public void clear(String service) {
        map.clear();
        write();
    }
}