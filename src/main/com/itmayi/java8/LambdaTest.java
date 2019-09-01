package itmayi.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author songjd
 * @date 20:30 2019/8/28.
 */
public class LambdaTest {

    @Test
    public void test() {
        Consumer consumer = x -> System.out.println(x);
        consumer.accept(2);
    }

    @Test
    public void test2() {
        Comparator<Integer> comparable = (x, y) -> Integer.compare(x, y);
        int compare = comparable.compare(2, 3);
        System.out.println(compare);
    }

    @Test
    public void test3() {
        String s = stringStander("\t\t sjd  ", (str) -> str.trim());
        System.out.println(s);
    }

    public String stringStander(String str, MyFunction myFunction) {
        return myFunction.getValue(str);
    }

    @Test
    public void test4() {
        List<Integer> integerList = generateNumber(10, () -> (int) (Math.random() * 100));
        for (Integer integer : integerList) {
            System.out.println(integer);
        }
    }

    //需求：生成制定个数的整数，存入集合并返回
    public List<Integer> generateNumber(int count, Supplier<Integer> supplier) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Integer integer = supplier.get();
            integerList.add(integer);
        }
        return integerList;
    }

    @Test
    public void test5(){
        Supplier<List<String>> supplier = ()->new ArrayList<>();
        List<String> stringList = supplier.get();
    }
}
