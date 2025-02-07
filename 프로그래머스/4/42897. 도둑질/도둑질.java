class Solution {
    public int solution(int[] money) {        
        int[][] dp = new int[2][money.length];  // 0은 첫집을 털음. 1은 첫집을 안털음.
        
        dp[0][0] = money[0];
        dp[0][1] = money[0];
        
        dp[1][1] = money[1];
    
        for(int i=2; i<money.length; i++) {
            dp[0][i] = Math.max(dp[0][i-1], dp[0][i-2] + money[i]);
            dp[1][i] = Math.max(dp[1][i-1], dp[1][i-2] + money[i]);
        }
        return Math.max(dp[0][money.length-2], dp[1][money.length-1]);
    }
}