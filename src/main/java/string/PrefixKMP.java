package string;

/**
 * Created by Aman on 1/9/2017.
 */
public class PrefixKMP {

    char [] c;
    public PrefixKMP(char [] c) {
        this.c = c;
    }

    public int[] buildPrefix() {
        int arr[] = new int[c.length];
        for (int i=1; i<c.length; i++) {
            int j = arr[i-1];
            while (j>0 && c[i]!=c[j]) {
                j = arr[j-1];
            }
            if (c[i]==c[j]) j++;
            arr[i] = j;
        }
        return arr;
    }
}
