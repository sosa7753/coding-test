import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int t = Integer.parseInt(br.readLine());
        
        for(int i=0; i<t; i++) {
            int k = Integer.parseInt(br.readLine());
            int[] arr = new int[k];
            st = new StringTokenizer(br.readLine());
            
            for(int j=0; j<k; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            int[] sum = new int[k];
            sum[0] = arr[0];
            for(int j=1; j<sum.length; j++) {
                sum[j] = sum[j-1] + arr[j];
            }
                        
            int value = solve(k, arr, sum);
            System.out.println(value);
        }
    }
    
    public static int solve(int k, int[] arr, int[] sum) {
        int[][] DP = new int[k][k];
        for(int i=0; i<k; i++) {
            for(int j=0; j<k; j++) {
                if(i==j) {
                    continue;
                }
                DP[i][j] = Integer.MAX_VALUE;
            }
        }
        return dp(DP, sum, 0, k-1);
    }
    
    public static int dp(int[][] DP, int[] sum, int start, int end) {         
        if(DP[start][end] != Integer.MAX_VALUE) {
            return DP[start][end];
        }
                     
        for(int i=start; i<end; i++) {
            int s = 0;
            if(start == 0) {
                s = sum[end];
            }else {
                s = sum[end] - sum[start-1];
            }
            DP[start][end] = Math.min(DP[start][end], (dp(DP, sum, start, i) + dp(DP, sum, i+1, end) 
                                                      + s));
        }
        return DP[start][end];
    }
}