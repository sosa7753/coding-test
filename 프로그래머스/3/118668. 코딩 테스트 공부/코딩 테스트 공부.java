import java.util.*;
class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAl = alp;
        int maxCo = cop;
        
        for(int[] problem : problems) {
            maxAl = Math.max(maxAl, problem[0]);
            maxCo = Math.max(maxCo, problem[1]);
        }
        
        if(maxAl == alp && maxCo == cop) {
            return 0;
        }
        
        alp = Math.min(alp, maxAl);
        cop = Math.min(cop, maxCo);
        
        int[][] dp = new int[maxAl + 1][maxCo + 1];
        for(int i=0; i<=maxAl; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        dp[alp][cop] = 0;
        
        for(int i=alp; i<=maxAl; i++) {
            for(int j=cop; j<=maxCo; j++) {
                if(i+1 <= maxAl) {
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 1);
                }
                
                if(j+1 <= maxCo) {
                    dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1);
                }
                
                for(int[] problem : problems) {
                    if(problem[0] <= i && problem[1] <= j) {
                        int nextAl = Math.min(maxAl, i+problem[2]);
                        int nextCo = Math.min(maxCo, j+problem[3]);
                        dp[nextAl][nextCo] = Math.min(
                        dp[nextAl][nextCo], dp[i][j] + problem[4]
                        );
                    }
                }
            }
        }
        return dp[maxAl][maxCo];
    }
}