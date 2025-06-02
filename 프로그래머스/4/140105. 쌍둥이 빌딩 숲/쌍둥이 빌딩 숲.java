class Solution {
    long MOD = 1000000007L;
    public int solution(int n, int count) {
        int answer = 0;
        
        long[][] dp = new long[n+1][count+1]; // 빌딩 종류, 보이는 수 
        dp[1][1] = 1;
        
        for(int i=2; i<=n; i++) { // 빌딩 종류
            for(int j=1; j<=count; j++) { // 보이는 수
                dp[i][j] = (dp[i-1][j] * (i-1) * 2 + dp[i-1][j-1])%MOD;
            }
        }
        
        return (int)dp[n][count];
    }
}