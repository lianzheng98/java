package com.pg.cc.sb.web.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/12/14 6:47 下午
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log2Center {

    String method() default "";
}
