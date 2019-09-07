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
        ɸѡ����Ƭ
        filter-����lambda�������й����ų�ĳЩԪ��
        limit-�ض�����ʹ��Ԫ�ز���������������
        skip-����Ԫ�أ�����һ���ӵ�Ǯn��Ԫ�ص������������Ԫ�ز���n�����򷵻�һ������
        distinct-ɸѡ��ͨ����������Ԫ�ص�hashCode��equalsȥ���ظ�Ԫ��
     */

    List<Employee> employeeList = Arrays.asList(
            new Employee("����", 22, 5555.5),
            new Employee("����", 25, 6666.6),
            new Employee("����", 36, 7777.7),
            new Employee("����", 28, 8888.8),
            new Employee("����", 28, 8888.8),
            new Employee("����", 28, 8888.8),
            new Employee("����", 38, 5555.5)
    );


    //ȥ���������35��
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
        �м����
        map-����lambda,��Ԫ��ת����������ʽ������ȡ��Ϣ������һ��������Ϊ�������ú����ᱻӦ�õ�ÿһ��Ԫ���ϣ�������ӳ��Ϊһ���µ�Ԫ��
        flatMap-����һ��������Ϊ�����������е�ÿһ��ֵ��������һ������Ȼ������е������ӳ�һ����
     */

    @Test
    public void test4(){
        List<String> stringList =Arrays.asList("aaa","bbb","ccc","ddd","eee");

        stringList.stream().map((str)->str.toUpperCase()).forEach(System.out::println);

        employeeList.stream().map(Employee::getName).forEach(System.out::println);
    }


    /*
        ����
           sorted()-��Ȼ����
           sorted(Comparator com)-��������
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
     * collect �����ռ�
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
