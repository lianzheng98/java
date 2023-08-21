package com.pg.cc.sb.web.freq.aspect;

import com.pg.cc.sb.web.freq.annotation.FrequencyControl;
import com.pg.cc.sb.web.freq.data.FrequencyControlDTO;
import com.pg.cc.sb.web.freq.utils.CollectionUtilsEx;
import com.pg.cc.sb.web.freq.utils.FrequencyControlUtil;
import com.pg.cc.sb.web.freq.utils.SpElUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class FrequencyControlAspect {
    @Around("@annotation(com.pg.cc.sb.web.freq.annotation.FrequencyControl)||@annotation(com.pg.cc.sb.web.freq.annotation.FrequencyControlContainer)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        FrequencyControl[] annotationsByType = method.getAnnotationsByType(FrequencyControl.class);
        Map<String, FrequencyControl> keyMap = new HashMap<>();
        for (FrequencyControl frequencyControl : annotationsByType) {
            String prefix = StringUtils.isEmpty(frequencyControl.prefixKey()) ? SpElUtils.getMethodKey(method) : frequencyControl.prefixKey();
            String key = "";
            keyMap.put(prefix + ":" + key, frequencyControl);
        }
        List<FrequencyControlDTO> frequencyControlDTOS = CollectionUtilsEx.mapToList(keyMap.entrySet(),
                entrySet -> buildFrequencyControlDTO(entrySet.getKey(), entrySet.getValue()));

        return FrequencyControlUtil.executeWithFrequencyControlList(TOTAL_COUNT_WITH_IN_FIX_TIME_FREQUENCY_CONTROLLER,
                frequencyControlDTOS,
                joinPoint::proceed);

    }

    public static final String TOTAL_COUNT_WITH_IN_FIX_TIME_FREQUENCY_CONTROLLER = "TotalCountWithInFixTime";

    private FrequencyControlDTO buildFrequencyControlDTO(String key, FrequencyControl frequencyControl) {
        FrequencyControlDTO frequencyControlDTO = new FrequencyControlDTO();
        frequencyControlDTO.setCount(frequencyControl.count());
        frequencyControlDTO.setTime(frequencyControl.time());
        frequencyControlDTO.setUnit(frequencyControl.unit());
        frequencyControlDTO.setKey(key);
        return frequencyControlDTO;
    }

}