import java.util.*;
class Solution {
    List<List<Integer>> list = new ArrayList<>();
    int[][] dp;
    public int solution(int[] sales, int[][] links) {
        int n = sales.length;      
        dp = new int[n+1][2];
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int[] link : links) {
            list.get(link[0]).add(link[1]);
        }
               
        DFS(1, sales);
        return Math.min(dp[1][0], dp[1][1]);
    }
    
    public void DFS(int now, int[] sales) {
        dp[now][0] = 0; // 참석 안함
        dp[now][1] = sales[now-1]; // 참석함
        
        if(list.get(now).size() == 0) return;
        
        int extra = Integer.MAX_VALUE;
        for(int child : list.get(now)) {   
            DFS(child, sales);      
            
            // child 참석X가 이득인 경우
            if(dp[child][0] < dp[child][1]) {
                dp[now][0] += dp[child][0];
                dp[now][1] += dp[child][0];
                
                extra = Math.min(extra, dp[child][1] - dp[child][0]);
            }else {
                dp[now][0] += dp[child][1];
                dp[now][1] += dp[child][1];
                
                extra = 0;
            }          
        }
        
        dp[now][0] += extra;
    }
}