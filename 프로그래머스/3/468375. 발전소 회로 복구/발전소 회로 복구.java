import java.util.*;
class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int k,m,n;
    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        k = panels.length;
        n = grid.length;
        m = grid[0].length();
        
        int[] pre = new int[k+1]; // i번 패널의 선행 패널(1), 비트는 i번 패널이 i-1(0)
        for(int[] seq : seqs) {
            pre[seq[1]-1] |= (1 << (seq[0]-1));
        }
        
        // 엘베 위치 찾기
        int er = -1; int ec = -1;
        boolean find = false;
        for(int i=0; i<n; i++) {
            if(find) break;
            for(int j=0; j<m; j++) {
                if(grid[i].charAt(j) == '@') {
                    er = i;
                    ec = j;
                    find = true;
                    break;
                } 
            }
        }
        
        int[] dist = new int[k+1]; // i번 패널 - 엘베 거리(1)
        for(int i=1; i<=k; i++) {
            dist[i] = BFS(grid, panels[i-1][1]-1, panels[i-1][2]-1, er, ec);
        }
        
        int[][] pairDist = new int[k+1][k+1]; // i번 패널에서 j번 패널까지 거리(1);
        for(int i=1; i<=k; i++) {
            for(int j=1; j<=k; j++) {
                if(i == j) continue;
                if(panels[i-1][0] == panels[j-1][0]) { // 같은 층
                    pairDist[i][j] = BFS(grid, panels[i-1][1]-1, panels[i-1][2]-1,
                                        panels[j-1][1]-1, panels[j-1][2]-1);
                }else {
                    pairDist[i][j] = dist[i] + dist[j] + 
                        Math.abs(panels[i-1][0] - panels[j-1][0]);
                }
            }
        }
               
        int[][] dp = new int[1<<k][k+1]; // 현재 상태에서 해당 패널까지의 거리(1), 비트는 i번 패널이 i-1(0)
        for(int i=0; i<dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        for(int i=0; i<(1<<k); i++) { // dp 방문 상태 값
            for(int j=0; j<=k; j++) { // dp 현재 패널 위치 
                if(dp[i][j] == Integer.MAX_VALUE) continue;
                
                for(int next=1; next<=k; next++) { // dp 다음 패널 위치
                    if((i & (1<<(next-1))) != 0) continue; // 이미 방문한 패널
                    if((i & pre[next-1]) != pre[next-1]) continue; // 선행 조건
                    
                    int cost = (j == 0) ? pairDist[1][next] : pairDist[j][next]; // 시작점 출발           
                    int nVisited = i | (1<<(next-1));
                    int nCost = dp[i][j] + cost;
                    
                    dp[nVisited][next] = Math.min(dp[nVisited][next], nCost);
                    
                }
            }
        }
        
        int fullVisited = (1<<k)-1;
        int answer = Integer.MAX_VALUE;
        for(int i=1; i<=k; i++) {
            answer = Math.min(answer, dp[fullVisited][i]);
        }
        
        return answer;
    }
    
    public int BFS(String[] grid, int sr, int sc, int er, int ec) {
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc, 0});
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0]; int c = now[1]; int w = now[2];
            if(r == er && c == ec) return w;
            
            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr < 0 || nr > n-1 || nc < 0 || nc > m-1) continue;
                if(grid[nr].charAt(nc) == '#') continue;
                if(!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc, w+1});
                }
            }
        }
        return 0;
    }
}