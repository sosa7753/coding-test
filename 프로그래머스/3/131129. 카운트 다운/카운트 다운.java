import java.util.*;
class Solution {
    public int[] solution(int target) {
        int[] dart = new int[target+1]; // 최소 다트수
        int[] dp = new int[target+1]; // 최대 싱글+불 수 
        
        Arrays.fill(dart, Integer.MAX_VALUE);
        dart[0] = 0;
        
        for(int i=1; i<=target; i++) {
            if(i >= 50) {
                if(dart[i] > dart[i-50] + 1) {
                    dart[i] = dart[i-50] + 1;
                    dp[i] = dp[i-50] + 1;
                }else if(dart[i] == dart[i-50] + 1) {
                    dp[i] = Math.max(dp[i], dp[i-50] + 1);
                }           
            }
            
            for(int j=1; j<=20; j++) {
                if(i >= 3 * j) {
                    if(dart[i] > dart[i-3*j] + 1) {
                        dart[i] = dart[i-3*j] + 1;
                        dp[i] = dp[i-3*j];
                    }
                }
                
                if(i >= 2 * j) {
                    if(dart[i] > dart[i-2*j] + 1) {
                        dart[i] = dart[i-2*j] + 1;
                        dp[i] = dp[i-2*j];
                    }
                }
                
                if(i >= j) {
                    if(dart[i] > dart[i-j] + 1) {
                        dart[i] = dart[i-j] + 1;
                        dp[i] = dp[i-j] + 1;
                    }else if(dart[i] == dart[i-j] + 1) {
                        dp[i] = Math.max(dp[i], dp[i-j] + 1);
                    }
                }
            }    
        }
        
        int[] answer = new int[2];
        answer[0] = dart[target];
        answer[1] = dp[target];
        return answer;
    }
}