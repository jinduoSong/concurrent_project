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
public class ParseDate implements Runnable{
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    int i = 0;

    public ParseDate(int i) {
        this.i = i;
    }

    public void run() {
        try {
            Date date = simpleDateFormat.parse("2015-03-09 19:29:" + i % 60);
            System.out.println(i+":"+date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ParseDate(i));
        }
    }
}
