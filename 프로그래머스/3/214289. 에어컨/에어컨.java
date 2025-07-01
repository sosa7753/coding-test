import java.util.*;
class Solution {
    int MAX = 1000001;
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        
        t1 += 10; 
        t2 += 10; 
        int tmp = temperature + 10;
        
        int n = onboard.length;
        int[][] DP = new int[n][51]; // n 시간에 m 온도를 맞추는 최소 에너지
        
        for(int i=0; i<n; i++) {
            Arrays.fill(DP[i], MAX);
        }
        
        DP[0][tmp] = 0; // 초기 온도는 실외온도
        
        for(int i=0; i<n-1; i++) {
            for(int j=0; j<=50; j++) {
                if(onboard[i] == 1 && (j < t1 || t2 < j)) { // 탑승 중에 불만족
                    continue;
                }
                   
                // 에어컨 ON
                DP[i+1][j] = Math.min(DP[i+1][j], DP[i][j] + b); // 유지
                
                if(j > 0) { // 이전 온도 + a
                    DP[i+1][j-1] = Math.min(DP[i+1][j-1], DP[i][j] + a); // 다운
                }
                
                if(j < 50) {
                    DP[i+1][j+1] = Math.min(DP[i+1][j+1], DP[i][j] + a); // 업
                }
                
                // 에어컨 OFF
                if(tmp == j) {
                   DP[i+1][j] = Math.min(DP[i+1][j], DP[i][j]); // 유지 
                   
                }else if(tmp < j && j > 0) {
                    DP[i+1][j-1] = Math.min(DP[i+1][j-1], DP[i][j]); // 자동하락 
                }else if(tmp > j && j < 50) {
                    DP[i+1][j+1] = Math.min(DP[i+1][j+1], DP[i][j]); // 자동증가
                }                  
            }
        }
        
        int answer = MAX;
        for(int i=0; i<=50; i++) {
            if(onboard[n-1] == 1 && (i < t1 || i > t2)) {
                continue;
            }
            answer = Math.min(answer, DP[n-1][i]);
        }
          
        return answer;
    }
}