import java.util.*;
class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};
    public int solution(String[] board) {
        
        int b = board.length;
        int bc = board[0].length();
          
        int[] start = new int[2];
        int[] finish = new int[2];
             
        // 초기 위치 찾기
        for(int i=0; i<b; i++) { 
            for(int j=0; j<bc; j++) {
                if(board[i].charAt(j) == 'R') { // 시작지점 찾기
                    start[0] = i;
                    start[1] = j;
                    continue;
                }
                
                if(board[i].charAt(j) == 'G') { // 목표지점 찾기
                    finish[0] = i;
                    finish[1] = j;
                    continue;
                }
            }
        }
        
        int[][] answer = new int[b][bc]; // 정답 배열 
        for(int i=0; i<b; i++) {
            for(int j=0; j<bc; j++) {
                answer[i][j] = Integer.MAX_VALUE;
            }
        }
        
        answer[start[0]][start[1]] = 0;
                
        // 현재 위치 + 횟수 
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {start[0], start[1] , 0});
        
        // 방문배열 
        boolean[][] visited = new boolean[b][bc];
        visited[start[0]][start[1]] = true;
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            
            // 상우하좌 반복
            for(int i=0; i<4; i++) {
                
                int row = now[0] + dy[i];
                int col = now[1] + dx[i];
                           
                // 이동하기
                while(true) {
                    
                    int rowP = row-dy[i];
                    int colP = col-dx[i];
                
                    // 범위를 벗어나는 값이면 
                    if(row < 0 || row > b-1 || col < 0 || col > bc-1) {
                        
                        // 만약 같은 위치면 종료
                        if(rowP == now[0] && colP == now[1]) {
                            break;
                        }
                        
                        // 이전 값이 방문한 적 없을 경우 횟수 추가 
                        if(!visited[rowP][colP]) {
                            visited[rowP][colP] = true;
                            answer[rowP][colP] = now[2] + 1;
                            queue.add(new int[] {rowP, colP, now[2] + 1});
                            break;                           
                        }
                        
                        // 이전 값이 방문한 적 있을 경우 값이 적을때만 추가 
                        if(visited[rowP][colP]) {
                            if(now[2] + 1 < answer[rowP][colP]) {
                                answer[rowP][colP] = now[2] + 1;
                                queue.add(new int[] {rowP, colP, now[2]+1}); 
                            }
                            break;
                        }
                    }
                    
                    // 한칸 이동 했을 때 장애물이 아닐 경우
                    if(board[row].charAt(col) != 'D') {
                        row += dy[i];
                        col += dx[i];
                        continue;
                    }
                    
                    // 장애물일 경우
                    if(board[row].charAt(col) == 'D') {
                        
                        // 이전 칸이 방문한적 없을 때 횟수 기입
                        if(!visited[rowP][colP]) {
                            visited[rowP][colP] = true;
                            answer[rowP][colP] = now[2] + 1;
                            queue.add(new int[] {rowP, colP, now[2] + 1});
                            break;
                        }
                        
                        // 이전 칸이 방문한적 있을 때 answer보다 작으면 기입
                        if(visited[rowP][colP]) {
                            if(now[2] + 1 < answer[rowP][colP]) {
                                answer[rowP][colP] = now[2] + 1;
                                queue.add(new int[] {rowP, colP, now[2] +1});
                            }
                            break;
                        }
                    }
                }
            }
        }
            
       if(answer[finish[0]][finish[1]] == Integer.MAX_VALUE) {
                return -1;
       }   
        
       return answer[finish[0]][finish[1]];            
    } 
}