package com.pg.cc.sb.web.freq;

import com.google.gson.Gson;
import com.pg.cc.sb.web.freq.annotation.FrequencyControl;
import com.pg.cc.sb.web.freq.utils.CollectionUtilsEx;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author lianzheng
 */
@RestController
@RequestMapping("/lock")
public class LockController {

    private static int aInt = 0;


    @GetMapping("/add")
    @ResponseBody
    @FrequencyControl(prefixKey = "a", time = 10, count = 2)
    public String add() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<Integer> integers1 = CollectionUtilsEx.mapToList(integers, (i) -> i + 1);
        if (true)
            return new Gson().toJson(integers1);
        return String.valueOf(aInt++);
    }

    @GetMapping("/desc")
    @ResponseBody
    public int desc() {
        return aInt--;
    }
}