package dataStructure;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Aman on 11/1/2016.
 */
public class SparseTableTest {

    int arr[] = {1, 2, 3, 4, 5, 6, 7};
    SparseTable sparseTable;

    @Before
    public void tearDown() {
        sparseTable = new SparseTable(arr);
    }

    @Test
    public void getMinInRangeFull() throws Exception {
        //given
        int left = 0;
        int right = arr.length;
        int expected = getExpectedMinimum(left, right);

        //when
        int actual = sparseTable.getMinInRange(left, right);

        //then
        assertEquals(expected, actual);
    }

    private int getExpectedMinimum(int left, int right) {
        int expected = Integer.MAX_VALUE;
        for (int i = left; i < right; i++) {
            expected = Math.min(expected, arr[i]);
        }
        return expected;
    }

    @Test
    public void getMinInRangeZeroToThree() throws Exception {
        //given
        int left = 0;
        int right = 3;
        int expected = getExpectedMinimum(left, right);

        //when
        int actual = sparseTable.getMinInRange(left, right);

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void getMinInRangePoint() throws Exception {
        //given
        int left = 3;
        int right = 4;
        int expected = getExpectedMinimum(left, right);

        //when
        int actual = sparseTable.getMinInRange(left, right);

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void getMinInRangeLeftCorner() throws Exception {
        //given
        int left = 0;
        int right = 1;
        int expected = getExpectedMinimum(left, right);

        //when
        int actual = sparseTable.getMinInRange(left, right);

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void getMinInRangeRightCorner() throws Exception {
        //given
        int left = arr.length-1;
        int right = arr.length;
        int expected = getExpectedMinimum(left, right);

        //when
        int actual = sparseTable.getMinInRange(left, right);

        //then
        assertEquals(expected, actual);
    }

}