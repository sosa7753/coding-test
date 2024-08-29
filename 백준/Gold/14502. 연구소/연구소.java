import java.util.*;
import java.io.*;

class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static int N;
    static int M;
    static Queue<int[]> queue =new LinkedList<>();
    static int max = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];       
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            } 
        }
        
        DFS(0);
        System.out.print(max);
    }
    
    public static void DFS(int cnt) {
        if(cnt == 3) {
            BFS();
            return;
        }
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 0) {
                    map[i][j] = 1;
                    DFS(cnt+1);
                    map[i][j] = 0;
                }
            }
        }
    } 
    
    public static void BFS() {
        int[][] copy = new int[N][M];
        for(int i=0; i<N; i++) {
            copy[i] = map[i].clone();
        }
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(copy[i][j] == 2) {
                    queue.offer(new int[] {i, j});
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            
            for(int i=0; i<4; i++) {
                int row = now[0] + dr[i];
                int col = now[1] + dc[i];
                
                if(row < 0 || row > N-1 || col < 0 || col > M-1) {
                    continue;
                }
                
                if(copy[row][col] == 0) {
                    copy[row][col] = 2;
                    queue.offer(new int[] {row, col});
                }          
            }
        }
        cal(copy);
    }
    
    public static void cal(int[][] copy) {
        int safe = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(copy[i][j] == 0) {
                    safe++;
                }
            }
        }
        max = Math.max(max, safe);
    }
}