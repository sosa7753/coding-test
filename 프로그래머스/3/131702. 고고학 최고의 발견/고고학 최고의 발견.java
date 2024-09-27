class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int[] arr; // 방향 배열
    int answer = Integer.MAX_VALUE;
    public int solution(int[][] clockHands) {        
        arr = new int[clockHands[0].length];
        
        DFS(0, clockHands);
        
        return answer;
    }
    
    public void DFS(int cnt, int[][] clockHands) {
        if(cnt == arr.length) {
            int result = cal(clockHands);
            if(result >= 0) {
                answer = Math.min(answer, result);
            }
            return;
        }
        
        for(int i=0; i<4; i++) {
            arr[cnt] = i;
            DFS(cnt+1, clockHands);
        }        
    }
    
    public int cal(int[][] clockHands) {
       int sum = 0;
       int[][] map = new int[clockHands.length][clockHands[0].length];
       for(int i=0; i<clockHands.length; i++) {
           map[i] = clockHands[i].clone();
       } 
               
       // 0행에 대한 회전 
       rotate(map, 0, arr);
        
       int[] next = new int[map[0].length];
       for(int i=0; i<map[0].length; i++) {
           next[i] = (4-map[0][i])%4;
           sum += next[i];
       }
        
       // 1행~ 대한 회전
       for(int i=1; i<map.length; i++) {
           rotate(map, i, next);
           for(int j=0; j<map[i].length; j++) {
               next[j] = (4-map[i][j])%4; 
               sum += next[j];
           }
       }
        
       // 마지막이 풀리는지       
       for(int i=0; i<next.length; i++) {
          if(next[i] != 0) {
              return -1;
          } 
       }
       
       // arr 값 더해주기.
       for(int i=0; i<arr.length; i++) {
           sum += arr[i];
       }
       return sum;
    }
    
    public void rotate(int[][] map, int row, int[] change) {        
        for(int i=0; i<map[row].length; i++) {
            map[row][i] = (map[row][i] + change[i])%4;
            
            for(int j=0; j<4; j++) {
                int r = row + dr[j];
                int c = i + dc[j];
                
                if(r < 0 || r > map.length-1 || c < 0 || c > map[0].length-1) {
                    continue;
                }
                
                map[r][c] = (map[r][c] + change[i])%4;
            }        
        }
    }
}