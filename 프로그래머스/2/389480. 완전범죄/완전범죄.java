import java.util.*;
class Solution {
    int sumA = 0;
    int sumB = 0;
    int[][] dp;
    public int solution(int[][] info, int n, int m) {   
        for(int i=0; i<info.length; i++) {
            sumA += info[i][0];
            sumB += info[i][1];
        }
        Arrays.sort(info, (x,y) -> (x[0] - y[0]));       
        dp = new int[info.length+1][n];
        
        for(int i=1; i<=info.length; i++) {
            for(int j=1; j<n; j++) {
                if(j-info[i-1][0] < 0) {
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-info[i-1][0]] + info[i-1][1]);
                }
            }
        }
                
        int L = 0;
        int R = n-1;
        
        while(L<=R) {
            int mid = (L + R)/2;
            if(check(mid, m)) {
                R = mid-1;
            }else {
                L = mid+1;
            }
        }
          
        if(L == n) {
            return -1;
        }else {
            return L;
        }
    }
    public boolean check(int mid, int m) { // A가 mid 흔적으로 훔칠 수 있는지 체크 
        int max = dp[dp.length-1][mid];
        
        if(m <= sumB - max) {
            return false;
        }
        return true;       
    }
}