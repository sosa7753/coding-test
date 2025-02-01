import java.util.*;
import java.io.*;
class Main {
    static int N;
    static int[][] map;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
         
        dp = new int[N+1][N+1][3];
        map = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // dp
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                for(int k=0; k<=2; k++) { // 각 우유까지 마신 dp
                    // 마시기
                    if(map[i][j] == k) {
                      dp[i][j][k] = Math.max(dp[i][j][k], Math.max(dp[i-1][j][(k+2)%3], dp[i][j-1][(k+2)%3]) + 1);    
                      if(dp[i][j][k] == 1 && k != 0) { // 처음 마신 우유
                           dp[i][j][k] = 0;
                      }
                    }
                    
                    // 안마시기
                    dp[i][j][k] = Math.max(dp[i][j][k], Math.max(dp[i-1][j][k], dp[i][j-1][k]));
                }
            }
        }      
        
        int result = Math.max(dp[N][N][0], Math.max(dp[N][N][1], dp[N][N][2]));
        System.out.print(result);
    }
}