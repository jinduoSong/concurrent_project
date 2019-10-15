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

    //����ʱ��
    @Test
    public void test1() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        //ʱ������
        LocalDateTime localDateTime = now.plusYears(1);
        LocalDateTime localDateTime1 = localDateTime.plusMonths(6);
        System.out.println(localDateTime1);
    }

    //ϵͳʱ�䣬ʱ���
    @Test
    public void test2() {
        Instant instant = Instant.now(); //��ȡUTCʱ��
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        System.out.println(instant.toEpochMilli());
    }

    //��ʽ��ʱ�����ڸ�ʽ
    @Test
    public void test3() {
        String format = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
        System.out.println(format);
        String format1 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format1);
        String format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        System.out.println(format2);
    }

    //�ַ���ת����ʱ������
    @Test
    public void test4(){
        String format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime parse = localDateTime.parse(format2, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(parse);
    }

}
