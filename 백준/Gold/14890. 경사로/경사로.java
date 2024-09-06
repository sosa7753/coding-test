import java.util.*;
import java.io.*;
class Main {
  static int N;
  static int L;
  static int answer = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());

    int[][] map = new int[N][N];
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for(int i=0; i<N; i++) {
      if(possible(map[i])) {
        answer++;
      }

      int[] arr = new int[N];
      for(int j=0; j<N; j++) {
        arr[j] = map[j][i];
      }

      if(possible(arr)) {
        answer++;
      }
    }
    System.out.print(answer);
  }

  public static boolean possible(int[] arr) {
    int cnt = 1; // 이전 블록이 연속된 개수 
    int h = arr[0]; // 이전 블록 높이     
    int next = 0; // 경사로를 아래로 세울때 다음 인덱스 
      
    for(int i=1; i<arr.length; i++) {
      if(i < next) {
        continue;
      }

      if(h == arr[i]) {
        cnt++;
        continue;
      }

      if(h + 1 < arr[i] || h - 1 > arr[i]) { // 경사가 2차이 이상
        return false;
      }

      if(h + 1 == arr[i]) { // 높은 경사를 만남.
        if(cnt >= L) {
          cnt = 1;
          h = arr[i];
          continue;
        }else {
          return false;
        }
      }

      if(h - 1 == arr[i]) { // 낮은 경사를 만남.
        int p = i;
        while(p < Math.min(i+L, arr.length)) {
          if(arr[i] == arr[p]) {
            p++;
          }else {
            break;
          }
        }

        if(p - i >= L) { // 설치 가능
          h = arr[i];
          cnt = 0;
          next = p;
        }else {
          return false;
        }
      }
    }
    return true;
  }
}