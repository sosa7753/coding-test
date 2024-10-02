class Solution {
    int[][] dp;
    public int solution(int m, int n, int[][] puddles) {
        dp = new int[n+1][m+1];        
        for(int i=0; i<puddles.length; i++) {
            dp[puddles[i][1]][puddles[i][0]] = -1;
        }
        
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(i==1 && j==1) {
                    dp[i][j] = 1;
                    continue;
                }
                if(dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }
                
                dp[i][j] = (int)(((long)dp[i-1][j] + (long)dp[i][j-1])%1000000007);
            }
        }
        
        return dp[n][m];
    }
}