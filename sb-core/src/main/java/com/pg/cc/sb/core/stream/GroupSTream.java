/**
 * @(#)GroupSTream.java, 6月 25, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.pg.cc.sb.core.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lianzhengbj
 */
public class GroupSTream {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class TestVO {

        int length;

        List<Integer> ranks;
    }

}