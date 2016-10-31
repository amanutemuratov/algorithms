package dataStructure;

/**
 * Created by Aman on 10/27/2016.
 */
public class SegmentTree {

    private SegmentTree leftNode;
    private SegmentTree rightNode;
    private int value;
    private int leftBound, rightBound;
    private int pushValue;

    public SegmentTree(int arr[], int leftBound, int rightBound) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        pushValue = 0;
        if (leftBound == rightBound) {
            value = arr[leftBound];
            return;
        }
        int mid = (leftBound + rightBound) / 2;
        leftNode = new SegmentTree(arr, leftBound, mid);
        rightNode = new SegmentTree(arr, mid + 1, rightBound);
        value = rangeValue();
    }

    public int getRangeValue(int l, int r) {
        if (rightBound < l || leftBound > r) return 0;
        if (l <= leftBound && rightBound <= r) return value;
        return leftNode.getRangeValue(l, r) + rightNode.getRangeValue(l, r);
    }

    public void update(int pos, int val) {
        if (leftBound == rightBound) {
            value = val;
            return;
        }

        int mid = (leftBound + rightBound) / 2;

        if (pos <= mid) leftNode.update(pos, val);
        else rightNode.update(pos, val);

        value = rangeValue();
    }

    public void update(int l, int r, int val) {
        push();
        if (rightBound < l || leftBound > r) return;
        if (l <= leftBound && rightBound <= r) {
            pushValue += val;
            push();
        } else {
            leftNode.update(l,r,val);
            rightNode.update(l,r,val);
            value = rangeValue();
        }
    }

    private void push() {
        value += pushValue*(rightBound-leftBound+1);
        if (leftBound!=rightBound) {
            leftNode.incrementValueBy(pushValue);
            rightNode.incrementValueBy(pushValue);
        }
        pushValue = 0;
    }

    private int getValue() {
        return value;
    }

    private void incrementValueBy(int val) {
        value+=val;
    }

    public int rangeValue() {
        return leftNode.getValue() + rightNode.getValue();
    }
}
