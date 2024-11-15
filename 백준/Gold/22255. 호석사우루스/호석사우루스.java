import java.util.*;
import java.io.*;
class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N;
    static int M;
    static int[] start = new int[2];
    static int[] end = new int[2];
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        st = new StringTokenizer(br.readLine());
        start[0] = Integer.parseInt(st.nextToken())-1;
        start[1] = Integer.parseInt(st.nextToken())-1;
        end[0] = Integer.parseInt(st.nextToken())-1;
        end[1] = Integer.parseInt(st.nextToken())-1;
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }        
        System.out.print(BFS());
    }
    
    public static int BFS() {
        int[][][] board = new int[N][M][3];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                for(int k=0; k<3; k++) {
                    board[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start[0],start[1], 0});
        board[start[0]][start[1]][0] = 0;
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
                    
            for(int i=0; i<4; i++) {
                if((now[2]+1)%3 == 1 && i%2 == 1) {
                    continue;
                }
                
                if((now[2]+1)%3 == 2 && i%2 == 0) {
                    continue;
                }
                
                int r = now[0] + dr[i];
                int c = now[1] + dc[i];
                
                if(r < 0 || r > N-1 || c < 0 || c > M-1) {
                    continue;
                }
                
                if(map[r][c] == -1) {
                    continue;
                }
                
                if(board[r][c][(now[2]+1)%3] > board[now[0]][now[1]][now[2]%3] + map[r][c]) {
                    board[r][c][(now[2]+1)%3] = board[now[0]][now[1]][now[2]%3] + map[r][c];
                    q.add(new int[] {r, c, now[2]+1});
                }
            }
        }
        
        int result = Integer.MAX_VALUE;
        for(int i=0; i<3; i++) {
            result = Math.min(result, board[end[0]][end[1]][i]);
        }
        
        if(result == Integer.MAX_VALUE) {
            return -1;
        }
        return result;
    }
}