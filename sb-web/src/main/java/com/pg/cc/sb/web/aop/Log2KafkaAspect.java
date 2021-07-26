package com.pg.cc.sb.web.aop;

import com.google.gson.Gson;
import com.pg.cc.sb.web.annoation.Log2Center;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/12/14 5:17 下午
 */

@Aspect
@Component
@Slf4j
public class Log2KafkaAspect {

    private final static String LOG_MAGIC_WORD = "#XMDT#";

    @Around("@annotation(com.pg.cc.sb.web.annoation.Log2Center)")
    public Object Log2CenterProceed(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        Log2Center log2Center = method.getAnnotation(Log2Center.class);
        String methodName = log2Center.method();
        Object[] params = pjp.getArgs();
        Object result = pjp.proceed();
        Map<String, String> tags = new HashMap<>();
        //        String paramJ = JSON.toJSONString(params);
        //        paramJ = paramJ.replace(LOG_MAGIC_WORD, "");
        tags.put("methodName", methodName);
        //        tags.put("param", paramJ);
        tags.put("result", new Gson().toJson(result));
        log.info("######## {}", new Gson().toJson(tags));
        return result;
    }
}
