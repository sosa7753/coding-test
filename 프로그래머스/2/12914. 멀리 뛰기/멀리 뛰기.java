class Solution {
    long MOD = 1234567;
    long[] dp;
    public long solution(int n) {
        if(n == 1) {
            return 1;
        }
        dp = new long[n+1];
        dp[1] = 1;
        dp[2] = 2;
        return solve(n);
    }
    
    public long solve(int now) {
        if(dp[now] !=0){
            return dp[now];
        }
                
        if(now - 1 >= 0) {
            dp[now] += solve(now-1);
        }
        
        if(now - 2 >= 0) {
            dp[now] += solve(now-2);
        }
        return dp[now] = dp[now]%MOD;
    }
}