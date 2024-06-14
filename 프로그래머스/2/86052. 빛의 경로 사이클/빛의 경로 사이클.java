import java.util.*;
class Solution {
    boolean[][][] visited;
    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};
    int n;
    int m;
    List<Integer> list = new ArrayList<>();
    public int[] solution(String[] grid) {        
        n = grid.length;
        m = grid[0].length();
        
        visited = new boolean[n][m][4];
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                // 0 : 상 1 : 우 2 : 하 3 : 좌 
                for(int k=0; k<4; k++) {
                    if(visited[i][j][k]) {
                        continue;
                    }      
                    list.add(BFS(grid, i,j,k));
                }
            }
        }
        
        Collections.sort(list);
        int[] answer = new int[list.size()];
        int idx = 0;
        for(int i=0; i<answer.length; i++) {
            answer[idx++] = list.get(i);
        } 
        
        return answer;
    }
    
    public int BFS(String[] grid, int row, int col, int direct) {
        int result = 0;
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {row, col, direct});
                
        while(!queue.isEmpty()) {
            int[] start = queue.poll();
            
            if(visited[start[0]][start[1]][start[2]]) {
                continue;
            }
            visited[start[0]][start[1]][start[2]] = true;
            
            int r = start[0] + dy[start[2]]; 
            int c = start[1] + dx[start[2]];
            
            // 좌표 체크 
            if(r < 0 || r > n-1) {
                r = Math.abs(n-1 - start[0]);
            }
        
            if(c < 0 || c > m-1) {
                c = Math.abs(m-1 - start[1]);
            }
            
            // 방향 잡기
            char next = grid[r].charAt(c);
            int nextK = start[2];
            if(next == 'L') { // 0 -> 3,  1 -> 0, 2 -> 1, 3 -> 2
                nextK = (nextK + 3)%4;
            }else if(next == 'R') { // 0 -> 1,  1 -> 2, 2 -> 3, 3 -> 0
                nextK = (nextK + 1)%4;
            }
            
            queue.offer(new int[] {r, c, nextK});
            result++;         
        }
        
        return result;    
    }
}