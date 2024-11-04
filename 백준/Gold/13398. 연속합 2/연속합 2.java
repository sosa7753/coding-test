import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        
        int[][] dp = new int[n][2]; 
        dp[0][0] = 0;
        dp[0][1] = arr[0];
        
        int max = arr[0];        
        for(int i=1; i<dp.length; i++) {
            dp[i][0] = Math.max(dp[i-1][1], dp[i-1][0] + arr[i]);
            dp[i][1] = Math.max(dp[i-1][1] + arr[i], arr[i]);
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }
        System.out.print(max);
    }
}