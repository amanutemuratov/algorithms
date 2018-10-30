public class PrimeResheto {
  private boolean[] getPrimesResheto(int n) {
        boolean isPrime[] = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        for (int i=2; i*i<n; i++) {
            if (isPrime[i]) {
                for (int j=i*i; j<n; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
  }
}
