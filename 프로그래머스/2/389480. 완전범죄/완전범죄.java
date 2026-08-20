import java.util.*;
class Solution {
    public int solution(int[][] info, int n, int m) {       
        // 2^40으로 모든 기준 보기는 힘듦 -> DP 방식
        int len = info.length;
        Arrays.sort(info, (x,y) -> (x[1] - y[1]));
        
        // index 0을 훔치면 1에 반영, .. len-1을 훔치면 len에 반영
        int[][] dp = new int[len+1][m]; // 인덱스 0의 물건을 훔칠 때 B의 j흔적에서 A 누적 최솟 값
        for(int i=1; i<=len; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
                     
        for(int i=0; i<len; i++) { // 인덱스 0의 물건을 훔칠 때,
            int a = info[i][0];
            int b = info[i][1];
            for(int j=0; j<m; j++) { // 현재 B의 흔적
                if(dp[i][j] == Integer.MAX_VALUE) continue;
                
                // A가 훔치는 경우
                if(dp[i][j] + a < n) {
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + a);
                }
                
                // B가 훔치는 경우
                if(j + b < m) {
                    dp[i+1][j+b] = Math.min(dp[i+1][j+b], dp[i][j]);
                }     
            }        
        }
        
        int answer = Integer.MAX_VALUE;
        for(int i=0; i<m; i++) {
            answer = Math.min(answer, dp[len][i]);
        }
        
        if(answer == Integer.MAX_VALUE) return -1;
        return answer;
    }
} 