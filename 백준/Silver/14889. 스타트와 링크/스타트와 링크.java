import java.util.*;
import java.io.*;
class Main {
  static int[][] map;
  static boolean[] visited;
  static int min = Integer.MAX_VALUE;
  static int N;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());

    map = new int[N][N];
    visited = new boolean[N];
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    DFS(0, 0);
    System.out.print(min);
  }

  public static void DFS(int idx, int cnt) {
    if(cnt == N/2) {
      cal();
      return;
    }

    for(int i=idx; i<N; i++) {
      if(!visited[i]) {
        visited[i] = true;
        DFS(i+1, cnt+1);
        visited[i] = false;
      }
    }
  }

  public static void cal() {
    int team1 = 0;
    int team2 = 0;
    for(int i=0; i<N-1; i++) {
      for(int j=i+1; j<N; j++) {
        if(visited[i]&& visited[j]) {
          team1 += map[i][j] + map[j][i];
        }

        if(!visited[i]&&!visited[j]) {
          team2 += map[i][j] + map[j][i];
        }
      }
    }

    int gap = Math.abs(team1 - team2);

    if(gap == 0) {
      System.out.print(0);
      System.exit(0);
    }
    min = Math.min(min, gap);
  }
}