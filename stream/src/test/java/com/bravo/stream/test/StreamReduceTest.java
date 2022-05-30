package com.bravo.stream.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Stream test reduce function
 *
 * 初始值的定义（Identity)：归并操作的初始值，如果Stream 是空的，也是Stream 的默认结果
 * 累加器（Accumulator)：归并操作的初始值，如果Stream 是空的，也是Stream 的默认结果
 * 组合器（Combiner）:调用一个函数来组合归并操作的结果，当归并是并行执行或者当累加器的函数和累加器的实现类型不匹配时才会调用此函数。
 *
 * @author zhangxinyu
 * @since 2022/5/30 12:14
 */
public class StreamReduceTest {
    @Test
    public void demo() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        numbers = Collections.emptyList();
        int result = numbers
                .stream()
                .reduce(0, (subtotal, element) -> subtotal + element);
        assertThat(result).isEqualTo(21);
    }

    @Test
    public void demo2() {
        List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
        String result = letters
                .stream()
                .reduce("", (partialString, element) -> partialString + element); // 可以使用String::concat优化，但是要考虑concat的NullpointerException问题
        assertThat(result).isEqualTo("abcde");
    }

    /**
     * 操作并行流
     * 当对一个流进行并行操作时，在运行时会把流分割多个子流来并行操作。需要一个函数来组合各个子流返回的结果，这个函数就是Combiner。
     */
    @Test
    public void demo3() {
        List<Integer> ages = Arrays.asList(25, 30, 45, 28, 32);
        int computedAges = ages.parallelStream().reduce(0, (a, b) -> a + b, Integer::sum);
        assertThat(computedAges).isEqualTo(160);
    }

    @Test
    public void demo4() {
        List<User> users = Arrays.asList(new User("John", 30), new User("Julie", 35));
        // 累加器的实现是求和，所以编译器无法推断参数 user 的类型, 当顺序读流或者累加器的参数和它的实现的类型匹配时，不需要使用组合器。
        int computedAges =
                users.stream().reduce(0, (partialAgeResult, user) -> partialAgeResult + user.getAge(), Integer::sum);
    }

    /**
     * 处理异常的方式
     */
    @Test
    public void demo5() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int divider = 2;
        int result = divideListElements(numbers, divider);
    }

    private int divideListElements(List<Integer> numbers, int divider) {
        // 优化
//        return numbers.stream().reduce(0, (a, b) -> {
//            try {
//                return a/2 + b /2;
//            } catch (ArithmeticException e) {
//                e.printStackTrace();
//            }
//            return 0;
//        });
        return numbers.stream().reduce(0, (a, b)-> divide(a, divider) + divide(b, divider));
    }

    private int divide(int value, int factor) {
        int result = 0;
        try {
            result = value / factor;
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
        return result;
    }

    @AllArgsConstructor
    @Setter@Getter
    class User{
        private String username;
        private int age;
    }
}
