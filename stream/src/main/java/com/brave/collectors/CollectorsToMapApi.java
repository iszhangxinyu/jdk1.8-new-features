package com.brave.collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Collectors.toMap()
 * <p>
 * toMap(Function keyMapper, Function valueMapper,BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier);
 * <p>
 * keyMapper: 定义key的映射函数，Person::getId 使用Person的id作为key
 * valueMapper: 定义value的映射函数，v->v 使用list中的元素作为作为Map的value       value的值不要为null，会报NullpointerException
 * mergeFunction: 如果key存在冲突，定义解决的办法 （key不能重复的）  IllegalStateException:Duplicate key
 * mapSupplier：Map 构造器，在需要返回特定的 Map 时使用
 *
 * @author zhangxinyu
 * @since 2022/5/30 20:12
 */
public class CollectorsToMapApi {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person(1, "1"));
//        list.add(new Person(1, "4"));
        list.add(new Person(2, "2"));
        list.add(new Person(3, "3"));

        Map<Integer, Person> collect = list.stream().collect(Collectors.toMap(Person::getId, Function.identity()));
        Map<Integer, Person> collect1 = list.stream().collect(Collectors.toMap(Person::getId, Function.identity(), (a, b) -> a));
        Map<Integer, Person> collect2 = list.stream().collect(Collectors.toMap(Person::getId, v -> v, (a, b) -> a));
        Collection<Person> values = list.stream().collect(Collectors.toMap(Person::getId, Function.identity(), (a, b) -> a)).values();
        long count = list.stream().collect(Collectors.toMap(Person::getId, Function.identity(), (a, b) -> a)).values().stream().count();
        System.out.println(collect);
        System.out.println(collect1);
        System.out.println(collect2);
        System.out.println(values);
        System.out.println(count);
    }
}

@Setter
@Getter
@AllArgsConstructor
class Person {
    private Integer id;
    private String name;

}
