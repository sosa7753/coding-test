import java.util.*;
import java.io.*;
class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int[] arr = new int[N];

    for(int i=0; i<N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int[] S = new int[N];
    S[0] = arr[0];
    for(int i=1; i<arr.length; i++) {
      S[i] = S[i-1] + arr[i];
    }

    int[] dp = new int[N];

    int p1 = 0;
    int p2 = 0;
    while(p1 <= p2 && p2 < arr.length) {
      int sum = 0;
      if(p1 == 0) {
        sum = S[p2];
      }else {
        sum = S[p2] - S[p1-1];
      }

      if(sum < K) {
        if(p2 == 0) {
          dp[p2] = 0;
        }else {
          if(p1 != 0) {
            dp[p2] = Math.max(dp[p2], dp[p1 - 1]);
          }
        }
        p2++;
      }else {
        int pre = 0;
        if(p1 != 0) {
          pre = dp[p1-1];
        }
        dp[p2] = Math.max(dp[p2], pre + sum - K);
        if(p1 == p2) {
          p2++;
        }else {
          p1++;
        }
      }
    }

    System.out.print(dp[dp.length-1]);
  }
}