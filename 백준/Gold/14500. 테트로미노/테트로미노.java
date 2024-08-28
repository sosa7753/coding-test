import java.util.*;
import java.io.*;

class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] visited;
    static int[][] map;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        visited = new boolean[N][M];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int answer = 0;        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                visited[i][j] = true;
                answer = Math.max(answer, DFS(i, j, 0));
                visited[i][j] = false;
                
                // ㅗ 모양 체크
                int cnt = 1;
                int sum = map[i][j];
                for(int k=0; k<4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    
                    if(nr < 0 || nr > N-1 || nc < 0 || nc > M-1) {
                        continue;
                    }
                    
                    sum += map[nr][nc];
                    cnt++;
                }
                
                if(cnt == 4) {
                    answer = Math.max(answer, sum);
                }else if(cnt == 5) {
                    for(int w = 0; w < 4; w++) {
                        answer = Math.max(answer, sum - map[i+dr[w]][j+dc[w]]);
                    }
                }               
            }
        }
        
        System.out.print(answer);     
    }
    
    public static int DFS(int r, int c, int cnt) {
        if(cnt == 3) {
            return map[r][c];
        }

        int result = 0;
        for(int i=0; i<4; i++) {
            int row = r + dr[i];
            int col = c + dc[i];
            
            if(row < 0 || row > N-1 || col < 0 || col > M-1) {
                continue;
            }
            
            if(visited[row][col]) {
                continue;
            }
            
            visited[row][col] = true;
            result = Math.max(result, DFS(row, col, cnt+1));
            visited[row][col] = false;
        }
        return result + map[r][c];
    }
}