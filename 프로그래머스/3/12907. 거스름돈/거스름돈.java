class Solution {
    long INF = 1_000_000_007L;
    public int solution(int n, int[] money) {
        long[] dp = new long[n+1];       
        for(int i=0; i<money.length; i++) {
            dp[money[i]] += 1;
            for(int j=1; j<=n; j++) {
                if(j >= money[i]) {
                    dp[j] = (dp[j] + dp[j-money[i]])%INF;
                }
            }
        }
        return (int)(dp[n]);
    }
}