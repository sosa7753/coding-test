class Solution {
    int solution(int[][] land) {
        int n = land.length;
        int[][] dp = new int[n][4];
        dp[0][0] = land[0][0];
        dp[0][1] = land[0][1];
        dp[0][2] = land[0][2];
        dp[0][3] = land[0][3];
        
        for(int i=1; i<n; i++) {
            for(int j=0; j<4; j++) {
                dp[i][j] = Math.max(dp[i-1][(j+1)%4],
                                    Math.max(dp[i-1][(j+2)%4], dp[i-1][(j+3)%4])) + land[i][j];
            }
        }
        
        int answer = 0;
        for(int i=0; i<4; i++) {
            answer = Math.max(answer, dp[n-1][i]);
        }
        
        return answer;
    }
}