import java.util.*;
import java.io.*;

class Main {
  static int[] dp;
  static int[][] map;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    map = new int[N][2]; // r+1 일에 T, P
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      map[i][0] = Integer.parseInt(st.nextToken());
      map[i][1] = Integer.parseInt(st.nextToken());
    }

    dp = new int[N+1];

    for(int i=0; i<N; i++) {
        if(i+map[i][0] <= N) {
          dp[i+map[i][0]] = Math.max(dp[i+map[i][0]], dp[i] + map[i][1]);
        }

        dp[i+1] = Math.max(dp[i+1], dp[i]);
    }

    System.out.println(dp[N]);
  }
}