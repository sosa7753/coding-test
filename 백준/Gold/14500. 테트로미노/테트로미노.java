import java.util.*;
import java.io.*;
class Main {
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N, M;
  static int[][] map;
  static boolean[][] visited;
  static int answer = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    visited = new boolean[N][M];
    map = new int[N][M];
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        Tspin(i, j);
        visited[i][j] = true;
        DFS(i, j, map[i][j], 1);
        visited[i][j] = false;
      }
    }

    System.out.print(answer);
  }

  public static void DFS(int row, int col, int value, int cnt) {
    if(cnt == 4) {
      answer = Math.max(answer, value);
      return;
    }


    for(int i=0; i<4; i++) {
      int r = row + dr[i];
      int c = col + dc[i];

      if(r < 0 || r > N -1 || c < 0 || c > M -1) {
        continue;
      }

      if(visited[r][c]) {
        continue;
      }

      visited[r][c] = true;
      DFS(r, c, value + map[r][c], cnt + 1);
      visited[r][c] = false;
    }
  }

  public static void Tspin(int row, int col) {
    int result = map[row][col];

    int cnt = 1;
    for(int i=0; i<4; i++) {
      int nr = row + dr[i];
      int nc = col + dc[i];

      if(nr < 0 || nr > N -1 || nc < 0 || nc > M -1) {
        continue;
      }
      result += map[nr][nc];
      cnt++;
    }

    if(cnt == 4) {
      answer = Math.max(answer, result);
    }else if(cnt == 5) {
      for(int i=0; i<4; i++) {
        answer = Math.max(answer, result - map[row + dr[i]][col + dc[i]]);
      }
    }
  }
}