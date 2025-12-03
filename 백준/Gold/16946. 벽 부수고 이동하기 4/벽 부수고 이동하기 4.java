import java.util.*;
import java.io.*;
class Main {
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int N,M;
  static int[][] board;
  static int[][] result;
  static Map<Integer, Integer> map = new HashMap<>();
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    board = new int[N][M];
    for(int i=0; i<N; i++) {
      String s = br.readLine();
      String[] str = s.split("");
      for(int j=0; j<M; j++) {
        board[i][j] = Integer.parseInt(str[j]);
      }
    }

    int cnt = 2;
    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        if(board[i][j] == 0) {
          BFS(i, j, cnt);
          cnt++;
        }
      }
    }

    result = new int[N][M];
    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        int sum = 0;
        int val = board[i][j];
        if(val == 1) {
          Set<Integer> set = new HashSet<>();
          for(int k=0; k<4; k++) {
            int nr = i + dr[k];
            int nc = j + dc[k];
            if(nr < 0 || nr > N-1 || nc < 0 || nc > M-1) {
              continue;
            }

            if(board[nr][nc] == 1) {
              continue;
            }

            if(set.contains(board[nr][nc])) {
              continue;
            }

            sum += map.get(board[nr][nc]);
            set.add(board[nr][nc]);
          }
          result[i][j] = (sum + 1)%10;
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        sb.append(result[i][j]);
        if(j==M-1) sb.append("\n");
      }
    }
    System.out.print(sb);
  }

  public static void BFS(int r, int c, int cnt) {
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{r, c});
    board[r][c] = cnt;

    int sum = 1;
    while(!q.isEmpty()) {
      int[] now = q.poll();
      int sr = now[0]; int sc = now[1];

      for(int i=0; i<4; i++) {
        int nr = sr + dr[i];
        int nc = sc + dc[i];
        if(nr < 0 || nr > N-1 || nc < 0 || nc > M-1) {
          continue;
        }

        if(board[nr][nc] == 0) {
          board[nr][nc] = cnt;
          sum++;
          q.offer(new int[]{nr, nc});
        }
      }
    }

    map.put(cnt, sum);
  }
}