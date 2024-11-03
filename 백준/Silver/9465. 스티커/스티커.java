import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[2][n];
            
            for(int j=0; j<2; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<n; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            
            sb.append(dp(n, arr)).append("\n");
            
        }
        System.out.print(sb.toString());
    }
    
    public static int dp(int n, int[][] arr) {
        int[][] DP = new int[n][3];
        // 해당 위치에 3가지 0은 위에 떼내기, 1은 아래 떼내기, 2는 둘 다 안떼기
        DP[0][0] = arr[0][0];
        DP[0][1] = arr[1][0];
        DP[0][2] = 0;
        
        for(int i=1; i<n; i++) {
            DP[i][0] = Math.max(DP[i-1][1], DP[i-1][2]) + arr[0][i];
            DP[i][1] = Math.max(DP[i-1][0], DP[i-1][2]) + arr[1][i];
            DP[i][2] = Math.max(DP[i-1][0], Math.max(DP[i-1][1], DP[i-1][2]));
        }
        
        return Math.max(DP[n-1][0], Math.max(DP[n-1][1], DP[n-1][2]));        
    }
}