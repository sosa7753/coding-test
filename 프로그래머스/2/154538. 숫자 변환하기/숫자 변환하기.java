class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
        
        int[] dp = new int[y+1];
        for(int i=x; i<=y; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        
        dp[x] = 0;
        
        for(int i=x; i<=y; i++) {
            if(dp[i] == Integer.MAX_VALUE) {
                continue;
            }
            // n 더하기
            if(i+n <= y) {
                dp[i+n] = Math.min(dp[i+n], dp[i] + 1);
            }
            
            // x2 
            if(i*2 <= y) {
                dp[2*i] = Math.min(dp[2*i], dp[i] + 1);
            }
            
            // x3 
            if(i*3 <=y) {
                dp[3*i] = Math.min(dp[3*i], dp[i] + 1);
            }
        }
              
        if(dp[y] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[y];
    }
}