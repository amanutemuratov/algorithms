class Factorization {
    private HashMap<Integer, Integer> getFactorized(int x) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 2; i <= x; i++) {
            int cnt = 0;
            while (x % i == 0) {
                x /= i;
                cnt++;
            }
            if (cnt > 0) {
                map.put(i, cnt);
            }
        }
        return map;
    }
}
