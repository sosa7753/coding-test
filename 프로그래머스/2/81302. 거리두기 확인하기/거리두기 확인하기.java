import java.util.*;
class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int i=0; i<5; i++) {
            boolean check = true;
            for(int j=0; j<5; j++) {
                for(int k=0; k<5; k++) {
                    if(places[i][j].charAt(k) == 'P') {
                        if(!BFS(places[i], j, k)) {
                            check =false;
                            break;
                        }
                    }
                }
                
                if(!check) {
                    break;
                }
            }
            
            if(check) {
                answer[i] = 1;
            }
        }
        return answer;
    }
    
    public boolean BFS(String[] place, int r, int c) {
        boolean[][] visited = new boolean[5][5];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r,c, 0});
        visited[r][c] = true;
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int row = now[0];
            int col = now[1];
            int cnt = now[2];
            
            if(cnt >= 2) {
                continue;
            }
            
            for(int i=0; i<4; i++) {
                int nr = row + dr[i];
                int nc = col + dc[i];
                
                if(nr < 0 || nr > 4 || nc < 0 || nc > 4) {
                    continue;
                }       
                
                if(visited[nr][nc]) {
                    continue;
                }
                
                visited[nr][nc] = true;
                if(place[nr].charAt(nc) == 'P') {
                    return false;
                }else if(place[nr].charAt(nc) =='O') {
                    q.add(new int[]{nr, nc, cnt+1});
                }          
            }
        }
        return true;
    }
}