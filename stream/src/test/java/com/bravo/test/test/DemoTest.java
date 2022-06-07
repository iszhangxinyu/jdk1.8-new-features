package com.bravo.test.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 随机测试
 *
 * @author zhangxinyu
 * @since 2022/5/31 11:32
 */
public class DemoTest {
    @Test
    public void demo() {
        int permissions = 16902;
        int permission = 2;
        int i = (permissions | (1 << permission)) - (1 << permission);

    }

    @Test
    public void demo2() {
        String format = new DecimalFormat("0.00%").format(BigDecimal.ZERO);
        System.out.println(format);

    }

    @Test
    public void demo3() {
//        double v = Double.parseDouble("");
        Double aDouble = Double.valueOf("");
        System.out.println(aDouble);
    }
}
