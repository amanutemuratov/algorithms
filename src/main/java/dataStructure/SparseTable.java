package dataStructure;

/**
 * Created by Aman on 11/1/2016.
 */
public class SparseTable {

    private int sparseTable[][];
    private int logPowerOfTwo[];

    public SparseTable(int arr[]) {
        int n = arr.length;
        logPowerOfTwo = new int[n + 1];
        buildLogTable(arr);

        sparseTable = new int[logPowerOfTwo[n] + 1][n];
        buildSparseTable(arr);
    }

    private void buildLogTable(int[] arr) {
        for (int i = 2; i <= arr.length; i++) {
            logPowerOfTwo[i] = logPowerOfTwo[i >> 1] + 1;
        }
    }

    private void buildSparseTable(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            sparseTable[0][i] = arr[i];
        }

        for (int k = 1; (1 << k) < n; k++) {
            for (int j = 0; j + (1 << k) <= n; j++) {
                int x = sparseTable[k - 1][j];
                int y = sparseTable[k - 1][j + (1 << k) - 1];
                sparseTable[k][j] = Math.min(x, y);
            }
        }
    }

    public int getMinInRange(int left, int right) {
        int dif = logPowerOfTwo[right - left];
        int x = sparseTable[dif][left];
        int y = sparseTable[dif][right - (1 << dif) + 1];
        return Math.min(x, y);
    }
}
