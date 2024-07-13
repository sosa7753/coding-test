import java.util.*;
class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};
    boolean[][] visited;
    int region = 0;
    public int[] solution(int m, int n, int[][] picture) {       
        visited = new boolean[m][n];     
        int max = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(!visited[i][j] && picture[i][j] != 0) {
                    max = Math.max(max, area(picture, i, j));
                }                
            }
        }
        
        int[] answer = new int[2];
        answer[0] = region;
        answer[1] = max;
        return answer;
    }
    
    public int area(int[][] picture, int row, int col) {
        int result = 0;
        Queue<int[]> queue = new LinkedList<>(); 
        queue.offer(new int[] {row, col});
        
        int check = picture[row][col];
        visited[row][col] = true;
        result = 1;
        
        boolean isfalse = true;
        while(!queue.isEmpty()) {
            int[] now = queue.poll(); // 행, 열 
            
            for(int i=0; i<4; i++) {
                int r = now[0] + dy[i];
                int c = now[1] + dx[i];
                
                if(r < 0 || r > picture.length - 1 || 
                   c < 0 || c > picture[0].length - 1 ) {                   
                    continue;
                }
                
                if(visited[r][c]) {
                    continue;
                }
                
                if(picture[r][c] != check) {
                    continue;
                }
                
                visited[r][c] = true;
                result++;
                queue.offer(new int[] {r, c});
            }
        }
        
        region++;
        return result;
    }
}