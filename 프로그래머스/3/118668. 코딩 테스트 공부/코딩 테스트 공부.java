import java.util.*;
class Solution {
    public int solution(int alp, int cop, int[][] problems) {        
        int maxAl = alp;
        int maxCo = cop;
        
        for(int i=0; i<problems.length; i++) {
            maxAl = Math.max(maxAl, problems[i][0]);
            maxCo = Math.max(maxCo, problems[i][1]);
        }
        
        if(maxAl <= alp && maxCo <= cop) {
            return 0;
        }
        
        // 한쪽만 큰 값이면 보정하기 
        alp = Math.min(alp, maxAl);
        cop = Math.min(cop, maxCo);
              
        int[][] dp = new int[maxAl+1][maxCo+1];        
        for(int i=0; i<dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        dp[alp][cop] = 0;
               
        for(int i=alp; i<= maxAl; i++) {        
            for(int j=cop; j<= maxCo; j++) {
                // 알고력 올려주기
                if(i + 1 <= maxAl) {
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 1);
                }
                      
                // 코딩력 올려주기
                if(j + 1 <= maxCo) {
                    dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j] + 1);
                }
                
                for(int k=0; k<problems.length; k++) {   
                    if(problems[k][0] <= i && problems[k][1] <=j) { // 풀 수 있는 경우
                        int nextAl = Math.min(maxAl, i+problems[k][2]);
                        int nextCo = Math.min(maxCo, j+problems[k][3]);
                        dp[nextAl][nextCo] = Math.min(dp[nextAl][nextCo], 
                                                      dp[i][j] + problems[k][4]);
                    }                               
                }
            }
        }
        
        return dp[maxAl][maxCo];
    }
}