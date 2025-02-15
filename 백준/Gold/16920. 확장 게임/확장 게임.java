import java.io.*;
import java.util.*;
class Main {

  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N, M, P;
  static int[] move;
  static int[][] map; // 1~9는 성. 10은 벽
  static List<List<int[]>> start = new ArrayList<>();
  static int[] answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    P = Integer.parseInt(st.nextToken());

    move = new int[P + 1];
    answer = new int[P + 1];
    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= P; i++) {
      move[i] = Integer.parseInt(st.nextToken());
    }

    for (int i = 0; i <= P; i++) {
      start.add(new ArrayList<>());
    }

    map = new int[N][M];
    for (int i = 0; i < N; i++) {
      String s = br.readLine();
      for (int j = 0; j < M; j++) {
        char c = s.charAt(j);
        if (c >= '1' && c <= '9') {
          map[i][j] = c - '0';
          start.get(map[i][j]).add(new int[]{i, j, map[i][j], 1});
          answer[map[i][j]]++;
        } else if (c == '#') {
          map[i][j] = 10;
        }
      }
    }

    BFS();

    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < answer.length; i++) {
      sb.append(answer[i]).append(" ");
    }
    System.out.println(sb.toString());
  }

  public static void BFS() {
    Queue<int[]> q = new LinkedList<>();
    for (int i = 1; i <= P; i++) {
      for (int j = 0; j < start.get(i).size(); j++) {
        q.offer(start.get(i).get(j));
      }
    }

    Queue<int[]> small = new LinkedList<>();
    while (!q.isEmpty()) {
      int n = q.peek()[2];
      while(!q.isEmpty() && q.peek()[2] == n) {
        small.offer(q.poll());
      }
      while (!small.isEmpty()) {
        int[] now = small.poll();
        int r = now[0];
        int c = now[1];
        int num = now[2]; // 성의 번호
        int level = now[3];
        for (int j = 0; j < 4; j++) {
          int nr = r + dr[j];
          int nc = c + dc[j];

          if (nr < 0 || nr > N - 1 || nc < 0 || nc > M - 1) {
            continue;
          }

          if (map[nr][nc] == 0) {
            map[nr][nc] = num;
            answer[num]++;
            if (level < move[num]) {
              small.offer(new int[]{nr, nc, num, level + 1});
            } else {
              q.offer(new int[]{nr, nc, num, 1});
            }
          }
        }
      }
    }
  }
}