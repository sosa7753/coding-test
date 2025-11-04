import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine(); // 정방향 
        String r = new StringBuilder(s).reverse().toString(); // 역방향 문자열
        
        int[][] dp = new int[N+1][N+1]; // s의 r번째 문자와 r의 c번째 문자까지 최대 공통 부분 수열 길이
        for(int i=1; i<=N; i++) {
            char a = s.charAt(i-1);
            for(int j=1; j<=N; j++) {
                char b = r.charAt(j-1);
                if(a == b) dp[i][j] = dp[i-1][j-1] + 1; // 마지막 문자가 같다면,
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]); // 다를 경우 각각 다른 문자열과 비교
            }
        }
        System.out.print(N - dp[N][N]);
    }
}