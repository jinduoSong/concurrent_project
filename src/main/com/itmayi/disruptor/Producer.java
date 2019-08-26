package itmayi.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author songjd
 * @date 10:25 2019/8/5.
 */
public class Producer {

    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer byteBuffer) {
        long sequence = ringBuffer.next();
        try {
            PCData pcData = ringBuffer.get(sequence);
            pcData.setValue(byteBuffer.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        PcDataFactory pcDataFactory = new PcDataFactory();
        int bufferSize = 1024;
        Disruptor<PCData> pcDataDisruptor = new Disruptor<PCData>(pcDataFactory, bufferSize, executor, ProducerType.MULTI, new BlockingWaitStrategy());
        pcDataDisruptor.handleEventsWithWorkerPool(new Consumer(), new Consumer(), new Consumer(), new Consumer());
        pcDataDisruptor.start();

        RingBuffer<PCData> ringBuffer = pcDataDisruptor.getRingBuffer();
        Producer producer = new Producer(ringBuffer);
        ByteBuffer allocate = ByteBuffer.allocate(8);
        for (long i = 0; true; i++) {
            allocate.putLong(0, i);
            producer.pushData(allocate);
            Thread.sleep(1000);
            System.out.println("add data " + i);
        }
    }
}
