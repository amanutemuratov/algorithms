package dataStructure;

public class SparseTable {

    private int sparseTable[][];
    private int powerOfTwo[];

    public SparseTable(int arr[]) {
        buildLogTable(arr);
        buildSparseTable(arr);
    }

    private void buildLogTable(int[] arr) {
        powerOfTwo = new int[arr.length + 1];
        for (int i = 2; i <= arr.length; i++) {
            powerOfTwo[i] = powerOfTwo[i / 2] + 1;
        }
    }

    private void buildSparseTable(int[] arr) {
        int n = arr.length;
        sparseTable = new int[powerOfTwo[n] + 1][n];
        for (int i = 0; i < n; i++) {
            sparseTable[0][i] = arr[i];
        }

        for (int k = 1; k <= powerOfTwo[n]; k++) {
            for (int j = 0; j + (1 << k) <= n; j++) {
                int x = sparseTable[k - 1][j];
                int y = sparseTable[k - 1][j + (1 << (k - 1))];
                sparseTable[k][j] = Math.min(x, y);
            }
        }
    }

    public int getMinInRange(int left, int right) {
        int dif = powerOfTwo[right - left];
        int x = sparseTable[dif][left];
        int y = sparseTable[dif][right - (1 << dif)];
        return Math.min(x, y);
    }
}