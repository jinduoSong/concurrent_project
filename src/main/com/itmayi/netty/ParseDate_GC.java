package itmayi.netty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author songjd
 * @date 15:00 2019/7/11.
 */
public class ParseDate_GC {
    static volatile ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected void finalize() throws Throwable {
            System.out.println(this.toString() + "is gc");
        }
    };
    static volatile CountDownLatch countDownLatch = new CountDownLatch(1000);
    public static class ParseDate implements Runnable{
        int i =0;
        public ParseDate(int i) {
            this.i = i;
        }

        public void run() {
            try {
                if (threadLocal.get() == null) {
                    threadLocal.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"){
                        @Override
                        protected void finalize() throws Throwable {
                            System.out.println(this.toString() + "is gc");
                        }
                    });
                    System.out.println(Thread.currentThread().getId()+": create SimpleDateFormat");
                }
                Date date = threadLocal.get().parse("2015-03-09 19:29:" + i % 60);
            } catch (ParseException e) {
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ParseDate(i));
        }
        countDownLatch.await();
        System.out.println("mission complete");
        threadLocal = null;
        System.gc();
        System.out.println("first gc complete");
        //在设置ThreadLocal的时候，会清除ThreadLocalMap中的无效对象
        threadLocal = new ThreadLocal<SimpleDateFormat>();
        countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new ParseDate(i));
        }
        countDownLatch.await();
        Thread.sleep(1000);
        System.gc();
        System.out.println("second gc complete");
        executorService.shutdown();
    }
}
