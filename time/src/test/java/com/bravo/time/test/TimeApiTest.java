package com.bravo.time.test;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * java.time.* api测试
 *
 * @author zhangxinyu
 * @since 2022/5/30 10:12
 */
public class TimeApiTest {
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM");

    @Test
    public void demo() {
        LocalDate now = LocalDate.now();

        LocalDateTime of = LocalDateTime.of(2022, 5, 30, 10, 22, 10);
    }


    /**
     * 解析年月格式String为LocalDate，报java.time.format.DateTimeParseException
     * 解决方式：
     */
    @Test
    public void convertLocalDate() {
        // 方式一
        YearMonth ym = YearMonth.parse("2020-04", fmt);
        LocalDate dt1 = ym.atEndOfMonth();
        LocalDate localDate = ym.atDay(12);

        // 方式二
        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM")
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();
        LocalDate dt2 = LocalDate.parse("2020-04", fmt);
    }

}
