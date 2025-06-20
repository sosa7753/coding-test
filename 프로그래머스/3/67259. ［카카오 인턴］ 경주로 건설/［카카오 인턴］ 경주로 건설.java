import java.util.*;
class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int[][] map;
    int n;
    int answer = Integer.MAX_VALUE;
    public int solution(int[][] board) {
        n = board.length;
        map = board;           
        return BFS(0,0);
    }
    
    public int BFS(int row, int col) {
        int result = Integer.MAX_VALUE;
        int[][] route = new int[n][n];
        route[0][0] = 0;
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col, 1, 0}); // 행,열,방향,비용
        q.add(new int[]{row, col, 2, 0});
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0];
            int c = now[1];
            int dir = now[2];
            int cost = now[3];
            
            if(r == n-1 && c == n-1) {
                result = Math.min(result, cost);     
                continue;
            }
            
            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if(nr < 0 || nr > n-1 || nc < 0 || nc > n-1) {
                    continue;
                }
                
                if((nr == 0 && nc == 0) || map[nr][nc] == 1) {
                    continue;
                }
                
                int w = ((r == 0 && c == 0) || dir == i) ? 100 : 600;
                if(route[nr][nc] == 0 || route[nr][nc] + 500 > cost + w) {
                    route[nr][nc] = cost + w;
                    q.add(new int[]{nr, nc, i, route[nr][nc]});
                }               
            }
        }
        return result;
    }
}