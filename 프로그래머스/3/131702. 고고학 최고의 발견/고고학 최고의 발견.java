import java.util.*;
class Solution {
    int[] dr = {0, 1, 0};
    int[] dc = {1, 0, -1};
    int[][] clock;
    int[][] map;
    int[] arr;
    int n;
    int answer = Integer.MAX_VALUE;
    public int solution(int[][] clockHands) {      
        clock = clockHands;
        n = clockHands.length;      
        arr = new int[n];
        
        DFS(0);
        return answer;
    }
    
    public void DFS(int cnt) {
        if(cnt == n) {
            cal();
            return;
        }
        
        for(int i=0; i<4; i++) {
            arr[cnt] = i;
            DFS(cnt+1);
        }   
    }
    
    public void cal() {
        map = new int[n][n];
        for(int i=0; i<n; i++) {
            map[i] = Arrays.copyOf(clock[i], n);
        }
        
        int result = 0;
        int[] next = Arrays.copyOf(arr, arr.length); // 다음 배열에 더해줄 값
        
        for(int i=0; i<n; i++) {
           rotate(next, i);
           if(result >= answer) {
               return;
           }
            
           for(int j=0; j<n; j++) {                
               result += next[j];   
               next[j] = (4-map[i][j])%4;
           }      
        }
        
        for(int i=0; i<n; i++) {
            if(next[i] != 0) {
                return;
            }
        }

        answer = Math.min(answer, result);
    }
    
    public void rotate(int[] next, int row) {
        for(int i=0; i<n; i++) { // 열 반복
            map[row][i] = (map[row][i] + next[i])%4;
            for(int j=0; j<3; j++) { // 3방향 탐색(윗 방향 제외)
                int nr = row + dr[j];
                int nc = i + dc[j];
                
                if(nr > n-1 || nc < 0 || nc > n-1) {
                    continue;
                }
                
                map[nr][nc] = (map[nr][nc] + next[i])%4;
            }
        }
    }
}