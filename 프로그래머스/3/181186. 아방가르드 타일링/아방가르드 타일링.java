class Solution {
    long INF = 1000000007L;
    public int solution(int n) {       
        long[] dp = new long[100001];
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        dp[4] = 23;
        dp[5] = 62;
        dp[6] = 170;
            
        for(int i=7; i<=n; i++) {
            dp[i] = (dp[i-1]%INF + ((dp[i-2]%INF)*2)%INF + ((dp[i-3]%INF)*6)%INF + dp[i-4]%INF - dp[i-6]%INF + INF)%INF;
        }
        
        return (int)(dp[n]%INF);
    }
}