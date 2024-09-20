import java.util.*;
import java.io.*;
class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());

    int[] happyLeft = new int[N+2]; // 1~idx 까지의 최소 행복도
    happyLeft[0] = Integer.MAX_VALUE;
    int[] happyRight = new int[N+2]; // idx ~ N까지의 최대 행복도
    happyRight[N+1] = -1;

    int[] sickLeft = new int[N+2]; // 1~idx까지의 최대 피로도
    sickLeft[0] = -1;
    int[] sickRight = new int[N+2]; // idx ~ N까지의 최소 피로도
    sickRight[N+1] = Integer.MAX_VALUE;

    int[] happy = new int[N+2];
    int[] sick = new int[N+2];

    for(int i=1; i<=N; i++) {
      st = new StringTokenizer(br.readLine());

      int nh = Integer.parseInt(st.nextToken());
      int ns = Integer.parseInt(st.nextToken());

      happy[i] = nh;
      sick[i] = ns;

      if(nh == 0) {
        happyLeft[i] = happyLeft[i-1];
      }else {
        happyLeft[i] = Math.min(happyLeft[i-1], nh);
      }

      if(ns == 0) {
        sickLeft[i] = sickLeft[i-1];
      }else {
        sickLeft[i] = Math.max(sickLeft[i-1], ns);
      }
    }


    for(int i=N; i>=1; i--) {
      if(happy[i] == 0) {
        happyRight[i] = happyRight[i+1];
      }else {
        happyRight[i] = Math.max(happyRight[i+1], happy[i]);
      }

      if(sick[i] == 0) {
        sickRight[i] = sickRight[i+1];
      }else {
        sickRight[i] = Math.min(sickRight[i+1], sick[i]);
      }
    }

    int answer = -1;
    for(int i=N-1; i>=1; i--) {
      if(happyLeft[i] > happyRight[i+1] && sickLeft[i] < sickRight[i+1]) {
        answer = i;
        break;
      }
    }
    System.out.println(answer);
  }
}