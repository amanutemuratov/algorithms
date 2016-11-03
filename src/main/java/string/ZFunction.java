package string;

/**
 * Created by AUtemuratov on 03.11.2016.
 */
public class ZFunction {

    int z[];

    public ZFunction(char arr[]) {
        buildZFunction(arr);
    }

    private void buildZFunction(char[] arr) {
        int n = arr.length;
        z = new int[n];

        int leftPosition = 0;
        int rightPosition = 0;

        for (int i = 1; i < n; i++) {
            if (i < rightPosition) {
                z[i] = Math.min(z[i - leftPosition], rightPosition - i + 1);
            }
            while (i + z[i] < n && arr[z[i]] == arr[i + z[i]]) {
                z[i]++;
            }
            if (z[i] > rightPosition) {
                leftPosition = i;
                rightPosition = i + z[i] - 1;
            }
        }
    }

    public int[] getZFunction() {
        return z;
    }
}
