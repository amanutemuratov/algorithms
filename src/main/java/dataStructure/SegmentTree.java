package dataStructure;

/**
 * Created by Aman on 10/27/2016.
 */
public class SegmentTree {

    private SegmentTree left;
    private SegmentTree right;
    private int sum;
    private int tl, tr;

    public SegmentTree(int arr[]) {
        new SegmentTree(arr, 0, arr.length-1);
    }

    public SegmentTree(int arr[], int tl, int tr) {
        this.tl = tl;
        this.tr = tr;
        if (tl==tr) {
            sum = arr[tl];
            return;
        }
        int mid = (tl + tr) / 2;
        left = new SegmentTree(arr, tl, mid);
        right = new SegmentTree(arr, mid + 1, tr);
        sum = left.getSum() + right.getSum();
    }

    public int getSum() {
        return sum;
    }

    public int getRangeSum(int l, int r) {
        if (tl>=l && r<=tr) {
            return sum;
        }
        int leftSum = 0;
        if (left!=null) {
            leftSum = left.getSum();
        }

        int rightSum = 0;
        if (right!=null) {
            rightSum = right.getSum();
        }
        return leftSum + rightSum;
    }
}
