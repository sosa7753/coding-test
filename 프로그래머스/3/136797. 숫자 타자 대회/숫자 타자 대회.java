import java.util.*;
class Solution {
    int INF = Integer.MAX_VALUE;
    public int solution(String numbers) {
        int[][] cost = move();
             
        int[][] prev = new int[10][10]; // 왼손 위치, 오른손 위치
        for(int i=0; i<10; i++) {
            Arrays.fill(prev[i], INF);
        }
        
        prev[4][6] = 0;
        for(char c : numbers.toCharArray()) {
            int t = c - '0';
            int[][] cur = new int[10][10];
            for(int[] row : cur) Arrays.fill(row, INF);
            
            for(int L=0; L<10; L++) {
                for(int R=0; R<10; R++) {
                    if(prev[L][R] >= INF) continue;
                    int v = prev[L][R];
                    
                    if(L == t) {
                        cur[t][R] = Math.min(cur[t][R], v+1);
                    }else if(R == t) {
                        cur[L][t] = Math.min(cur[L][t], v+1);
                    }else {
                        cur[t][R] = Math.min(cur[t][R], v + cost[L][t]);
                        cur[L][t] = Math.min(cur[L][t], v + cost[R][t]);
                    }
                }
            }
            prev = cur;
        }
        
        int answer = INF;
        for(int[] row : prev) {
            for(int v : row) answer = Math.min(answer, v);
        }
        return answer;
    }
    
    public int[][] move() {
        int[][] map = new int[10][10];
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                if(i == j) {
                    map[i][j] = 1;
                    continue;
                }
                
                int s = i;
                int e = j;
                if(s == 0) s = 11;
                if(e == 0) e = 11;
        
                int sr = (s-1)/3; int sc = (s-1)%3;
                int er = (e-1)/3; int ec = (e-1)%3;
        
                int y = Math.abs(sr - er);
                int x = Math.abs(sc - ec);
        
                int max = Math.max(y, x);
                int min = Math.min(y, x);
                map[i][j] = min * 3 + (max-min)*2; 
            }
        }  
        return map;
    }
}