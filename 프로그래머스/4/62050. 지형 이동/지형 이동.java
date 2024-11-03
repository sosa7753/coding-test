import java.util.*;
class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    boolean[][] visited;    
    PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[2] - y[2]));
    int answer = 0;    
    public int solution(int[][] land, int height) {
        visited = new boolean[land.length][land[0].length];        
        pq.offer(new int[] {0,0,0});
        
        while(!pq.isEmpty()) {      
            int[] now = pq.poll();            
            if(visited[now[0]][now[1]]) {
                continue;
            }
            
            answer += now[2];
            BFS(now[0], now[1], land, height);
            
        }
        return answer;
    }
    
    public void BFS(int row, int col, int[][] land, int height) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {row, col});
        visited[row][col] = true;
      
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
                               
            for(int i=0; i<4; i++) {
                int r = now[0] + dr[i];
                int c = now[1] + dc[i];
                
                if(r < 0 || r > land.length-1 || c < 0 || c > land.length-1) {
                    continue;
                }
                
                if(visited[r][c]) {
                    continue;
                }             
                int cur = land[now[0]][now[1]]; // 현재 값
                if(land[r][c] >= cur - height && land[r][c] <= cur+height) { 
                    queue.offer(new int[] {r, c});
                    visited[r][c] = true;  
                }else { // 불가능
                    pq.offer(new int[] {r,c, Math.abs(land[r][c] - cur)});
                }
            }
        } 
    }    
}