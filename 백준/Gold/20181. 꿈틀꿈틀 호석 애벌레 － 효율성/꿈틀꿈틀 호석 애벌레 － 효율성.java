import java.util.*;
import java.io.*;
class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    long K = Long.parseLong(st.nextToken());

    st = new StringTokenizer(br.readLine());
    long[] arr = new long[N];

    for(int i=0; i<N; i++) {
      arr[i] = Long.parseLong(st.nextToken());
    }

    long[] S = new long[N];
    S[0] = arr[0];
    for(int i=1; i<arr.length; i++) {
      S[i] = S[i-1] + arr[i];
    }

    long[] dp = new long[N];

    int p1 = 0;
    int p2 = 0;
    while(p1 <= p2 && p2 < arr.length) {
      long sum = 0;
      if(p1 == 0) {
        sum = S[p2];
      }else {
        sum = S[p2] - S[p1-1];
      }

      if(sum < K) {
        if(p2 == 0) {
          dp[p2] = 0;
        }else {
          dp[p2] = Math.max(dp[p2], dp[p2 - 1]);
        }
        p2++;
      }else {
        long pre = 0;
        if(p1 != 0) {
          pre = dp[p1-1];
        }
        dp[p2] = Math.max(dp[p2], pre + sum - K);
        if(p1 == p2) {
          p2++;
          p1++;
        }else {
          p1++;
        }
      }
    }

    System.out.print(dp[dp.length-1]);
  }
}