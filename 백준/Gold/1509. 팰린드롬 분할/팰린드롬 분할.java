import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();
        int n = s.length();
        char[] arr = s.toCharArray();

        boolean[][] pal = new boolean[n][n];

        // 길이가 1인 구간은 전부 팰린드롬
        for (int i = 0; i < n; i++) {
            pal[i][i] = true;
        }

        // 길이가 2인 구간
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                pal[i][i + 1] = true;
            }
        }

        // 길이가 3 이상인 구간
        for (int len = 3; len <= n; len++) { // 구간 길이
            for (int start = 0; start + len - 1 < n; start++) {
                int end = start + len - 1;
                if (arr[start] == arr[end] && pal[start + 1][end - 1]) {
                    pal[start][end] = true;
                }
            }
        }

        // 최소 분할 DP
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (pal[j][i]) {
                    dp[i] = Math.min(dp[i], (j == 0 ? 0 : dp[j - 1]) + 1);
                }
            }
        }

        System.out.println(dp[n - 1]);
    }
}