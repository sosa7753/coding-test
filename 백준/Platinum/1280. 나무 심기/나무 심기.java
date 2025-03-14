import java.util.*;
import java.io.*;
class Main {
  static long INF = 1000000007L;
  static int max = 200000;
  static int N;
  static long[] cnt;
  static long[] sum;
  static long answer = 1;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());

    cnt = new long[max*4];
    sum = new long[max*4];

    int first = Integer.parseInt(br.readLine());
    cntUpdate(first, 1, 0, max);
    sumUpdate(first, 1, 0, max);

    for(int i=2; i<=N; i++) {
      int value = Integer.parseInt(br.readLine());
      long left = value * getCnt(1, 0, max, 0, value-1) - getSum(1, 0, max, 0, value-1);
      long right = getSum(1, 0, max, value+1, max) - value * getCnt(1, 0, max, value+1, max);

      answer = (answer * ((left + right) % INF))%INF;

      cntUpdate(value, 1, 0, max);
      sumUpdate(value, 1, 0, max);
    }

    System.out.print(answer);
  }

  // start, end 는 트리 관리 범위
  public static long cntUpdate(int value, int idx, int start, int end) {
    if(value < start || end < value) { // 아예 벗어난 범위
      return cnt[idx];
    }

    if(start == end) {
       return cnt[idx] = cnt[idx]+1;
    }

    int mid = (start + end)/2;
    return cnt[idx] = cntUpdate(value, idx * 2, start, mid) + cntUpdate(value, idx * 2 + 1, mid+1, end);
  }

  public static long getCnt(int idx, int start, int end, int L, int R) {
    if(R < start || end < L) {
      return 0;
    }

    if(L<=start && end<=R) {
      return cnt[idx];
    }

    int mid = (start + end)/2;
    return getCnt(idx*2, start, mid, L, R) + getCnt(idx*2+1, mid+1, end, L, R);
  }

  public static long sumUpdate(int value, int idx, int start, int end) {
    if (value < start || end < value) { // 아예 벗어난 범위
      return sum[idx];
    }

    if (start == end) {
      return sum[idx] += value;
    }

    int mid = (start + end) / 2;
    return sum[idx] = sumUpdate(value, idx * 2, start, mid) + sumUpdate(value, idx * 2 + 1, mid + 1, end);
  }

  public static long getSum(int idx, int start, int end, int L, int R) {
    if(R < start || end < L) {
      return 0;
    }

    if(L<=start && end<=R) {
      return sum[idx];
    }

    int mid = (start + end)/2;
    return getSum(idx*2, start, mid, L, R) + getSum(idx*2+1, mid+1, end, L, R);
  }
}