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
    public static final int GEN_COUNT = 10000000;   //����ÿ���̲߳��������������
    public static final int THREAD_COUNT = 4;       //������빤�����߳���
    static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);    //�����̳߳�
    public static Random random = new Random(123);  //���屻���̹߳����Randomʵ�������ڲ��������
    /**
     * ��������ThreadLocal��װ��Random
     */
    public static ThreadLocal<Random> threadLocal = new ThreadLocal<Random>() {
        @Override
        protected Random initialValue() {
            return new Random(123);
        }
    };

    /**
     * ����һ�������̵߳��ڲ��߼��������Թ���������ģʽ��
     * <p>
     * ��һ���Ƕ��̹߳���һ��Random��mode=0��
     * �ڶ����Ƕ��̸߳�����һ��Random��mode=1��
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
         * �����̵߳Ĺ������ݣ�ÿ���̶߳���������ɸ����������ɹ����󣬼�¼���������ĵ�ʱ��
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
        System.out.println("���̷߳���ͬһ��Randomʵ����ʱ��" + totaltime + "ms");

        for (int i = 0; i < THREAD_COUNT; i++) {
            futs[i] = executorService.submit(new RndTask(1));
        }
        totaltime = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totaltime += futs[i].get();
        }
        System.out.println("ʹ��ThreadLocal��װRandomʵ����ʱ��" + totaltime + "ms");
        executorService.shutdown();
    }

}
