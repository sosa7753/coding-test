import java.util.*;
import java.io.*;
class Main {
  static int[] arr;
  static int max = 0;
  static int K;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    arr = new int[N];
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    DFS(0, 0);
    System.out.print(max);
  }

  public static void DFS(int idx, int tal) {
    if(idx == arr.length) {
      max = Math.max(max, tal);
      return;
    }

    DFS(idx+1, tal);
    // 종속적인 값 체크하기
    int result = 0;
    for(int i=idx; i<arr.length; i++) {
      result += arr[i];

      if(result < K) {
        continue;
      }
      DFS(i+1 ,tal+result-K);
      break;
    }
  }
}