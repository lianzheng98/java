/**
 * @(#)paramStr.java, 7月 19, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.pg.cc.sb.core.TTest;

/**
 * @author lianzhengbj
 */
public class Exam {

    private String asdList;
    public boolean canViewAnswer(String viewAnswer) {
        if (viewAnswer == null) {
            return false;
        }
        return viewAnswer.equals("123");
    }

}