import java.util.*;
import java.io.*;

class Main {
    static int[] dr = {0, 0, -1, 1}; // 동서북남
    static int[] dc = {1,- 1 ,0, 0};
    static int[][] map;
    static int N;
    static int M;
    static int[] start;
    static int[] dice;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        
        start = new int[2];
        start[0] = Integer.parseInt(st.nextToken());
        start[1] = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(st.nextToken()); // 명령 개수
        
        map = new int[N][M];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++)  {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int[] order = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            order[i] = Integer.parseInt(st.nextToken());           
        }
        
        dice = new int[6];
        Arrays.fill(dice, 0);
        
        // 명령하기
        for(int i=0; i<K; i++) {
            move(start[0], start[1], order[i] - 1);
        }
        
    }
    // 현재 위치, 갈 방향 
    public static void move(int r, int c, int direct) {
        int nr = r + dr[direct];
        int nc = c + dc[direct];
        
        if(nr < 0 || nr > N - 1 || nc < 0 || nc > M -1) {
            return;
        }
        
        int tmp = 0;
        if(direct == 0) { // 동
            tmp = dice[5];
            dice[5] = dice[3];
            dice[3] = dice[2];
            dice[2] = dice[1];
            dice[1] = tmp;          
        }else if(direct == 1) { // 서
            tmp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[3];
            dice[3] = dice[5];
            dice[5] = tmp;
        }else if(direct == 2) { // 북
            tmp = dice[0];
            dice[0] = dice[2];
            dice[2] = dice[4];
            dice[4] = dice[5];
            dice[5] = tmp;
       }else if(direct == 3) { // 남
            tmp = dice[5];
            dice[5] = dice[4];
            dice[4] = dice[2];
            dice[2] = dice[0];
            dice[0] = tmp;
        }
        
        if(map[nr][nc] == 0) {
            map[nr][nc] = dice[5];     
        }else {
            dice[5] = map[nr][nc];
            map[nr][nc] = 0;
        }
        
        start[0] = nr;
        start[1] = nc;
        System.out.println(dice[2]);
    }
}