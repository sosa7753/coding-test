import java.util.*;
class Solution {
    List<List<Integer>> list = new ArrayList<>();
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log){
        for(int i=0; i<=n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i=0; i<m; i++) {
            list.get(edge_list[i][0]).add(edge_list[i][1]);
            list.get(edge_list[i][1]).add(edge_list[i][0]);
        }
        
        for(int i=1; i<=n; i++) {
            list.get(i).add(i);
        }
        
        int[][] dp = new int[k][n+1]; // 현재 시간의 해당 거점까지 최소 수정횟수
        for(int i=0; i<k; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        dp[0][gps_log[0]] = 0;
        
        // 다음 시간대 작성
        for(int i=1; i<k; i++) { // 시간대
            for(int j=1; j<=n; j++) { // 거점
                if(dp[i-1][j] == Integer.MAX_VALUE) {
                    continue;
                }
                
                for(int next : list.get(j)) {
                    int w = (next == gps_log[i]) ? 0 : 1;
                    dp[i][next] = Math.min(dp[i][next], dp[i-1][j] + w);
                }
            }
        }
        
        int result = dp[k-1][gps_log[k-1]];
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}