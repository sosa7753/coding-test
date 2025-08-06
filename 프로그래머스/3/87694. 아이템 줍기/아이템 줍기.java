import java.util.*;
class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1 ,0};
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        
        int[][] map = new int[101][101]; // 2배 확장
        
        for(int[] rect : rectangle) { // 테두리 채우기
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;
            
            for(int i=y1; i<=y2; i++) {
                map[x1][i] = 1;
                map[x2][i] = 1;
            }
            
            for(int i=x1; i<=x2; i++) {
                map[i][y1] = 1;
                map[i][y2] = 1;
            }
        }
        
        for(int[] rect : rectangle) { // 내부 값 지우기
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;
            
            for(int i=x1+1; i<x2; i++) {
                for(int j=y1+1; j<y2; j++) {
                    map[i][j] = 0;
                }
            }
        }
        
        return BFS(map, characterX*2, characterY*2, itemX*2, itemY*2);
    }
    
    public int BFS(int[][] map, int sx, int sy, int ex, int ey) {
        boolean[][] visited = new boolean[101][101];
        visited[sx][sy] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy, 0});
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            int v = now[2];
            
            if(x == ex && y == ey) {
                return v/2;
            }
            
            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx > 100 || ny < 0 || ny > 100) {
                    continue;
                }
                
                if(visited[nx][ny]) {
                    continue;
                }
                
                if(map[nx][ny] == 0) {
                    continue;
                }
                
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny, v+1});
            }
        }
        return 0;
    }
}