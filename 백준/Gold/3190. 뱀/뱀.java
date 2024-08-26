import java.util.*;
import java.io.*;

class Main {
  static int[] dr = {0, 1, 0, -1}; // 우 하 좌 상
  static int[] dc = {1, 0, -1, 0};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine()); // 맵 크기
    int K = Integer.parseInt(br.readLine()); // 사과 개수

    int[][] map = new int[N][N];
    boolean[][] visited = new boolean[N][N];

    for (int i = 0; i < K; i++) { // 사과 정보 입력
      st = new StringTokenizer(br.readLine());

      int r = Integer.parseInt(st.nextToken()) -1;
      int c = Integer.parseInt(st.nextToken()) - 1;
      map[r][c] = 1;
    }

    int D = Integer.parseInt(br.readLine()); // 방향 전환 횟수

    Queue<int[]> turn = new LinkedList<>();
    for (int i = 0; i < D; i++) {
      String[] str = br.readLine().split(" ");

      if ("L".equals(str[1])) {
        turn.offer(new int[]{Integer.parseInt(str[0]), -1});
      } else {
        turn.offer(new int[]{Integer.parseInt(str[0]), 1});
      }
    }

    // 게임 시작
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{0, 0, 0}); // r,c,d
    visited[0][0] = true;

    List<int[]> last = new ArrayList<>();
    last.add(new int[]{0, 0});

    int time = 0;
    while (!queue.isEmpty()) {
      int[] now = queue.poll();

      int row = now[0] + dr[now[2]];
      int col = now[1] + dc[now[2]];

      if (row < 0 || row > N - 1 || col < 0 || col > N - 1) { // 벽에 부딪히기
        break;
      }

      if (visited[row][col]) { // 자기 몸에 부딪히기
        break;
      }

      if (map[row][col] == 1) {
        map[row][col] = 0;
      } else {
        int[] l = last.remove(0);
        visited[l[0]][l[1]] = false;
      }

      visited[row][col] = true;
      last.add(new int[]{row, col});
      time++;

      if (!turn.isEmpty() && turn.peek()[0] == time) {
        queue.offer(new int[]{row, col, (now[2] + turn.peek()[1] + 4) % 4});
        turn.poll();
      } else {
        queue.offer(new int[]{row, col, now[2]});
      }
    }

    System.out.print(time + 1);
  }
}
