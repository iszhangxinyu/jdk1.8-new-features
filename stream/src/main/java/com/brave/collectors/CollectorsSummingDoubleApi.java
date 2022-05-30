package com.brave.collectors;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Collectors.summingDouble
 *
 * @author zhangxinyu
 * @since 2022/5/30 20:28
 */
public class CollectorsSummingDoubleApi {
    @Test
    public void example() {
        List<Double> list = Arrays.asList(1.1, 2.2, 3.3, 4.4);
        // 每个数值会打印两次
        Double sum = list.stream().collect(Collectors.summingDouble(i -> {
            System.out.println("i -> " + i);
            return i;
        }));
        System.out.println(sum);
    }

}
