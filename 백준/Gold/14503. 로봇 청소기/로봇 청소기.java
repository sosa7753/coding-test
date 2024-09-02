import java.util.*;
import java.io.*;
class Main {
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken()); // 행
    int M = Integer.parseInt(st.nextToken()); // 열

    int[] start = new int[2];
    int direct = -1;

    st = new StringTokenizer(br.readLine());
    start[0] = Integer.parseInt(st.nextToken());
    start[1] = Integer.parseInt(st.nextToken());
    direct = Integer.parseInt(st.nextToken());

    int[][] map = new int[N][M];
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int answer = 0;
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[] {start[0], start[1], direct});

    while(!queue.isEmpty()) {
      int[] now = queue.poll();

      if(map[now[0]][now[1]] == 0) {
        answer++;
        map[now[0]][now[1]] = 2; // 청소하면 2
      }

      int not = 0;
      int nd = now[2];
      for(int i=0; i<4; i++) { // 현 방향의 다음 반시계방향부터 순회
          int row = now[0] + dr[(nd + 3 - i)%4];
          int col = now[1] + dc[(nd + 3 - i)%4];

          if(map[row][col]==1 || map[row][col]==2) {
            continue;
          }

          not++;
          nd = (nd + 3 - i)%4;
          queue.offer(new int[] {row, col, nd});
          break;
      }

      if(not == 0) {
        int backR = now[0] - dr[now[2]];
        int backC = now[1] - dc[now[2]];
        if(map[backR][backC] == 1) {
          break;
        }
        queue.offer(new int[] {backR, backC, now[2]});
      }
    }

    System.out.println(answer);
  }
}