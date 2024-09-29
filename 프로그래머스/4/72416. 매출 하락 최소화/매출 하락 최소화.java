import java.util.*;
class Solution {
    List<List<Integer>> list = new ArrayList<>();
    int[][] dp;
    public int solution(int[] sales, int[][] links) {
        dp = new int[sales.length+1][2]; // 0은 참석안함. 1은 참석함. 
        
        for(int i=0; i<=sales.length; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<links.length; i++) {
            list.get(links[i][0]).add(links[i][1]);
        }
        
        DFS(1, sales);
        
        
        return Math.min(dp[1][0], dp[1][1]);
    }
    
    public void DFS(int node, int[] sales) {        
        dp[node][0] = 0; // 참석 안함.
        dp[node][1] = sales[node-1] ; // 참석
        
        // 리프 노드면 종료
        if(list.get(node).size() == 0) {
            return;
        }
                
        int extra = Integer.MAX_VALUE; // 현재 팀장의 팀원들 중 참여비용이 최소
        
        for(int child : list.get(node)) {
            DFS(child, sales);
            
            // child 참석 X가 이득인 경우 
            if(dp[child][0] < dp[child][1]) {
                dp[node][0] += dp[child][0]; // 팀장 x 팀원 x -> 전체팀원 중 1명 필요
                dp[node][1] += dp[child][0]; // 팀장 o 팀원 x 
                
                extra = Math.min(extra, dp[child][1] - dp[child][0]);
            }else { // child 참석 O가 이득인 경우
                dp[node][0] += dp[child][1]; // 팀원 x 팀장 o 
                dp[node][1] += dp[child][1]; // 팀원 o 팀장 o
                
                extra = 0; // 한 명이라도 참여 했으면 0;
            }            
        }
        dp[node][0] += extra;
    }    
}