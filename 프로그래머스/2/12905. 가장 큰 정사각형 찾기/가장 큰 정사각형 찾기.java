class Solution{
    public int solution(int [][]board) {
        int n = board.length;
        int m = board[0].length;
        
        int[][] dp = new int[n+1][m+1];
        
        int answer = 0;
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(board[i-1][j-1] == 0) {
                    continue;
                }
                
                dp[i][j] = Math.min(dp[i-1][j-1], 
                           Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                answer = Math.max(answer, dp[i][j]);
            }
        }

        return answer * answer;
    }
}