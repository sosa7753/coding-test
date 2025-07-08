import java.util.*;
class Solution {
    public int solution(String[] strs, String t) {
        
        int n = t.length();
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        dp[0] = 0;
        
        for(int i=1; i<=n; i++) {
            for(String str : strs) {
                int len = str.length();
                if(i >= len && t.substring(i - len, i).equals(str)) {
                    if(dp[i-len] != Integer.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], dp[i-len] + 1);
                    } 
                }
            }
        }

        return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    }
}