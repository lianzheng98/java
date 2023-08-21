package com.pg.cc.sb.web.freq.utils;

import com.pg.cc.sb.web.freq.service.AbstractFrequencyControlService;
import com.pg.cc.sb.web.freq.service.AbstractFrequencyControlService.SupplierThrowWithoutParam;
import com.pg.cc.sb.web.freq.data.FrequencyControlDTO;
import com.pg.cc.sb.web.freq.factory.FrequencyControlStrategyFactory;

import java.util.List;

public class FrequencyControlUtil {

    public static <T, K extends FrequencyControlDTO> T executeWithFrequencyControlList(String strategyName, List<K> frequencyControlList, SupplierThrowWithoutParam<T> supplier) throws Throwable {
        AbstractFrequencyControlService<K> frequencyController = FrequencyControlStrategyFactory.getFrequencyControllerByName(strategyName);
        return frequencyController.executeWithFrequencyControlList(frequencyControlList, supplier);
    }

    private FrequencyControlUtil() {
    }

}
