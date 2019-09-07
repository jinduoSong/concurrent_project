package itmayi.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author songjd
 * @date 19:51 2019/9/1.
 */
public class StreamTest {

    /*
        筛选与切片
        filter-接收lambda，从流中过滤排除某些元素
        limit-截断流，使其元素不超过给定的数量
        skip-跳过元素，返回一个扔掉钱n个元素的流，如果流中元素不够n个，则返回一个空流
        distinct-筛选，通过流所生成元素的hashCode和equals去除重复元素
     */

    List<Employee> employeeList = Arrays.asList(
            new Employee("张三", 22, 5555.5),
            new Employee("李四", 25, 6666.6),
            new Employee("王五", 36, 7777.7),
            new Employee("赵六", 28, 8888.8),
            new Employee("赵六", 28, 8888.8),
            new Employee("赵六", 28, 8888.8),
            new Employee("田七", 38, 5555.5)
    );


    //去除年龄大于35的
    @Test
    public void test1() {
        Stream<Employee> stream = employeeList.stream().filter((e) -> e.getAge() > 35);
        stream.forEach(System.out::println);
    }

    @Test
    public void test2(){
        employeeList.stream().filter((e)->e.getSalary()>5555).skip(2).forEach(System.out::println);
    }

    @Test
    public void test3(){
        employeeList.stream().distinct().forEach(System.out::println);
    }

    /*
        中间操作
        map-接收lambda,将元素转换成其他形式或者提取信息，接收一个函数作为参数，该函数会被应用到每一个元素上，并将其映射为一个新的元素
        flatMap-接收一个函数作为参数，将流中的每一个值都换成另一个流，然后把所有的流连接成一个流
     */

    @Test
    public void test4(){
        List<String> stringList =Arrays.asList("aaa","bbb","ccc","ddd","eee");

        stringList.stream().map((str)->str.toUpperCase()).forEach(System.out::println);

        employeeList.stream().map(Employee::getName).forEach(System.out::println);
    }


    /*
        排序：
           sorted()-自然排序
           sorted(Comparator com)-定制排序
     */

    @Test
    public void test5(){
        List<String> stringList =Arrays.asList("ccc","aaa","bbb","ddd","eee");
        stringList.stream().sorted().forEach(System.out::println);
        System.out.println("-------------------");

        employeeList.stream().sorted((e1,e2)->{
            if(e1.getAge().equals(e2.getAge())){
                return e1.getName().compareTo(e2.getName());
            }else {
                return e1.getAge().compareTo(e2.getAge());
            }
        }).forEach(System.out::println);
    }

    /**
     * collect 集合收集
     */

    @Test
    public void test6(){
        List<String> stringList = employeeList.stream().map(Employee::getName).collect(Collectors.toList());
        stringList.forEach(System.out::println);
        System.out.println("-------------------------------");
        HashSet<String> collect = employeeList.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        collect.forEach(System.out::println);
    }
}
