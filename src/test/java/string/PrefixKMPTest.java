package string;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Aman on 1/9/2017.
 */
public class PrefixKMPTest {

    @Test
    public void buildPrefix() throws Exception {
        String s = "abacaba";
        PrefixKMP kmp = new PrefixKMP(s.toCharArray());
        int pref[] = kmp.buildPrefix();
        int naive[] = naiveSolution(s);
        System.out.println(Arrays.toString(pref));
        System.out.println(Arrays.toString(naive));
        assertArrayEquals(pref, naive);
    }


    @Test
    public void buildPrefix2() throws Exception {
        String s = "abccabaaded";
        PrefixKMP kmp = new PrefixKMP(s.toCharArray());
        int pref[] = kmp.buildPrefix();
        int naive[] = naiveSolution(s);
        System.out.println(Arrays.toString(pref));
        System.out.println(Arrays.toString(naive));
        assertArrayEquals(pref, naive);
    }

    private int[] naiveSolution(String s) {
        int arr[] = new int[s.length()];
        for (int i=0; i<s.length(); i++) {
            for (int j=0; j<=i; j++) {
                String left = s.substring(0,j);
                String right = s.substring(i-j+1,i+1);
                if (left.equals(right)) {
                    arr[i] = j;
                }
            }
        }
        return arr;
    }
}