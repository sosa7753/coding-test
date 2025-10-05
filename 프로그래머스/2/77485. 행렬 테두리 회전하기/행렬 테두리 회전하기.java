import java.util.*;
class Solution {
    int[][] map;
    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows][columns];
        for(int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                map[i][j] = i * columns + j + 1;
            }
        }
              
        int n = queries.length;
        int[] answer = new int[n];      
        for(int i=0; i<n; i++) {
            answer[i] = 
                rotate(queries[i][0]-1, queries[i][1]-1, queries[i][2]-1, queries[i][3]-1);
        }
        return answer;
    }
    
    public int rotate(int rs, int cs, int re, int ce) {
        // tmp 값을 나한테 적고, 내 값은 pre에 넣기 -> pre = tmp
        int min = map[rs][cs];
        int tmp = map[rs][cs];  
        int pre = 0;  
        for(int i=cs+1; i<=ce; i++) { // 위
            pre = map[rs][i]; 
            map[rs][i] = tmp; 
            tmp = pre;        
            min = Math.min(min, tmp);
        }
        
        for(int i=rs+1; i<=re; i++) { // 우
            pre = map[i][ce];
            map[i][ce] = tmp;
            tmp = pre;
            min = Math.min(min, tmp);
        }
        
        for(int i=ce-1; i>=cs; i--) { // 하
            pre = map[re][i];
            map[re][i] = tmp;  
            tmp = pre;
            min = Math.min(min, tmp);
        }
        

        for(int i=re-1; i>=rs; i--) { // 좌
            pre = map[i][cs];
            map[i][cs] = tmp;
            tmp = pre;
            min = Math.min(min, tmp);
        }
        
        return min;
    }
}