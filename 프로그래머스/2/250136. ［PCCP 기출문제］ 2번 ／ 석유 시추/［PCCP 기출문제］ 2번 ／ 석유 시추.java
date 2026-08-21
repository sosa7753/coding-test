import java.util.*;
class Solution {
    Map<Integer, Integer> map = new HashMap<>(); // 각 열 인덱스의 값
    int[][] land;
    int n,m;
    public int solution(int[][] land) {      
        this.land = land;
        n = land.length;
        m = land[0].length;
        
        int idx = 2;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(land[i][j]==1) {
                   BFS(i, j, idx);
                   idx++; 
                }
            }
        }
                 
        int answer = 0;
        for(int v : map.values()) {
            answer = Math.max(answer, v);
        }
       
        return answer;
    }
    
    public void BFS(int row, int col, int idx) {
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        
        land[row][col] = idx;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{row, col});

        Set<Integer> set = new HashSet<>();
        int cnt = 0;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0]; int c = now[1];
            cnt++;
            set.add(c);
            
            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if(nr < 0 || nr > n-1 || nc < 0 || nc > m-1) continue;
                if(land[nr][nc] == 1) {
                    land[nr][nc] = idx;
                    q.offer(new int[]{nr, nc});          
                }           
            }
        }
        
        for(int s : set) {
            if(!map.containsKey(s)) {
                map.put(s, cnt);
            }else {
                map.put(s, map.get(s) + cnt);
            }
        }
    }
}