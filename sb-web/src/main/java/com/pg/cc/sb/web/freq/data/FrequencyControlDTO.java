package com.pg.cc.sb.web.freq.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.concurrent.TimeUnit;

/**
 * @author lianzheng
 */

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrequencyControlDTO {
    private String key;
    private Integer time;
    private TimeUnit unit;
    private Integer count;
}
