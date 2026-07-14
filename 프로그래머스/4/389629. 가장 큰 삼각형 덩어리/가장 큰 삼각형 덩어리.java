import java.util.*;
class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int[][][] visited;
    int n,m;
    public int solution(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
            
        //  -1        1      -1     1
        // (0, 1), (1, 2), (2, 3),(3, 0) 을 가진 삼각형
        visited = new int[n][m][4]; 
        
        int answer = 0;
        
        int idx = 1;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                int f = grid[i][j];
                int start = (f == -1) ? 0 : 1;
                for(int k=start; k<4; k += 2) {
                    if(visited[i][j][k] != 0) continue;
                    
                    answer = Math.max(answer, BFS(grid, i, j, k, idx));
                    idx++;
                }
            }
        }
        
        return answer;
    }
    
    public int BFS(int[][] grid, int row, int col, int tri, int idx) { 
        Set<Integer> set = new HashSet<>();
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{row, col, tri}); // 행, 열, 대각선 방향
        visited[row][col][tri] = idx;
        set.add(row*m + col);
        
        int result = 1;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0]; int c = now[1]; int t = now[2];
                  
            for(int i=0; i<2; i++) {
                int d = (t+i)%4;  
                int nr = r + dr[d];
                int nc = c + dc[d];
                                   
                if(nr < 0 || nr > n-1 || nc < 0 || nc > m-1) continue;
                if(set.contains(nr*m + nc)) continue;
                
                int nt = triangle((d+2)%4, grid[nr][nc]);
                if(visited[nr][nc][nt] == idx) continue;
                
                visited[nr][nc][nt] = idx;       
                q.offer(new int[]{nr, nc, nt});     
                set.add(nr*m + nc);
                result++;
            }
        }
        return result;
    }
    
    // 이전 위치에서 온 방향 (i+2)%, 현재 위치의 대각선 값이 주어지면 어떤 삼각형인지 나옴
    public int triangle(int d, int f) {   
        // f가 -1이면 홀수 idx, f가 1이면 짝수 idx
        // d = 2 -> d와 (d+3)% 이 후보
        if(f == -1) { 
            return d%2 == 0 ? d : (d+3)%4;
        }else {
            return d%2 == 1 ? d : (d+3)%4;
        }
    }
}