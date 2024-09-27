class Solution {
    public int solution(int n, int[] money) {
        int[] dp = new int[n+1];
        
        for(int i=0; i<money.length; i++) {
            dp[money[i]] += 1; 
            for(int j=0; j<=n; j++) {
                if(j >= money[i]) {
                    dp[j] = (dp[j] + dp[j-money[i]])%1000000007;
                }
            }
        }
        
        return dp[n];
    }
}