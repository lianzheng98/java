/**
 * @(#)GroupSTream.java, 6月 25, 2021.
 * <p>
 * Copyright 2021 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.pg.cc.sb.core.stream;

import com.google.common.collect.Lists;
import com.pg.cc.sb.core.util.JSONUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static void main(String[] args) {
        ArrayList<TestVO> arrs = Lists.newArrayList();
        arrs.add(new TestVO(1, Lists.newArrayList(1, 2, 3)));
        arrs.add(new TestVO(1, Lists.newArrayList(1, 2, 4)));
        arrs.add(new TestVO(2, Lists.newArrayList(1, 2, 6)));
        arrs.add(new TestVO(3, Lists.newArrayList(10, 11, 6)));
        //        Map<Integer, List<TestVO>> collect = arrs.stream().collect(Collectors.groupingBy(TestVO::getLength));
        //        Map<Integer, Set<TestVO>> collect1 =
        //                arrs.stream().collect(Collectors.groupingBy(TestVO::getLength, Collectors.toSet()));
        //        Map<Integer, Set<List<Integer>>> collect2 =
        //                arrs.stream().collect(Collectors.groupingBy(TestVO::getLength, Collectors.mapping((TestVO t) -> {
        //                    return t.getRanks();
        //                }, Collectors.toSet()));

        //        arrs.stream().collect(Collectors.reducing(TestVO::getLength,
        //
        //
        //                System.out.println(JSONUtils.writeValue(collect1));
        Set<Integer> objects = new HashSet<>();
        objects.add(1111);
        TestVO testVO = new TestVO();
        List<Integer> aaa = new ArrayList<>();


        //        Map<Integer, Set<Integer>> collect = arrs.stream().collect(Collectors.groupingBy(TestVO::getLength,
        //                Collectors.reducing(objects, test -> new HashSet<>(test.getRanks()), (a, b) -> {
        //                    System.out.println(
        //                            JSONUtils.writeValue(a) + "   " +JSONUtils.writeValue(b)
        //                    );
        //                    a.addAll(b);
        //                    return a;
        //
        //                }))
        //
        //
        //        ); 
        Map<Integer, Optional<HashSet<Integer>>> collect = arrs.stream().collect(Collectors
                .groupingBy(TestVO::getLength,
                        Collectors.mapping(test -> new HashSet<>(test.getRanks()), Collectors.reducing((a, b) -> {
                            return a;
                        }))));


        System.out.println(JSONUtils.writeValue(collect));

        arrs.parallelStream().collect(Collectors.groupingBy(TestVO::getLength));


        //                .reduce(objects, (a, b) -> {
        //            List<Integer> ranks = b.getRanks();
        //            a.addAll(b.getRanks());
        //            return a;
        //        }, (a, b) -> {
        //            a.addAll(b);
        //            return a;
        //    });

        //        Map<String, List<item>> countMap = recordItems.stream().collect(Collectors.groupingBy(o -> o.getProductType()));
        //
        //        List<Record> records = new ArrayList<Record>;
        //        countMap.keySet().forEach(productType -> {
        //            Map<String, Long> countMap1 = countMap.get(productType).stream().collect(Collectors.groupingBy(o -> o.getCountry(), Collectors.counting()));
        //            countMap1(key).stream().forEach(country -> {
        //                Record record = new Record();
        //                record.set("device_type", productType);
        //                record.set("location", country;
        //                record.set("count", countMap1(key).intValue());
        //                records.add(record);
        //            });
        //        });
        //
        //
        //        // 分组统计
        //        Map<String, Long> countMap = records.stream().collect(Collectors.groupingBy(o -> o.getProductType() + "_" + o.getCountry(), Collectors.counting()));
        //
        //        List<Record> countRecords = countMap.keySet().stream().map(key -> {
        //            String[] temp = key.split("_");
        //            String productType = temp[0];
        //            String country = temp[1];
        //
        //            Record record = new Record();
        //            record.set("device_type", productType);
        //            record.set("location", country;
        //            record.set("count", countMap.get(key).intValue());
        //            return record;
        //        }).collect(Collectors.toList());


        //a
        //        Map<Long, List<Long>> exhibitionPitemMap = list.stream().collect(Collectors
        //                .groupingBy(TestDTO1::getLevle1CategoryId,
        //                        Collectors.mapping(TestDTO1::getPitemId, Collectors.toList())));
        //        //b
        //        Map<Long, List<TestDTO2>> categoryPitemMap =
        //                list.stream().collect(Collectors.groupingBy(TestDTO2::getLevle1CategoryId));


    }
}