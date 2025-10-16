import java.util.*;
class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int answer = 0;
    int height;
    int n;
    int[][] land;
    boolean[][] visited;
    PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[2]-y[2]));
    public int solution(int[][] land, int height) {
        this.n = land.length;
        this.height = height;
        this.land = land;
        visited = new boolean[n][n];
        
        pq.offer(new int[]{0,0,0});
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            int r = now[0]; int c = now[1]; int h = now[2];
            
            if(visited[r][c]) continue;
            
            visited[r][c] = true;
            answer += h;
            
            BFS(r, c);
            
        }
        
        return answer;
    }
    
    public void BFS(int r, int c){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
               
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int row = now[0]; int col = now[1]; 
            
            for(int i=0; i<4; i++) {
                int nr = row + dr[i];
                int nc = col + dc[i];
                
                if(nr < 0 || nr > n-1 || nc < 0 || nc > n-1) {
                    continue;
                }
                                
                if(visited[nr][nc]) {
                    continue;
                }
                
                int gap = Math.abs(land[nr][nc] - land[row][col]);
                if(gap <= height) {
                    q.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                }else {
                    pq.offer(new int[]{nr, nc, gap});
                }
            }
        }
    }
 }