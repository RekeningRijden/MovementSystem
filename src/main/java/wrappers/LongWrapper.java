package wrappers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 15-04-16.
 */
public class LongWrapper {

    private Long value;

    public LongWrapper(Long value) {
        this.value = value;
    }

    public LongWrapper() {
        // create empty LongWrapper
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public static List<LongWrapper> wrapLongs(List<Long> longs) {
        List<LongWrapper> wrapperList = new ArrayList<>();
        for (Long longValue : longs) {
            LongWrapper wrapper = new LongWrapper(longValue);
            wrapperList.add(wrapper);
        }
        return wrapperList;
    }

}
