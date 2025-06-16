import java.util.*;
class Solution {
    int[][] dp;
    int[][] matrix;
    int n;
    public int solution(int[][] matrix_sizes) {
        matrix = matrix_sizes;
        n = matrix_sizes.length;
        dp = new int[n][n];
                
        for(int i=0; i<dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        for(int i=0; i<n; i++) {
            dp[i][i] = 0;
        }
        
        return DP(0, n-1);
    }
    
    public int DP(int start, int end) {
        if(start == end) {
            return 0;
        }
        
        if(dp[start][end] != Integer.MAX_VALUE) {
            return dp[start][end];
        }
        
        if(end - start == 1) {
            dp[start][end] = Math.min(dp[start][end], 
                                     matrix[start][0] * 
                                     matrix[start][1] *
                                     matrix[end][1]);
            return dp[start][end];
        }
        
        int result = Integer.MAX_VALUE;
        for(int i=start; i<end; i++) {
            int tmp = 0;
            tmp += DP(start, i);
            tmp += DP(i+1, end);
            tmp += matrix[start][0] * matrix[i][1] * matrix[end][1];
            
            result = Math.min(result, tmp);
        }
        
        return dp[start][end] = result;
    }
}