import java.util.*;
import java.io.*;
class Main {
    static long INF = 1000000007L;
    static int N, L, R;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        long[][][] dp = new long[N+1][L+1][R+1]; // N번째 빌딩을 놓을 때, 왼쪽 개수, 오른쪽 개수
        dp[1][1][1] = 1;
        for(int i=2; i<=N; i++) { // i번쨰 빌딩을 놓을 때, 
            for(int l=1; l<=L; l++) { // 왼쪽 빌딩 수 
                for(int r=1; r<=R; r++) { // 오른쪽 빌딩 수 
                    dp[i][l][r] = (dp[i-1][l-1][r] + dp[i-1][l][r-1] + dp[i-1][l][r] * (i-2))%INF;
                }
            }
        }
        
        System.out.print(dp[N][L][R]);
    }
}