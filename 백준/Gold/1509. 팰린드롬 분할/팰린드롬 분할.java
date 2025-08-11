import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int n = str.length();
        char[] c = str.toCharArray();

        boolean[][] pal = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            pal[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            if (c[i] == c[i + 1]) {
                pal[i][i + 1] = true;
            }
        }

        // 길이가 3 이상인 구간
        for (int i= 3; i <= n; i++) { // 길이 
            for (int j = 0; j + i - 1 < n; j++) { // 시작 위치 
                int end = j + i - 1; // 끝 위치
                if (c[j] ==  c[end] && pal[j + 1][end - 1]) {
                    pal[j][end] = true;
                }
            }
        }

        // 최소 분할 DP
        int[] dp = new int[n];
        Arrays.fill(dp, 3000);

        for (int i = 0; i < n; i++) { // 
            for (int j = 0; j <= i; j++) { // 분할 구간 
                if (pal[j][i]) {
                    dp[i] = Math.min(dp[i], (j == 0 ? 0 : dp[j - 1]) + 1);
                }
            }
        }

        System.out.println(dp[n - 1]);
    }
}