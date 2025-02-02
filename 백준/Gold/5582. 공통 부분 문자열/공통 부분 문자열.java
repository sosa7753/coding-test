import java.util.*;
import java.io.*;
class Main {
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String first = br.readLine();
        String second = br.readLine();
        
        dp = new int[first.length()+1][second.length()+1];
        
        int max = 0;
        for(int i=1; i<=first.length(); i++) {
            for(int j=1; j<=second.length(); j++) {
                if(first.charAt(i-1) == second.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }   
        System.out.print(max);
    }
}