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
    
    public int razer(int r, int c, int d) {
        int result = 0;

        while(!visited[r][c][d]) {
            visited[r][c][d] = true;
            result++;
                        
            d = nextDir(map[r][c], d);
            
            r = (r + dr[d] + n)%n;
            c = (c + dc[d] + m)%m;
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