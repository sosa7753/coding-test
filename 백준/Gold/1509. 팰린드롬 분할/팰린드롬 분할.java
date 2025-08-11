import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();
        int n = s.length();
        char[] a = s.toCharArray();

        // pal[l][r] = s[l..r]가 팰린드롬인가?
        boolean[][] pal = new boolean[n][n];

        // 중심 확장으로 팰린드롬 전처리 (O(n^2))
        for (int center = 0; center < n; center++) {
            // 홀수 길이
            int l = center, r = center;
            while (l >= 0 && r < n && a[l] == a[r]) {
                pal[l][r] = true;
                l--; r++;
            }
            // 짝수 길이
            l = center; r = center + 1;
            while (l >= 0 && r < n && a[l] == a[r]) {
                pal[l][r] = true;
                l--; r++;
            }
        }

        // dp[i] = s[0..i] 최소 팰린드롬 분할 수
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            // j..i가 팰린드롬이면, 앞쪽은 dp[j-1] + 1
            for (int j = 0; j <= i; j++) {
                if (pal[j][i]) {
                    int prev = (j == 0) ? 0 : dp[j - 1];
                    if (prev != Integer.MAX_VALUE) {
                        dp[i] = Math.min(dp[i], prev + 1);
                    }
                }
            }
        }

        System.out.println(dp[n - 1]);
    }
}