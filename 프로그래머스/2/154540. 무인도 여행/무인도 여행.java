import java.util.*;
class Solution {
    boolean[][] visited;
    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};
    int n; // 행
    int m; // 열
    int cnt;
    public int[] solution(String[] maps) {        
        n = maps.length;
        m = maps[0].length();
        
        visited = new boolean[n][m];
        
        List<Integer> list = new ArrayList<>();
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(visited[i][j]) {
                    continue;
                }
                
                if(maps[i].charAt(j) == 'X') {
                    continue;
                }
                cnt = 0;
                
                DFS(maps, i,j);
                list.add(cnt);
            }
        }
        
        if(list.isEmpty()) {
            return new int[] {-1};
        }
        Collections.sort(list);
        int[] answer = new int[list.size()];
        int idx = 0;
        for(int i=0; i<list.size(); i++) {
            answer[idx++] = list.get(i);
        }
        
        return answer;
    }
    
    public void DFS(String[] maps, int row, int col) {      
        if(visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        cnt += maps[row].charAt(col) - '0';
        
        for(int i=0; i<4; i++) {
            int r = row + dy[i];
            int c = col + dx[i];
            
            if(r < 0 || r > n-1 || c < 0 || c > m-1) {
                continue;
            }
            
            if(maps[r].charAt(c) == 'X') {
                continue;
            }
            
            if(visited[r][c]) {
                continue;
            }
            DFS(maps,r,c);
        }     
    }
}