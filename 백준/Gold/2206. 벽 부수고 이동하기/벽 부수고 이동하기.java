import java.util.*;
import java.io.*;
class Main {
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N;
  static int M;
  static int[][] map;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      for (int j = 0; j < M; j++) {
        map[i][j] = str.charAt(j) - '0';
      }
    }

    System.out.println(BFS());
  }

  public static int BFS() {
    boolean[][][] visited = new boolean[N][M][2];
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{0, 0, 0, 1}); // 행, 열, 파괴 여부, 이동 횟수
    visited[0][0][0] = true;

    while (!queue.isEmpty()) {
      int[] now = queue.poll();

      if (now[0] == N - 1 && now[1] == M - 1) {
        return now[3];
      }

      for (int i = 0; i < 4; i++) {
        int r = now[0] + dr[i];
        int c = now[1] + dc[i];

        if (r < 0 || r > N - 1 || c < 0 || c > M - 1) {
          continue;
        }

        if (visited[r][c][now[2]]) {
          continue;
        }

        if (map[r][c] == 0) { // 갈 수 있음.
          queue.offer(new int[]{r, c, now[2], now[3] + 1});
          visited[r][c][now[2]] = true;
        } else { // 벽인 경우 부술 수 있으면 이동
          if (now[2] == 0 && !visited[r][c][1]) {
            queue.offer(new int[]{r, c, 1, now[3] + 1});
            visited[r][c][1] = true;
          }
        }
      }
    }
    return -1;
  }
}