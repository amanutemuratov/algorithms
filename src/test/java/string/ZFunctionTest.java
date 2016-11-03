package string;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by AUtemuratov on 03.11.2016.
 */
public class ZFunctionTest {

    @Test
    public void getZFunction1() throws Exception {
        String s = "abacaba";
        ZFunction zFunc = new ZFunction(s.toCharArray());

        int[] naiveSolution = getNaiveSolution(s.toCharArray());
        int[] zFunction = zFunc.getZFunction();

        assertArrayEquals(naiveSolution, zFunction);
    }

    @Test
    public void getZFunction2() throws Exception {
        String s = "test";
        ZFunction zFunc = new ZFunction(s.toCharArray());

        int[] naiveSolution = getNaiveSolution(s.toCharArray());
        int[] zFunction = zFunc.getZFunction();

        assertArrayEquals(naiveSolution, zFunction);
    }

    @Test
    public void getZFunctionOneChar() throws Exception {
        String s = "a";
        ZFunction zFunc = new ZFunction(s.toCharArray());

        int[] naiveSolution = getNaiveSolution(s.toCharArray());
        int[] zFunction = zFunc.getZFunction();

        assertArrayEquals(naiveSolution, zFunction);
    }

    @Test
    public void getZFunctionNumbers() throws Exception {
        String s = "12345566712123";
        ZFunction zFunc = new ZFunction(s.toCharArray());

        int[] naiveSolution = getNaiveSolution(s.toCharArray());
        int[] zFunction = zFunc.getZFunction();

        assertArrayEquals(naiveSolution, zFunction);
    }

    public int [] getNaiveSolution(char arr[]) {
        int n = arr.length;
        int z[] = new int[n];
        for (int i = 1; i< n; i++) {
            while (i+z[i]<n && arr[i+z[i]]==arr[z[i]]) {
                z[i]++;
            }
        }
        return z;
    }

}