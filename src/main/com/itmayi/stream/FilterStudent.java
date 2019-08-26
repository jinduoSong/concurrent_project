package itmayi.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author songjd
 * @date 9:48 2019/8/26.
 */
public class FilterStudent {
    public static void main(String[] args) {
        Student s1 = new Student(1L, "肖战", 15, "浙江");
        Student s2 = new Student(2L, "王一博", 15, "湖北");
        Student s3 = new Student(3L, "杨紫", 17, "北京");
        Student s4 = new Student(4L, "李现", 17, "浙江");
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

//        List<Student> studentList = testFilter(students);
//        studentList.forEach(System.out::println);

//        testMap(students);

        testDistinct1();
    }

    private static List<Student> testFilter(List<Student> students) {
        return students.stream().filter(s -> "浙江".equals(s.getAddress())).collect(Collectors.toList());
    }

    /**
     * 集合转换
     * @param students
     * @return
     */
    private static void testMap(List<Student> students) {
        //在地址前面加上部分信息，只获取地址输出
        List<String> addresses = students.stream().map(s ->"住址:"+s.getAddress()).collect(Collectors.toList());
        addresses.forEach(a ->System.out.println(a));
    }

    /**
     * 集合去重（基本类型）
     */
    private static void testDistinct1() {
        //简单字符串的去重 对象去重要重新hashCode和equals方法
        List<String> list = Arrays.asList("111","222","333","111","222");
        list.stream().distinct().forEach(System.out::println);
    }
}
