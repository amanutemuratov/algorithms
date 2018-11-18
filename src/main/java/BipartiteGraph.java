import java.io.*;
import java.util.*;
// http://codeforces.com/problemsets/acmsguru/problem/99999/172 acmsguru - 172 Exam

public class Main implements Runnable {

    int maxn = (int)2e2+11;
    int inf = (int)1e9;
    long mod = (long)1e9+7;
    int n,m,k;
    char color[] = new char[333];
    int[][] matrix = new int[333][333];
    boolean[] used = new boolean[333];

    void solve() throws Exception {
        n = in.iInt();
        m = in.iInt();

        for (int i=1; i<=m; i++) {
            int left = in.iInt();
            int right = in.iInt();
            matrix[left][right] = 1;
            matrix[right][left] = 1;
        }

        boolean isOk = true;
        for (int i=1; i<=n; i++) {
            if (!used[i]) {
                isOk &= dfs(i, '1');
            }
        }

        if (isOk) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i=1; i<=n; i++) {
                if (color[i]=='1') list.add(i);
            }
            out.println("yes");
            out.println(list.size());
            for (int val : list) {
                out.print(val + " ");
            }
        } else {
            out.println("no");
        }
    }

    private boolean dfs(int v, char c) {
        used[v] = true;
        color[v] = c;

        boolean isPossible = true;
        for (int to=1; to<=n; to++) {
            if (matrix[v][to]==1) {
                if (used[to] && color[to]==color[v]) {
                    isPossible = false;
                } else if (used[to]){

                } else {
                    char next = (c=='1')?'0':'1';
                    isPossible &= dfs(to, next);
                }
            }
        }

        return isPossible;
    }

    class Pair {
        int x,y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    String fileInName = "";

    static Throwable throwable;
    public static void main (String [] args) throws Throwable {
        Thread thread = new Thread(null, new Main(), "", (1 << 26));
        thread.start();
        thread.join();
        thread.run();
        if (throwable != null)
            throw throwable;
    }

    FastReader in;
    PrintWriter out;

    public void run() {

        try {
            if (!fileInName.isEmpty()) {
                in = new FastReader(new BufferedReader(new FileReader(fileInName+".in")));
                out = new PrintWriter(new BufferedWriter(new FileWriter(fileInName+".out")));
            } else {
                in = new FastReader(new BufferedReader(new InputStreamReader(System.in)));
                out = new PrintWriter(System.out);
            }

            solve();
        } catch(Exception e) {
            throwable = e;
        } finally {
            out.close();
        }

    }

    class FastReader {
        BufferedReader bf;
        StringTokenizer tk = null;

        public FastReader(BufferedReader bf) {
            this.bf = bf;
        }

        public Integer iInt() throws Exception {
            return Integer.parseInt(sString());
        }

        public Long lLong() throws Exception {
            return Long.parseLong(sString());
        }

        public Double dDouble() throws Exception {
            return Double.parseDouble(sString());
        }

        public String sString () throws Exception {
            if (tk==null || !tk.hasMoreTokens()) {
                tk = new StringTokenizer(bf.readLine());
            }
            if (!tk.hasMoreTokens()) return sString();
            else
                return tk.nextToken();
        }
    }
}
