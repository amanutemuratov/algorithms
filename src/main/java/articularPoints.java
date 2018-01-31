import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.Objects;

public class Main implements Runnable {

    int maxn = (int)2e4+111;
    long mod = 1743;
    int inf = (int)1e9+111;
    int n,m,k;
    ArrayList<Integer> g[] = new ArrayList[maxn];
    boolean used[] = new boolean[maxn];
    int tin[] = new int[maxn];
    int fup[] = new int[maxn];
    int timer = 0;
    TreeSet<Integer> articulation = new TreeSet<>();

    void solve() throws Exception {
        n = in.nextInt();
        m = in.nextInt();
        for (int i=1; i<=n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i=1; i<=m; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            g[from].add(to);
            g[to].add(from);
        }

        for (int i=1; i<=n; i++) {
            if (!used[i]) {
                findArticulationPoint(i, -1);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(articulation.size() + "\n");
        for (int val : articulation) {
            sb.append(val + "\n");
        }

        out.println(sb.toString());
    }

    private void findArticulationPoint(int v, int p) {
        used[v] = true;
        tin[v] = fup[v] = timer++;
        int children = 0;
        for (int to : g[v]) {
            if (to==p) continue;
            if (used[to]) {
                fup[v] = Math.min(fup[v], tin[to]);
            } else {
                findArticulationPoint(to, v);
                fup[v] = Math.min(fup[v], fup[to]);
                if (fup[to]>=tin[v] && p!=-1) {
                    articulation.add(v);
                }
                children++;
            }
        }

        if (p==-1 && children>1) {
            articulation.add(v);
        }

    }

    String fileInName = "points";
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
