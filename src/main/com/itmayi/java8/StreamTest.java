package itmayi.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author songjd
 * @date 19:51 2019/9/1.
 */
public class StreamTest {

    List<Employee> employeeList = Arrays.asList(
            new Employee("����", 22, 5555.5),
            new Employee("����", 25, 6666.6),
            new Employee("����", 36, 7777.7),
            new Employee("����", 28, 8888.8),
            new Employee("����", 38, 5555.5)
    );


    //ȥ���������35��
    @Test
    public void test1() {
        Stream<Employee> stream = employeeList.stream().filter((e) -> e.getAge() > 35);
        stream.forEach(System.out::println);
    }
}
