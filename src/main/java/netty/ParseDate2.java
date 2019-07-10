package netty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author songjd
 * @date 16:56 2019/7/10.
 */
public class ParseDate2 implements Runnable {
    static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    int i = 0;

    public ParseDate2(int i) {
        this.i = i;
    }

    public void run() {
        try {
            if (threadLocal.get() == null) {
                threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            }
            Date date = threadLocal.get().parse("2015-03-09 19:29:" + i % 60);
            System.out.println(i + ":" + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ParseDate2(i));
        }
    }
}
