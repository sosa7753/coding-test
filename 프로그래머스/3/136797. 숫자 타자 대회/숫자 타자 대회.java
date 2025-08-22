import java.util.*;
class Solution {
    int[][] w = new int[10][10]; // s -> e 로 가는 최소 가중치
    int[][][] dp;
    int n;
    public int solution(String numbers) {
        init();
        
        n = numbers.length();
        dp = new int[n][10][10]; // 누를 인덱스 , 왼손 위치 , 오른손 위치
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<10; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        
        return solve(numbers, 0, 4, 6);
    }
    
    public int solve(String numbers, int idx, int left, int right) {
        if(idx == n) {
            return 0;
        }
        
        if(dp[idx][left][right] != -1) {
            return dp[idx][left][right];
        }
        
        int next = numbers.charAt(idx) - '0';
        int value = Integer.MAX_VALUE;
        
        
        if(next != right) { // 왼손 이동
            value = Math.min(solve(numbers, idx+1, next, right) 
                             + w[left][next], value);
        }
         
        if(next != left) { // 오른쪽 이동
            value = Math.min(solve(numbers, idx+1, left, next) + w[right][next], value);
        }
        return dp[idx][left][right] = value;
    }
    
    public void init() {
        int[] start = new int[2];
        int[] end = new int[2];
        
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                if(i == j) {
                    w[i][j] = 1;
                    continue;
                }
                
                if(i == 0) {
                    start[0] = 3;
                    start[1] = 1;
                }else {
                    start[0] = (i-1)/3;
                    start[1] = (i-1)%3;
                }
                
                if(j == 0) {
                    end[0] = 3;
                    end[1] = 1;
                }else {
                    end[0] = (j-1)/3;
                    end[1] = (j-1)%3;
                }
                
                int[] moving = new int[2];
                moving[0] = Math.abs(start[0] - end[0]);
                moving[1] = Math.abs(start[1] - end[1]);
                
                int min = Math.min(moving[0], moving[1]); // 대각 횟수
                int max = Math.max(moving[0], moving[1]) - min; // 직진 횟수
                w[i][j] = min * 3 + max * 2;
            }
        }
    }
}