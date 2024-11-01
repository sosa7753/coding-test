import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[][] W = new int[N+1][2]; // 무게 가치
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i][0] = Integer.parseInt(st.nextToken());
            W[i][1] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(W, (x,y) -> (x[0] - y[0]));
        
        int[][] dp = new int[N+1][K+1];
        
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=K; j++) {
                // 언제부터였지
                if(W[i][0] > j) {
                    dp[i][j] = dp[i-1][j];
                    continue;
                }
                
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-W[i][0]] + W[i][1]); 
            }
        }   
        
        System.out.print(dp[N][K]);
    }
}