class Solution {
    int[] dx = {0, 1, -1, 0}; // 상 우 좌 하 
    int[] dy = {1, 0,  0, -1};
    public int solution(String dirs) {
        int answer = 0;
        
        boolean[][][] visited = new boolean[11][11][4]; // +5, +5
        
        int x = 5;
        int y = 5;
        
        for(int i=0; i<dirs.length(); i++) {
            int direct = direction(dirs.charAt(i));
            
            int nx = x + dx[direct];
            int ny = y + dy[direct];
            
            if(nx < 0 || nx > 10 || ny < 0 || ny > 10) {
                continue;
            }
            
            if(visited[nx][ny][Math.abs(3 - direct)] && 
               visited[x][y][Math.abs(direct)]) {
                x = nx;
                y = ny;
                continue;
            }
            visited[nx][ny][Math.abs(3 - direct)] = true;
            visited[x][y][Math.abs(direct)] = true;
            x = nx;
            y = ny;
            answer++;
        }
        
        return answer;
    }
    
    public int direction(char c) {
        if('U' == c) {
            return 0;
        }else if('D' == c) {
            return 3;
        }else if('R' == c) {
            return 1;
        }else {
            return 2;
        }
    }
}