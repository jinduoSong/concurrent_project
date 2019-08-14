package itmayi.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author songjd
 * @date 9:53 2019/8/5.
 */
public class PcDataFactory implements EventFactory<PCData> {
    public PCData newInstance() {
        return new PCData();
    }
}
