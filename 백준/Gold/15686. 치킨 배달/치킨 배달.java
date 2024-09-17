import java.util.*;
import java.io.*;
class Main {
  static int N;
  static int M;
  static List<int[]> home = new ArrayList<>();
  static List<int[]> chicken = new ArrayList<>();
  static boolean[] open;
  static int min = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        int value = Integer.parseInt(st.nextToken());
        if(value == 1) {
          home.add(new int[] {i, j});
        }else if(value == 2) {
          chicken.add(new int[] {i, j});
        }
      }
    }

    open = new boolean[chicken.size()];

    DFS(0, 0);
    System.out.println(min);
  }

  public static void DFS(int start, int cnt) {
    if(cnt == M) {
      min = Math.min(min, cal());
      return;
    }

    for(int i=start; i<chicken.size(); i++) {
      open[i] = true;
      DFS(i+1, cnt+1);
      open[i] = false;
    }
  }

  public static int cal() {
    int result = 0;
    for(int i=0; i<home.size(); i++) {
      int tmp = Integer.MAX_VALUE;
      for(int j=0; j<chicken.size(); j++) {
        if(!open[j]) {
          continue;
        }

        int distance = (Math.abs(chicken.get(j)[0] - home.get(i)[0])
            + Math.abs(chicken.get(j)[1] - home.get(i)[1]));

        tmp = Math.min(tmp, distance);
      }
      result += tmp;
    }
    return result;
  }
}