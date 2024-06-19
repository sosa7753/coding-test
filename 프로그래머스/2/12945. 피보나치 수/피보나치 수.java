class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        
        for(int i=2; i<dp.length; i++) {
            dp[i] = (dp[i-1]%1234567 + dp[i-2]%1234567)%1234567;
        }
        
        return dp[n];
    }
}