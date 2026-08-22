import java.util.*;
class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    public int[] solution(String[] maps) {
        List<Integer> list = new ArrayList<>();
        
        int n = maps.length;
        int m = maps[0].length();
        int[][] visited = new int[n][m];
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(maps[i].charAt(j) == 'X') continue;
                if(visited[i][j] == 1) continue;
                
                list.add(BFS(i, j, maps, visited));
            }
        }
        
        if(list.isEmpty()) return new int[]{-1};
        
        Collections.sort(list);
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    public int BFS(int row, int col, String[] maps, int[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{row, col});
        visited[row][col] = 1;
        
        int cnt = 0;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0]; int c = now[1];
            cnt += maps[r].charAt(c) - '0';
            
            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr < 0 || nr > maps.length-1 || nc < 0 || nc > maps[0].length()-1) continue;
                if(maps[nr].charAt(nc) == 'X') continue;
                if(visited[nr][nc] != 1) {
                    visited[nr][nc] = 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
        return cnt;
    }
}