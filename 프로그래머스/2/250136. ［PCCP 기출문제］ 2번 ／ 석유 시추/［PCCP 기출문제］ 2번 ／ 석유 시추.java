import java.util.*;
class Solution {
    int n, m;
    int[][] board;
    Map<Integer, Integer> map = new HashMap<>();
    public int solution(int[][] land) {
        n = land.length; m = land[0].length; 
        board = land;
        
        int idx = 2;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] != 1) continue;
                BFS(i, j, idx);
                idx++;
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
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});
        board[row][col] = idx;
                
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
                if(board[nr][nc] != 1) continue;
                board[nr][nc] = idx;
                q.offer(new int[]{nr, nc});
            }
        }
        
        for(int s : set) {
            if(!map.containsKey(s)) map.put(s, cnt);
            else map.put(s, map.get(s) + cnt);
        }
    }
}