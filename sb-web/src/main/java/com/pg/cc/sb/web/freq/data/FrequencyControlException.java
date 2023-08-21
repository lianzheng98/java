package com.pg.cc.sb.web.freq.data;

import lombok.Data;

@Data
public class FrequencyControlException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    protected Integer errorCode;

    protected String errorMsg;

    public FrequencyControlException() {
        super();
    }

    public FrequencyControlException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }
}
