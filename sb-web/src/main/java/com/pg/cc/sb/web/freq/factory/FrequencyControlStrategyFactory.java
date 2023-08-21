package com.pg.cc.sb.web.freq.factory;

import com.pg.cc.sb.web.freq.service.AbstractFrequencyControlService;
import com.pg.cc.sb.web.freq.data.FrequencyControlDTO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lianzheng
 */
public class FrequencyControlStrategyFactory {

    public static final String TOTAL_COUNT_WITH_IN_FIX_TIME = "TotalCountWithInFixTime";

    static Map<String, AbstractFrequencyControlService<?>> frequencyControlServiceStrategyMap = new ConcurrentHashMap<>(8);

    public static <K extends FrequencyControlDTO> void registerFrequencyController(String strategyName, AbstractFrequencyControlService<K> abstractFrequencyControlService) {
        frequencyControlServiceStrategyMap.put(strategyName, abstractFrequencyControlService);
    }

    public static <K extends FrequencyControlDTO> AbstractFrequencyControlService<K> getFrequencyControllerByName(String strategyName) {
        return (AbstractFrequencyControlService<K>) frequencyControlServiceStrategyMap.get(strategyName);
    }


    private FrequencyControlStrategyFactory() {

    }
}