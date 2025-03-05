import java.io.*;
import java.util.*;
class Main {
  static int[] dr = {-1, 0, 0, 1};
  static int[] dc = {0, -1, 1, 0};
  static int N;
  static int[][] map;
  static int size = 2;
  static int time = 0;
  static int eat = 2;
  static int[] start = new int[2];
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    N = Integer.parseInt(br.readLine());
    map = new int[N][N];

    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if(map[i][j] == 9) {
          map[i][j] = 0;
          start[0] = i;
          start[1] = j;
        }
      }
    }

    boolean check = true;
    while(check) {
      check = BFS();
    }

    System.out.print(time);
  }

  public static boolean BFS() {
    boolean[][] visited = new boolean[N][N];
    PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> {
      if (x[2] == y[2] && x[0] == y[0]) {
        return x[1] - y[1];
      }else if(x[2] == y[2]) {
        return x[0] - y[0];
      }else {
        return x[2] - y[2];
      }
    });

    pq.offer(new int[]{start[0],start[1],0});
    visited[start[0]][start[1]] = true;

    while(!pq.isEmpty()) {
      int[] now = pq.poll();
      int r = now[0];
      int c = now[1];
      int cnt = now[2];

      if(map[r][c] > 0 && map[r][c] < size) {
        if(eat == 1) {
          size++;
          eat = size;
        }else {
          eat--;
        }
        start[0] = r;
        start[1] = c;
        map[r][c] = 0;
        time += cnt;
        return true;
      }

      for(int i=0; i<4; i++) {
        int nr = r + dr[i];
        int nc = c + dc[i];

        if(nr < 0 || nr > N-1 || nc < 0 || nc > N-1) {
          continue;
        }

        if(visited[nr][nc]) {
          continue;
        }

        if(map[nr][nc] > size) {
          continue;
        }

        visited[nr][nc] = true;
        pq.offer(new int[]{nr, nc, cnt+1});
      }
    }
    return false;
  }
}