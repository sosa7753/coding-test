import java.util.*;
import java.io.*;
class Main {
  static long N;
  static String answer = "-1";
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Long.parseLong(br.readLine());

    Stack<String> stack = new Stack<>();
    int cnt = 0;
    while (N > 0) {
      if (cnt > 99) {
        break;
      }

      if ((N & 2) == 2) { // 2번째 비트가 1인 경우 없애기
        N -= 2;
        stack.push("[+]");
      } else if ((N & 1) == 1) { // 첫 번째 비트가 1인 경우 * 2 해주기
        N *= 2;
        stack.push("[/]");
      }else {
        N /= 2;
        stack.push("[*]");
      }

      cnt++;
    }

    if (!stack.isEmpty()) {
      System.out.println(cnt);
      StringBuilder sb = new StringBuilder();
      while (!stack.isEmpty()) {
        sb.append(stack.pop()).append(' ');
      }
      answer = sb.toString();
    }
    System.out.println(answer);
  }
}