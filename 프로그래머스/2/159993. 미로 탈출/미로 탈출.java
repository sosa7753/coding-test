import java.util.*;
class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};
    public int solution(String[] maps) {
      
        int m = maps.length;
        int ml = maps[0].length();
               
        int[][] answer = new int[m][ml]; // 정답 배열 

        for(int i=0; i<m; i++) { // 정답 배열 초기화
            for(int j=0; j<ml; j++) {
                answer[i][j] = Integer.MAX_VALUE;
            }
        }
            
        int[] start = new int[2];
        int[] exit = new int[2];
        int[] lever = new int[2];
        
        // 시작지점 끝 지점 찾기 
        for(int i=0; i<m; i++) {
            for(int j=0; j<ml; j++) {
                if(maps[i].charAt(j) == 'S') { // 시작 지점 
                    start[0] = i;
                    start[1] = j;
                    continue;
                }
                
                if(maps[i].charAt(j) == 'E') {
                    exit[0] = i;
                    exit[1] = j;
                    continue;
                }
                
                if(maps[i].charAt(j) == 'L') {
                    lever[0] = i;
                    lever[1] = j;
                    continue;
                }
                
                if(maps[i].charAt(j) == 'X') {
                    answer[i][j] = -1;
                }
            }
        }
        
        int first = BFS(answer, start, lever); // 시작 -> 레버
        int second = BFS(answer, lever, exit); // 레버 -> 끝
        
        if(first == Integer.MAX_VALUE || second == Integer.MAX_VALUE) {
            return -1;
        }
        return first + second;
    }
    
    // 시작 점에서 끝 점까지 최단거리
    public int BFS(int[][] answer, int[] start, int[] end) {
                
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {start[0], start[1], 0});
        
        boolean[][] visited = new boolean[answer.length][answer[0].length];
        visited[start[0]][start[1]] = true;
        
        answer[start[0]][start[1]] = 0;
        
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            
            // 이동하기 
            for(int i=0; i<4; i++) {
                int row = now[0] + dy[i];
                int col = now[1] + dx[i];
                
                // 범위를 벗어났으면 패스
                if(row < 0 || row > answer.length -1 
                   || col < 0 || col > answer[0].length -1) {
                    continue;
                }
                
                // 벽이면 패스
                if(answer[row][col] == -1) {
                    continue;
                }
                
                
                // 방문한 적이 없는 경우 값 기입 
                if(!visited[row][col]) {
                    visited[row][col] = true;
                    
                    answer[row][col] = now[2]+1;
                    queue.add(new int[] {row, col, now[2]+1});
                    continue;
                }
                
                // 방문한적 있다면 작은값일 때 기입 
                if(visited[row][col]) {
                    if(answer[row][col] > now[2] + 1) {
                        answer[row][col] = now[2] + 1;
                        queue.add(new int[] {row, col, now[2]+1});
                    }
                    continue;
                }
            }
        }
               
        return answer[end[0]][end[1]];   
    }
}