package com.bravo.test.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Test
    public void demo4() {
        ArrayList<User> users = new ArrayList<>();
        for(int j =0 ; j < 2; j++) {
            for (int i = 0; i < 3; i++) {
                User user = new User();
                user.setDimessionId(i + "");
                user.setScore(100 + i);
                users.add(user);
            }
        }
        User user = new User();
        user.setScore(200);
        users.add(user);


        Map<String, List<User>> map = users.stream().peek(t-> {
            if(t.getDimessionId() == null) {
                t.setDimessionId("");
            }
        }).collect(Collectors.groupingBy(User::getDimessionId));

        ArrayList<User> allUsers = new ArrayList<>(map.size());
        map.forEach((k,v) -> {
            String s = v.stream().map(item -> new BigDecimal(item.getScore())).reduce(BigDecimal.ZERO, BigDecimal::add).toPlainString();
            String dimessionId = v.get(0).getDimessionId();
            User user1 = new User();
            user1.setDimessionId(dimessionId);
            user1.setScore(Integer.valueOf(s));
            allUsers.add(user1);
        });

        System.out.println("heewerw");
    }
}
