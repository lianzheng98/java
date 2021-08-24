/**
 * @(#)AA.java, 8月 09, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.pg.cc.sb.core.TTest;

import java.util.Map;

/**
 * @author lianzhengbj
 */
public class AA {

    public static void main(String[] args) {

        Map<Integer, Integer> asdList = null;
        asdList.entrySet().stream().forEach(s->{
            System.out.println(s);
        });
        return;
    }
}