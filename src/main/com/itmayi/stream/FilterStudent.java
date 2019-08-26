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
        Student s1 = new Student(1L, "Фս", 15, "�㽭");
        Student s2 = new Student(2L, "��һ��", 15, "����");
        Student s3 = new Student(3L, "����", 17, "����");
        Student s4 = new Student(4L, "����", 17, "�㽭");
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
        return students.stream().filter(s -> "�㽭".equals(s.getAddress())).collect(Collectors.toList());
    }

    /**
     * ����ת��
     * @param students
     * @return
     */
    private static void testMap(List<Student> students) {
        //�ڵ�ַǰ����ϲ�����Ϣ��ֻ��ȡ��ַ���
        List<String> addresses = students.stream().map(s ->"סַ:"+s.getAddress()).collect(Collectors.toList());
        addresses.forEach(a ->System.out.println(a));
    }

    /**
     * ����ȥ�أ��������ͣ�
     */
    private static void testDistinct1() {
        //���ַ�����ȥ�� ����ȥ��Ҫ����hashCode��equals����
        List<String> list = Arrays.asList("111","222","333","111","222");
        list.stream().distinct().forEach(System.out::println);
    }
}
