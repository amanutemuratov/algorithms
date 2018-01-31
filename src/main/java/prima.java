import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.Objects;

public class Main implements Runnable {

    int maxn = (int)5e3+111;
    long mod = 1743;
    int inf = (int)1e9+111;
    int n,m,k;
    double g[][] = new double[maxn][maxn];
    boolean used[] = new boolean[maxn];
    double min_e[] = new double[maxn];
    int sel_e[] = new int[maxn];

    void solve() throws Exception {
        n = in.nextInt();

        ArrayList<Pair> list = new ArrayList<>();
        for (int i=1; i<=n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            list.add(new Pair(x,y));
        }

        for (int i=0; i<list.size(); i++) {
            int x = list.get(i).x;
            int y = list.get(i).y;
            for (int j=i+1; j<list.size(); j++) {
                int xx = list.get(j).x;
                int yy = list.get(j).y;
                double dist = Point.distance((double)x,(double)y,(double)xx,(double)yy);
                g[i][j] = dist;
                g[j][i] = dist;
            }
        }

        double ans = 0.0;
        Arrays.fill(min_e, inf);
        Arrays.fill(sel_e, -1);
        min_e[0] = 0.0;
        for (int i=0; i<n; i++) {
            int v = -1;
            for (int j=0; j<n; j++) {
                if (!used[j] && (v==-1 || min_e[j]<min_e[v])) {
                    v = j;
                }
            }

            if (min_e[v]==inf) {
                out.println("NO MST");
                return;
            }

            used[v] = true;
            if (sel_e[v]!=-1) {
                ans+=g[v][sel_e[v]];
            }

            for (int to=0; to<n; to++) {
                if (g[v][to]<min_e[to]) {
                    min_e[to] = g[v][to];
                    sel_e[to] = v;
                }
            }
        }

        out.println(ans);
    }

    String fileInName = "unionday";
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
