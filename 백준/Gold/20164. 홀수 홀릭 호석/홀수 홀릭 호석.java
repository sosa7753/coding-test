import java.io.*;
class Main {
  static int max = Integer.MIN_VALUE;
  static int min = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String str = br.readLine();
    DFS(str, 0);

    System.out.print(min + " " + max);
  }

  public static void DFS(String s, int sum) {
    // 현재 수의 홀수 개수 세주기.
    int result = sum;
    for(int i=0; i<s.length(); i++) {
      int tmp = s.charAt(i) - '0';
      if(tmp%2 == 1) {
        result++;
      }
    }

    if(s.length() == 1) {
      max = Math.max(max, result);
      min = Math.min(min, result);
      return;
    }

    int next = 0;
    if(s.length() == 2) {
      for(int i=0; i<s.length(); i++) {
        next += s.charAt(i) - '0';
      }
      DFS(String.valueOf(next), result);
      return;
    }

    // 3 자리 이상의 문자열
    for(int i=0; i<s.length()-2; i++) {
      int value = Integer.parseInt(s.substring(0,i+1));
      for(int j=i+1; j<s.length()-1; j++) {
        int sub = Integer.parseInt(s.substring(i+1, j+1))
            + Integer.parseInt(s.substring(j+1));
        DFS(String.valueOf(value + sub), result);
      }
    }
  }
}