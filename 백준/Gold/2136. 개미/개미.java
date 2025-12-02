import java.util.*;
import java.io.*;
class Main {
  static int N,L;
  static int[] origin; // 원본 배열
  static Integer[] arr; // 좌표 정렬
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());

    arr = new Integer[N];
    origin = new int[N];
    for(int i=0; i<N; i++) {
      int val = Integer.parseInt(br.readLine());
      arr[i] = val; origin[i] = val;
    }

    Arrays.sort(arr, (x,y) -> (Math.abs(x) - Math.abs(y)));

    int lmin = 0; int rmin = L; // 음수 중 가장 작은 값, 양수중 가장 작은 값
    int lc = 0; int rc = 0; // 왼쪽으로 떨어지는 개수, 오른쪽으로 떨어지는 개수
    for(int i=0; i<N; i++) {
      if(arr[i] < 0) {
        lmin = Math.min(lmin, arr[i]);
        lc++;
      }else {
        rmin = Math.min(rmin, arr[i]);
        rc++;
      }
    }

    int time = 0; int value = 0;
    if(Math.abs(lmin) > L - rmin) {
      time = Math.abs(lmin);
      value = arr[lc-1];
    }else {
      time = L - rmin;
      value = arr[N - rc];
    }

    int num = 0;
    for(int i=0; i<origin.length; i++) {
      if(origin[i] == value) {
        num = i + 1;
        break;
      }
    }

    StringBuilder sb = new StringBuilder();
    sb.append(num).append(' ').append(time);
    System.out.print(sb.toString());
  }
}