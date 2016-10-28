package dataStructure;

/**
 * Created by Aman on 10/27/2016.
 */
public class SegmentTree {

    private SegmentTree left;
    private SegmentTree right;
    private int value;
    private int leftBound, rightBound;

    public SegmentTree(int arr[], int leftBound, int rightBound) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        if (leftBound == rightBound) {
            value = arr[leftBound];
            return;
        }
        int mid = (leftBound + rightBound) / 2;
        left = new SegmentTree(arr, leftBound, mid);
        right = new SegmentTree(arr, mid + 1, rightBound);
        value = rangeValue();
    }

    public int getRangeValue(int l, int r) {
        if (rightBound < l || leftBound > r) return 0;
        if (l <= leftBound && rightBound <= r) return value;
        return left.getRangeValue(l, r) + right.getRangeValue(l, r);
    }

    public void update(int pos, int val) {
        if (leftBound == rightBound) {
            value = val;
            return;
        }

        int mid = (leftBound + rightBound) / 2;

        if (pos <= mid) left.update(pos, val);
        else right.update(pos, val);

        value = rangeValue();
    }

    private int getValue() {
        return value;
    }

    public int rangeValue() {
        return left.getValue() + right.getValue();
    }
}
