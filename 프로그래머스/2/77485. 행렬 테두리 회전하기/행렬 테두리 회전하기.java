import java.util.*;
class Solution {
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};      
    int[][] map;
    int[] answer;
    int idx;
    public int[] solution(int rows, int columns, int[][] queries) {
        answer = new int[queries.length];        
        idx = 0;   
        
        map = new int[rows][columns];
        int cnt = 1;
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map[i].length; j++) {
                map[i][j] = cnt++;
            }
        }
        
        // (2,2) ~(5,4) 회전
        // 1~4 행, 1~3열 변경, 
        
        for(int i=0; i<queries.length; i++) {
            int gap = (queries[i][2] - queries[i][0] + queries[i][3] - queries[i][1]) * 2;
            
            rotate(queries[i][0]-1, queries[i][1]-1, queries[i][0]-1, queries[i][1]-1,
                   queries[i][2]-1, queries[i][3]-1, 0, 
                   map[queries[i][0]-1][queries[i][1]-1], gap, Integer.MAX_VALUE, 0);
        }
        
        return answer;
    }
    
    public void rotate(int row, int col, int rs, int cs, int row2, int col2, int direct, 
                       int pre, int max, int min, int depth) {
        if(depth == max+1) {
            answer[idx++] = min;
            return;
        }
        
        int now = map[row][col]; 
        map[row][col] = pre;        
        
        int r = row + dy[direct%4];
        int c = col + dx[direct%4];
        int nextDirect = direct;
        
        if(r < rs || r > row2 || c < cs || c > col2) {
            nextDirect++;
            r = row + dy[nextDirect%4];
            c = col + dx[nextDirect%4];
        }
        
        min = Math.min(min, now);
  
        rotate(r, c, rs, cs, row2, col2, nextDirect, now, max, min, depth+1);        
    }
}