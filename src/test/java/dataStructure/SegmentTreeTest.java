package dataStructure;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aman on 10/27/2016.
 */
public class SegmentTreeTest {

    @Test
    public void getSum() throws Exception {
        int arr[] = {1,1,1,1,1,1,1};
        SegmentTree segmentTree = new SegmentTree(arr);
        int val = segmentTree.getRangeSum(0,3);
        assertEquals(val, 1);
    }

    @Test
    public void getSum1() throws Exception {

    }

}