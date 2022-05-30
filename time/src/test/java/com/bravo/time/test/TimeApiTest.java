package com.bravo.time.test;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * java.time.* api测试
 *
 * @author zhangxinyu
 * @since 2022/5/30 10:12
 */
public class TimeApiTest {

    @Test
    public void demo() {
        LocalDate now = LocalDate.now();

        LocalDateTime of = LocalDateTime.of(2022, 5, 30, 10, 22, 10);
    }

}
