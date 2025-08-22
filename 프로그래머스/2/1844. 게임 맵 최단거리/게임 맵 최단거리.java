import java.util.*;
class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int n,m;
    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        
        if(n == 1 && m == 1) {
            return 0;
        }
            
        return BFS(maps);
    }
    
    public int BFS(int[][] maps) {
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0,1});
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0]; int c = now[1]; int w = now[2];
            
            if(r == n-1 && c == m-1) {
                return w;
            }
            
            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if(nr < 0 || nr > n-1 || nc < 0 || nc > m-1) {
                    continue;
                }
                
                if(maps[nr][nc] == 0) {
                    continue;
                }
                
                if(visited[nr][nc]) {
                    continue;
                }
    
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc, w+1});
            }
        }
        return -1;
    }
}