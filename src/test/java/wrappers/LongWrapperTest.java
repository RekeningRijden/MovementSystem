/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrappers;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marijn
 */
public class LongWrapperTest {

    private Long long1;
    private Long long2;
    private Long long3;

    private LongWrapper longWrapper;

    public LongWrapperTest() {
    }

    @Before
    public void setUp() {
        long1 = Long.parseLong("1");
        long2 = Long.parseLong("2");
        long3 = Long.parseLong("3");

        longWrapper = new LongWrapper();
    }

    @Test
    public void testConstructor() {
        LongWrapper longWrapper = new LongWrapper(long1);
        assertSame(long1, longWrapper.getValue());
    }

    @Test
    public void testGetterAndSetter() {
        longWrapper.setValue(long1);
        assertSame(long1, longWrapper.getValue());
        longWrapper.setValue(long2);
        assertSame(long2, longWrapper.getValue());
    }

    @Test
    public void testWrapperList() {
        List<Long> longs = new ArrayList<>();
        longs.add(long1);
        longs.add(long2);
        longs.add(long3);
        List<LongWrapper> longWrappers = LongWrapper.wrapLongs(longs);
        assertSame(3, longWrappers.size());
        assertSame(long1, longWrappers.get(0).getValue());
    }
}
