package dataStructure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Aman on 10/27/2016.
 */
public class SegmentTreeTest {

    private SegmentTree segmentTree;
    private int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    @Before
    public void tearDown() {
        segmentTree = new SegmentTree(arr, 0, arr.length - 1);
    }

    @Test
    public void getSumAllElements() throws Exception {
        int sum = 0;
        for (int val : arr) sum += val;
        int val = segmentTree.getRangeValue(0, arr.length - 1);
        assertEquals(val, sum);
    }

    @Test
    public void getSumAtSinglePosition() throws Exception {
        int pos = 3;
        int val = segmentTree.getRangeValue(pos, pos);
        assertEquals(val, arr[pos]);
    }

    @Test
    public void getSumCorners() throws Exception {
        int startPos = 0;
        int endPos = arr.length - 1;

        int startVal = segmentTree.getRangeValue(startPos, startPos);
        int endVal = segmentTree.getRangeValue(endPos, endPos);

        assertEquals(startVal, arr[startPos]);
        assertEquals(endVal, arr[endPos]);
    }

    @Test
    public void update() throws Exception {
        int pos = 0;
        int newVal = 99;
        segmentTree.update(pos, newVal);
        int afterUpdateValue = segmentTree.getRangeValue(pos, pos);
        assertEquals(afterUpdateValue, newVal);
    }
}