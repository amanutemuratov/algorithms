import java.io.*;
import java.util.*;
import java.util.Objects;

public class Main implements Runnable {

    int maxn = (int)1e5+111;
    long mod = 1743;
    int inf = (int)1e9+111;
    int n,m,k;
    int a[] = new int[maxn];
    ArrayList<Integer> g[] = new ArrayList[maxn];
    int P[][] = new int[20][maxn];
    int level[] = new int[maxn];

    void solve() throws Exception {
        n = in.nextInt();
        for (int i=0; i<maxn; i++) {
            g[i] = new ArrayList<>();
        }

        int root = -1;
        for (int i=1; i<=n; i++) {
            int from = in.nextInt();
            int to = i;
            if (from==0) {
                root = from;
                g[from].add(to);
            } else {
                g[from].add(to);
                g[to].add(from);
            }


        }

        dfs(root, 0, 0);
        preCalc();
        int q = in.nextInt();
        for (int i=1; i<=q; i++) {
            int left = in.nextInt();
            int right = in.nextInt();
            int parent = getLCA(left, right);
            if (parent==left) {
                out.println(1);
            } else {
                out.println(0);
            }
        }

    }

    private int getLCA(int x, int y) {
        for (int i=19; i>=0; i--) {
            if (level[P[i][y]] >= level[x]) y = P[i][y];
            if (level[P[i][x]] >= level[y]) x = P[i][x];
        }
        if (x==y) return x;
        for (int i=19; i>=0; i--) {
            if (P[i][x] != P[i][y]) {
                x = P[i][x];
                y = P[i][y];
            }
        }
        return P[0][x];
    }

    private void preCalc() {
        for (int j=1; j<20; j++) {
            for (int i=1; i<=n; i++) {
                P[j][i] = P[j-1][P[j-1][i]];
            }
        }
    }

    private void dfs(int v, int p, int degree) {
        level[v] = degree;
        P[0][v] = p;
        for (int i=0; i<g[v].size(); i++) {
            int to = g[v].get(i);
            if (to==p) continue;
            dfs(to,v,degree+1);
        }
    }



    String fileInName = "ancestor";
    boolean file = true;
    boolean isAcmp = false;

    class Pair implements Comparable<Pair> {
        int x;
        int y;

        public Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }



        @Override
        public int hashCode() {

            return Objects.hash(x, y);
        }

        @Override
        public int compareTo(Pair p) {
            if (this.x>p.x) {
                return 1;
            } else if(this.x == p.x) {
                if (this.y < p.y) {
                    return 1;
                } else if (this.y == p.y) return 0;
                else return -1;
            } else {
                return -1;
            }
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }



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
        String fileIn = "absum.in";
        String fileOut = "absum.out";

        try {
            if (isAcmp) {
                if (file) {
                    in = new FastReader(new BufferedReader(new FileReader(fileIn)));
                    out = new PrintWriter (fileOut);
                } else {
                    in = new FastReader(new BufferedReader(new InputStreamReader(System.in)));
                    out = new PrintWriter(System.out);
                }
            } else if (file) {
                in = new FastReader(new BufferedReader(new FileReader(fileInName+".in")));
                out = new PrintWriter (fileInName + ".out");
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
}

class FastReader {
    BufferedReader bf;
    StringTokenizer tk = null;

    public FastReader(BufferedReader bf) {
        this.bf = bf;
    }

    public String nextToken () throws Exception {
        if (tk==null || !tk.hasMoreTokens()) {
            tk = new StringTokenizer(bf.readLine());
        }
        if (!tk.hasMoreTokens()) return nextToken();
        else
            return tk.nextToken();
    }

    public int nextInt() throws Exception {
        return Integer.parseInt(nextToken());
    }

    public long nextLong() throws Exception {
        return Long.parseLong(nextToken());
    }

    public double nextDouble() throws Exception {
        return Double.parseDouble(nextToken());
    }
}
