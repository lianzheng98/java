package com.pg.cc.sb.web.freq.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)// 运行时生效
@Target(ElementType.METHOD)// 作用在方法上
public @interface FrequencyControl {

    String prefixKey() default "";
    Target target() default Target.EL;
    String spEl() default "";
    int time();
    TimeUnit unit() default TimeUnit.SECONDS;
    int count();

    enum Target {
        UID, IP, EL
    }

}
