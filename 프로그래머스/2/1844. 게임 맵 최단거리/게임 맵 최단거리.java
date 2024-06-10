import java.util.*;
class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};
    int[][] board; 
    boolean[][] visited;
    public int solution(int[][] maps) {
        int answer = 0;
        
        board = new int[maps.length][maps[0].length];
        visited = new boolean[maps.length][maps[0].length];
                                           
        BFS(visited, maps, board);
             
        if(board[maps.length-1][maps[0].length-1] == 0) {
            return -1;
        }                               
        return board[maps.length-1][maps[0].length-1];
    }
    
    public void BFS(boolean[][] visited, int[][] maps, int[][] board) {
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0,0});
        board[0][0] = 1;
                
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            
            if(now[0] == maps.length-1 && now[1] == maps[0].length-1) {
                return;
            }
            
            for(int i=0; i<4; i++) {
                int row = now[0] + dy[i];
                int col = now[1] + dx[i];
                
                if(row < 0 || row > maps.length-1 || col < 0 || col > maps[0].length-1) {
                    continue;
                }
                
                if(visited[row][col]) {
                    continue;
                }
                
                if(maps[row][col] == 0) {
                    continue;
                }
                
                visited[row][col] = true;                 
                board[row][col] = board[now[0]][now[1]] + 1;
                queue.offer(new int[] {row, col});                
            }
        }
    }
}