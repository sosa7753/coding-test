import java.util.*;
class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        
        int maxAl = 0;
        int maxCo = 0;
        for(int[] problem : problems) {
            maxAl = Math.max(maxAl, problem[0]);
            maxCo = Math.max(maxCo, problem[1]);
        }
        
        int a = Math.min(alp, maxAl);
        int c = Math.min(cop, maxCo);
        
        int[][] dp = new int[maxAl+1][maxCo+1];
        for(int i=0; i<dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        dp[a][c] = 0;
        for(int i=0; i<=maxAl; i++) {
            for(int j=0; j<=maxCo; j++) {
                if(dp[i][j] == Integer.MAX_VALUE) continue;
                
                for(int[] problem : problems) {
                    if(i >= problem[0] && j>=problem[1]) {
                        int al = Math.min(maxAl, i+problem[2]);
                        int co = Math.min(maxCo, j+problem[3]);
                        if(dp[al][co] > dp[i][j] + problem[4]) {
                            dp[al][co] = dp[i][j] + problem[4];
                        }
                    }
                }
                
                // 알고력 올리기
                for(int k=1; k<=maxAl; k++) {
                    if(i+k <= maxAl) {
                        if(dp[i+k][j] > dp[i][j] + k) {
                            dp[i+k][j] = dp[i][j] + k;
                        }
                    }
                }
                
                for(int k=1; k<=maxCo; k++) {
                    if(j+k <= maxCo) {
                        if(dp[i][j+k] > dp[i][j] + k) {
                            dp[i][j+k] = dp[i][j] + k;
                        }
                    }
                }
            }
        }
        
        return dp[maxAl][maxCo];
    }
}