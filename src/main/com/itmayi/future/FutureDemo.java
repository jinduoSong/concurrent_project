package itmayi.future;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executors;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author songjd
 * @date 10:07 2019/8/15.
 * Guava对Future模式的支持
 */
public class FutureDemo {
    public static void main(String[] args) throws InterruptedException {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> task = listeningExecutorService.submit(new RealData("x"));
        task.addListener(() -> {
            System.out.print("异步处理成功：");
            try {
                System.out.println(task.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, MoreExecutors.directExecutor());

        System.out.println("main task done......");
        Thread.sleep(3000);
    }
}
