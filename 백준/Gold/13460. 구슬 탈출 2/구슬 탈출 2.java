import java.util.*;
import java.io.*;
class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 행
        int M = Integer.parseInt(st.nextToken()); // 열
        
        char[][] map = new char[N][M];
     
        int[] red = new int[2];
        int[] blue = new int[2];
        
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                char c = str.charAt(j);
                map[i][j] = c;
                
                if(c == 'R') {
                    red[0] = i;
                    red[1] = j;
                }else if(c == 'B') {
                    blue[0] = i;
                    blue[1] = j;
                }      
            }
        }
          
        System.out.print(BFS(red, blue, map));   
    }
    
    public static int BFS(int[] red, int[] blue, char[][] map) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {red[0], red[1], blue[0], blue[1], 0});
               
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            
            if(now[4] == 10) { // 10번 했으면 패스 
               continue;
            }
                      
            for(int i=0; i<4; i++) {
                int rowR = now[0];
                int colR = now[1];
                int rowB = now[2];
                int colB = now[3];
                
                int moveR = 0;
                int moveB = 0;
                
                boolean isR = false;
                boolean isB = false;
                            
                // 레드
                while(true) {
                    rowR += dy[i];
                    colR += dx[i];               
                    if(map[rowR][colR] == '#') { // 벽을 만남
                        rowR -= dy[i];
                        colR -= dx[i];
                        break;
                    }
                    moveR++;
                    
                    if(map[rowR][colR] == 'O') { // 구멍에 빠짐 
                        isR = true;
                        break;
                    }
                }
                
                // 블루
                while(true) {
                    rowB += dy[i];
                    colB += dx[i];               
                    if(map[rowB][colB] == '#') { // 벽을 만남
                        rowB -= dy[i];
                        colB -= dx[i];
                        break;
                    }
                    moveB++;
                    
                    if(map[rowB][colB] == 'O') { // 구멍에 빠짐 
                        isB = true;
                        break;
                    }
                }
                
                if(isB) {
                    continue;
                }else {
                    if(isR) {
                        return now[4] + 1;
                    }                  
                }
                
                // 처음과 같은 위치면 패스
                if(now[0] == rowR && now[1] == colR && now[2] == rowB && now[3] == colB) {
                    continue;
                }
                
                // 구슬이 겹치면 조정 
                if(rowR == rowB && colR == colB) {
                    if(i==0) {
                        if(moveR < moveB) { // B가 더 많이 움직임(더 뒤에 있음)
                            rowB++;
                        }else {
                            rowR++;
                        }
                    }else if(i==1) {
                        if(moveR < moveB) { // B가 더 많이 움직임(더 뒤에 있음)
                            colB--;
                        }else {
                            colR--;
                        }
                    }else if(i==2) {
                        if(moveR < moveB) { // B가 더 많이 움직임(더 뒤에 있음)
                            rowB--;
                        }else {
                            rowR--;
                        }
                    }else {
                        if(moveR < moveB) { // B가 더 많이 움직임(더 뒤에 있음)
                            colB++;
                        }else {
                            colR++;
                        }
                    }
                }
                
                queue.offer(new int[] {rowR, colR, rowB, colB, now[4]+1});             
             }
        }
        return -1;
    }
}