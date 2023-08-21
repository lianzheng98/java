package com.pg.cc.sb.web.freq.service;


import com.pg.cc.sb.web.freq.data.FrequencyControlDTO;
import com.pg.cc.sb.web.freq.data.FrequencyControlException;
import com.pg.cc.sb.web.freq.factory.FrequencyControlStrategyFactory;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
public abstract class AbstractFrequencyControlService<K extends FrequencyControlDTO> {

    @PostConstruct
    protected void registerMyselfToFactory() {
        FrequencyControlStrategyFactory.registerFrequencyController(getStrategyName(), this);
    }

    private <T> T executeWithFrequencyControlMap(Map<String, K> frequencyControlMap, SupplierThrowWithoutParam<T> supplier) throws Throwable {
        if (reachRateLimit(frequencyControlMap)) {
            throw new FrequencyControlException("FREQUENCY_LIMIT");
        }
        try {
            return supplier.get();
        } finally {
            addFrequencyControlStatisticsCount(frequencyControlMap);
        }
    }

    public <T> T executeWithFrequencyControlList(List<K> frequencyControlList, SupplierThrowWithoutParam<T> supplier) throws Throwable {
        Map<String, FrequencyControlDTO> frequencyControlDTOMap = frequencyControlList.stream().collect(Collectors.groupingBy(FrequencyControlDTO::getKey, Collectors.collectingAndThen(Collectors.toList(), list -> list.get(0))));
        return executeWithFrequencyControlMap((Map<String, K>) frequencyControlDTOMap, supplier);
    }

    @FunctionalInterface
    public interface SupplierThrowWithoutParam<T> {
        T get() throws Throwable;
    }

    protected abstract boolean reachRateLimit(Map<String, K> frequencyControlMap);

    protected abstract void addFrequencyControlStatisticsCount(Map<String, K> frequencyControlMap);

    protected abstract String getStrategyName();

}
