package com.pg.cc.sb.web.freq.service;

import com.pg.cc.sb.web.freq.data.FrequencyControlDTO;
import com.pg.cc.sb.web.freq.factory.FrequencyControlStrategyFactory;
import com.pg.cc.sb.web.freq.service.AbstractFrequencyControlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lianzheng
 */
@Slf4j
@Service
public class TotalCountInFixTimeFrequencyControlService extends AbstractFrequencyControlService<FrequencyControlDTO> {

    private static Map<String, Integer> maps = new ConcurrentHashMap<>();

    @Override
    protected boolean reachRateLimit(Map<String, FrequencyControlDTO> frequencyControlMap) {
        return false;
    }

    @Override
    protected void addFrequencyControlStatisticsCount(Map<String, FrequencyControlDTO> frequencyControlMap) {
        frequencyControlMap.forEach((K, v) -> {
            log.info("key:{},value:{}", K, v);
            maps.put(K, maps.getOrDefault(K, 0) + 1);
        });
    }

    @Override
    protected String getStrategyName() {
        return FrequencyControlStrategyFactory.TOTAL_COUNT_WITH_IN_FIX_TIME;
    }
}