import java.util.*;
class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};
    int[] R = new int[2];
    int[] G = new int[2];
    boolean[][] visited;
    int answer = -1;
    public int solution(String[] board) {
        int b = board.length;
        int bl = board[0].length();
        
        visited = new boolean[b][bl];
        
        for(int i=0; i<b; i++) {
            for(int j=0; j<bl; j++) {
                if(board[i].charAt(j) == 'R') {
                    R[0] = i;
                    R[1] = j;
                }
                
                if(board[i].charAt(j) == 'G') {
                    G[0] = i;
                    G[1] = j;
                }
                
                
            }
        }
        
        BFS(board);
        return answer;
    }
    
    public void BFS(String[] board) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {R[0], R[1], 0});
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            
            if(G[0] == now[0] && G[1] == now[1]) { // 도착
                answer = now[2];
                return;
            }
            
            if(visited[now[0]][now[1]]) {
                continue;
            }
            visited[now[0]][now[1]] = true;
            
            // 4방향으로 D가 나오거나 끝까지 가야함.
            for(int i=0; i<4; i++) {
                int r = now[0];
                int c = now[1];
                while(true) {
                    r += dy[i];
                    c += dx[i];
                    
                    // 벗어난 값이면
                    if(r < 0 || r > board.length-1 || 
                       c < 0 || c > board[0].length()-1) {
                        
                        r -= dy[i];
                        c -= dx[i];
                        break;
                    }
                                        
                    if(board[r].charAt(c) == 'D') {
                        r -= dy[i];
                        c -= dx[i];
                        break;
                    } 
                }
                
                queue.offer(new int[] {r, c, now[2] + 1});
            }           
        }
    }
}