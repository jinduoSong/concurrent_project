package itmayi.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author songjd
 * @date 9:35 2019/8/5.
 */
public class Consumer implements WorkHandler<PCData> {

    public void onEvent(PCData pcData) {
        System.out.println(Thread.currentThread().getId() + ":Event: --" + pcData.getValue() * pcData.getValue() + "--");
    }
}
