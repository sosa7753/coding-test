import java.util.*;
class Solution {
    int[][] dp;
    int[][] matrix;
    public int solution(int[][] matrix_sizes) {
        int answer = 0;
        
        matrix = new int[matrix_sizes.length][matrix_sizes[0].length];
        for(int i=0; i<matrix_sizes.length; i++) {
            matrix[i] = matrix_sizes[i].clone();
        }
        
        // start ~ end 까지의 최소
        dp = new int[matrix_sizes.length][matrix_sizes.length];    
        for(int i=0; i<dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
                
        for(int i=0; i<matrix_sizes.length; i++) {
            dp[i][i] = 0;
        }
                        
        return solve(0, dp.length-1);
    }
    
    public int solve(int start, int end) {
        if(start == end) {
            return 0;
        }
        
        // 메모이제이션
        if(dp[start][end] != Integer.MAX_VALUE) {
            return dp[start][end];
        }
        
        // 행렬이 인접한다.
        if(end - start == 1) {
           return dp[start][end] = Math.min(dp[start][end], 
                                  matrix[start][0]*matrix[start][1]*matrix[end][1]);   
        }
        
        int result = Integer.MAX_VALUE;
        for(int i=start; i<end; i++) {
            int tmp = 0;
            tmp += solve(start, i);
            tmp += solve(i+1, end);     
            tmp += matrix[start][0] * matrix[i][1] * matrix[end][1];
            
            result = Math.min(result, tmp);           
        }
        
        return dp[start][end] = result;                            
    }
}