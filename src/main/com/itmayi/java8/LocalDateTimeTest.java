package itmayi.java8;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author songjd
 * @date 14:40 2019/9/7.
 */
public class LocalDateTimeTest {

    //本地时间
    @Test
    public void test1() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        //时间增加
        LocalDateTime localDateTime = now.plusYears(1);
        LocalDateTime localDateTime1 = localDateTime.plusMonths(6);
        System.out.println(localDateTime1);
    }

    //系统时间，时间戳
    @Test
    public void test2() {
        Instant instant = Instant.now(); //获取UTC时区
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        System.out.println(instant.toEpochMilli());
    }

    //格式化时间日期格式
    @Test
    public void test3() {
        String format = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
        System.out.println(format);
        String format1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format1);
        String format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        System.out.println(format2);
    }

    //字符串转换回时间日期
    @Test
    public void test4(){
        String format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime parse = localDateTime.parse(format2, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(parse);
    }

}
