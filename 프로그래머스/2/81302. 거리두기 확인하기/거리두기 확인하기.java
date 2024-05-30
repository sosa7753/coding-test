import java.util.*;
class Solution {
    int[] dx = {0,1,0,-1};
    int[] dy = {-1,0,1,0};
    boolean isfalse;
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        isfalse = true;
        int idx = 0;
        
        for(int i=0; i<places.length; i++) {
            int cnt = 1;
            List<int[]> list = location(places[i]);
            
            while(!list.isEmpty()) {
                int[] now = list.remove(0);
                boolean[][] visited = new boolean[5][5];
                
                check(places[i], visited, now, now);
                if(!isfalse) {
                    cnt = 0;
                    isfalse = true;
                    break;
                }
            }
            answer[idx++] = cnt;    
        }
        
        return answer;
    }
    
    // 잘 지켰는지 체크, 잘 지켰으면 T 
    public List<int[]> location(String[] places) {
        
        List<int[]> list = new ArrayList<>();
        
        // P위치 찾기 
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                if(places[i].charAt(j) == 'P') {
                    list.add(new int[] {i, j});
                }
            }
        }    
        return list;
    }
    
    // 개별 P에 대해 체크 
    public void check(String[] place, boolean[][] visited, 
                         int[] node, int[] start) {
        int dis = Math.abs(start[0] - node[0]) + Math.abs(start[1] - node[1]);
        if(dis > 2) {
           return;
        }
        visited[node[0]][node[1]] = true;
                    
        for(int i=0; i<4; i++) {
            int row = node[0] + dy[i];
            int col = node[1] + dx[i];
            
            if(row < 0 || row > 4 || col < 0 || col > 4) {
                continue;
            }
            
            if(visited[row][col]) {
                continue;
            }
            
            if(place[row].charAt(col) == 'X') {
                continue;
            }
            visited[row][col] = true;
            
            if(place[row].charAt(col) == 'P') {
               int len = Math.abs(start[0] - row) + Math.abs(start[1] - col);
               if(len <=2) {
                   isfalse = false;
               }          
               return;
            }
            
            int[] tmp = {row, col};
            check(place, visited, tmp, start);
        }
    }
}