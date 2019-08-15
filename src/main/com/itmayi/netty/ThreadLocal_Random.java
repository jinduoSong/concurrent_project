package itmayi.netty;

import java.util.Random;
import java.util.concurrent.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author songjd
 * @date 17:35 2019/7/11.
 */
public class ThreadLocal_Random {
    public static final int GEN_COUNT = 10000000;   //定义每个线程产生随机数的数量
    public static final int THREAD_COUNT = 4;       //定义参与工作的线程数
    static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);    //定义线程池
    public static Random random = new Random(123);  //定义被多线程共享的Random实例，用于产生随机数
    /**
     * 定义了由ThreadLocal封装的Random
     */
    public static ThreadLocal<Random> threadLocal = new ThreadLocal<Random>() {
        @Override
        protected Random initialValue() {
            return new Random(123);
        }
    };

    /**
     * 定义一个工作线程的内部逻辑，他可以工作在两种模式下
     * <p>
     * 第一个是多线程共享一个Random（mode=0）
     * 第二个是多线程各分配一个Random（mode=1）
     */
    public static class RndTask implements Callable<Long> {
        private int mode = 0;

        public RndTask(int mode) {
            this.mode = mode;
        }

        public Random getRandom() {
            if (mode == 0) {
                return random;
            } else if (mode == 1) {
                return threadLocal.get();
            } else {
                return null;
            }
        }

        /**
         * 定义线程的工作内容，每个线程都会产生若干个随机数，完成工作后，记录并返回消耗的时间
         */
        public Long call() {
            long b = System.currentTimeMillis();
            for (long i = 0; i < GEN_COUNT; i++) {
                getRandom().nextInt();
            }
            long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " spend " + (e - b) + "ms");
            return e - b;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<Long>[] futs = new Future[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            futs[i] = executorService.submit(new RndTask(0));
        }
        long totaltime = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totaltime += futs[i].get();
        }
        System.out.println("多线程访问同一个Random实例耗时：" + totaltime + "ms");

        for (int i = 0; i < THREAD_COUNT; i++) {
            futs[i] = executorService.submit(new RndTask(1));
        }
        totaltime = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totaltime += futs[i].get();
        }
        System.out.println("使用ThreadLocal包装Random实例耗时：" + totaltime + "ms");
        executorService.shutdown();
    }

}
