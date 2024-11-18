import java.util.*;
class Solution {
    boolean[][][] map;
    int nl;
    PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> {
        if(x[0] == y[0]) {
            if(x[1] == y[1]) {
                return x[2] - y[2];
            }else {
                return x[1] - y[1];
            }
        }else {
            return x[0] - y[0];
        }
    });
       
    public int[][] solution(int n, int[][] build_frame) {
        map = new boolean[n+1][n+1][2]; // 0은 기둥 1은 보   
        nl = n;
        for(int i=0; i<build_frame.length; i++) {
            int[] tmp = build_frame[i];
            int x = tmp[0];
            int y = tmp[1];
            if(tmp[3] == 1) { // 설치
                if(tmp[2] == 0 && ki(x, y)) { // 기둥
                    map[x][y][0] = true;
                }else if(tmp[2] == 1 && bo(x, y)) { // 보
                    map[x][y][1] = true;
                }
            }else { // 삭제
                if(tmp[2] == 0) { // 기둥 
                    map[x][y][0] = false; 
                    if((!map[x][y+1][0] || ki(x, y+1)) && 
                       (x == 0 || (!map[x-1][y+1][1] || bo(x-1, y+1))) && 
                       (!map[x][y+1][1] || bo(x, y+1))) { // 윗 기둥, 윗 보 2개 체크
                        continue;       
                    }
                    map[x][y][0] = true;
                }else { // 보
                    map[x][y][1] = false;
                    if((!map[x][y][0] || ki(x, y)) &&
                       (!map[x+1][y][0] || ki(x+1, y)) &&           
                       (x == 0 || (!map[x-1][y][1] || bo(x-1, y))) && 
                       (!map[x+1][y][1] || bo(x+1, y))) { // 윗 기둥 2개, 양쪽 보 2개
                       continue;                        
                    }
                    map[x][y][1] = true;
                }
            }   
        }
           
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=n; j++) {
                for(int k=0; k<=1; k++) {
                    if(map[i][j][k]) {
                        pq.add(new int[] {i,j,k});
                    }
                }
            }
        }
        
        int[][] answer = new int[pq.size()][3];
        
        int idx = 0;
        while(!pq.isEmpty()) {
            answer[idx++] = pq.poll();
        }               
        return answer;
    }
   
    public boolean ki(int x, int y) {
        if(y == 0 || (y > 0 && map[x][y-1][0]) || (x > 0 && map[x-1][y][1]) 
           || map[x][y][1]) {
            return true;
        }
        return false;      
    }
    
    public boolean bo(int x, int y) {
        if(map[x][y-1][0] || map[x+1][y-1][0] || 
           (x > 0 && map[x-1][y][1] && map[x+1][y][1])) {
            return true;
        }
        return false;      
    }
}