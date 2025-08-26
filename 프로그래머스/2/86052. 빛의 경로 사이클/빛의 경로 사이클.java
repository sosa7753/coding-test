import java.util.*;
class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int n,m;
    char[][] map;
    boolean[][][] visited;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    public int[] solution(String[] grid) {
        n = grid.length;
        m = grid[0].length();
        map = new char[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                map[i][j] = grid[i].charAt(j);
            }
        }
        
        visited = new boolean[n][m][4];
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                for(int k=0; k<4; k++) {
                    if(visited[i][j][k]) {
                        continue;
                    }
                    pq.offer(razer(i, j, k));
                }
            }
        }
        int[] answer = new int[pq.size()];
        int idx = 0;
        while(!pq.isEmpty()) {
            answer[idx++] = pq.poll();
        }
        return answer;
    }
    
    public int razer(int row, int col, int dir) {
        int result = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{row,col,dir});
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0]; int c = now[1]; int d = now[2];
            
            if(visited[r][c][d]) {
                continue;
            }
            visited[r][c][d] = true;
           
            int nr = now[0] + dr[d];
            int nc = now[1] + dc[d];
            
            if(nr < 0 || nr > n-1) {
                nr = Math.abs(n-1 - now[0]);
            }
            
            if(nc < 0 || nc > m-1) {
                nc = Math.abs(m-1 - now[1]);
            }
            
            int nd = nextDir(map[nr][nc], d);
            
            q.offer(new int[]{nr, nc, nd});
            result++;
        }
        
        return result;     
    }
    
    public int nextDir(char c, int d) {
        if(c == 'S') {
            return d;
        }else if(c == 'L') {
            return (d - 1 + 4)%4;
        }else {
            return (d + 1)%4;
        }      
    }
}