import java.util.*;
class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int m, n;
    int[][] pic;
    boolean[][] visited;
    public int[] solution(int m, int n, int[][] picture) {
        this.m = m;
        this.n = n;
        pic = picture;
        
        int cnt = 0;
        int max = 0;
        visited = new boolean[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(picture[i][j] == 0) continue;
                
                if(visited[i][j]) continue;
                visited[i][j] = true;
                max = Math.max(max, BFS(i, j, picture[i][j]));
                cnt++;
            }
        }
        
        int[] answer = new int[2];
        answer[0] = cnt;
        answer[1] = max;
        return answer;
    }
    
    public int BFS(int row, int col, int v) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{row, col});
        
        int cnt = 1;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0]; int c = now[1]; 
            
            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if(nr < 0 || nr > m-1 || nc < 0 || nc > n-1) {
                    continue;
                }
                
                if(visited[nr][nc]) {
                    continue;
                }
                
                if(pic[nr][nc] == v) {
                    q.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}