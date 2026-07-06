class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    int answer = Integer.MAX_VALUE;
    int err = 0; int erc = 0; // 빨 도착
    int ebr = 0; int ebc = 0; // 파 도착
    int n, m;
    public int solution(int[][] maze) {
        n = maze.length; m = maze[0].length;       
        int srr = 0; int src = 0;
        int sbr = 0; int sbc = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(maze[i][j] == 1) { 
                    srr = i; src = j;
                }else if(maze[i][j] == 2) {
                    sbr = i; sbc = j;
                }else if(maze[i][j] == 3) {
                    err = i; erc = j;
                }else if(maze[i][j] == 4) {
                    ebr = i; ebc = j;
                }
            }
        }
       
        int visitedR = (1 << (srr*m + src));
        int visitedB = (1 << (sbr*m + sbc));
        DFS(maze, visitedR, visitedB, new int[]{srr, src}, new int[]{sbr, sbc}, 0);
        if(answer == Integer.MAX_VALUE) {
            return 0;
        }
        return answer;
    }
    public void DFS(int[][] maze, int visitedR, int visitedB, 
                    int[] R, int[] B, int cnt) {
         if(cnt >= answer) {
             return;
         }
        
         int r1 = R[0]; int c1 = R[1]; int r2 = B[0]; int c2 = B[1];
         if(r1 == err && c1 == erc && r2 == ebr && c2 == ebc) { // 도착한 경우
             answer = Math.min(answer, cnt);
             return;
         }
               
         // 빨간 수레 고정
         if(r1 == err && c1 == erc) {
             for(int i=0; i<4; i++) {
                 int nr2 = r2 + dr[i];
                 int nc2 = c2 + dc[i]; 
                 if(!check(maze, visitedB, nr2, nc2)) continue;
                 if(r1 == nr2 && c1 == nc2) continue;
                 visitedB |= (1 << (nr2*m + nc2));
                 DFS(maze, visitedR, visitedB, R, new int[]{nr2, nc2}, cnt+1);
                 visitedB &= ~(1 << (nr2*m + nc2));
             }
             return;
         }
        
         // 파란 수레 고정 
         if(r2 == ebr && c2 == ebc) {
             for(int i=0; i<4; i++) {
                 int nr1 = r1 + dr[i];
                 int nc1 = c1 + dc[i]; 
                 if(!check(maze, visitedR, nr1, nc1)) continue;
                 if(r2 == nr1 && c2 == nc1) continue;
                 visitedR |= (1 << (nr1*m + nc1));
                 DFS(maze, visitedR, visitedB, new int[]{nr1, nc1}, B, cnt+1);
                 visitedR &= ~(1 << (nr1*m + nc1));
             }
             return;
         }
         
         // 모두 안맞을 경우
         for(int i=0; i<4; i++) { // 빨강의 4방향
             int nr1 = r1 + dr[i];
             int nc1 = c1 + dc[i];
             if(!check(maze, visitedR, nr1, nc1)) continue;    
             visitedR |= (1 << (nr1*m + nc1));
             for(int j=0; j<4; j++) { // 파랑의 4방향
                 int nr2 = r2 + dr[j];
                 int nc2 = c2 + dc[j];
                 if(!check(maze, visitedB, nr2, nc2)) continue;
                 if(nr1 == nr2 && nc1 == nc2) continue; // 같은 자리
                 if(r1 == nr2 && c1 == nc2 && r2 == nr1 && c2 == nc1) continue; // 스왑
                 visitedB |= (1 << (nr2*m + nc2));
                 DFS(maze, visitedR, visitedB, 
                     new int[]{nr1, nc1}, new int[]{nr2, nc2}, cnt+1);
                 visitedB &= ~(1 << (nr2*m + nc2));
             }
             visitedR &= ~(1 << (nr1*m + nc1));
         }
    }
    
    // 맵 밖 + 벽 + 방문한 곳인지 -> true면 가능
    public boolean check(int[][] maze, int visited, int r, int c) {
        if(r < 0 || r > n-1 || c < 0 || c > m-1) return false;
        if(maze[r][c] == 5) return false;
        if((visited & (1 << (r*m + c))) != 0) return false; 
        return true;
    }
}