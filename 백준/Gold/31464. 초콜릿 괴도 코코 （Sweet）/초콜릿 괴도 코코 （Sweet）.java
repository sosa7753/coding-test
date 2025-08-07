import java.util.*;
import java.io.*;
class Main {
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int[][] map;
  static int N;
  static int answer = 0;
  static int total = 0;
  static PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> {
    if(x[0] == y[0]) {
      return x[1] - y[1];
    }else {
      return x[0] - y[0];
    }
  });
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    map = new int[N][N];

    for(int i=0; i<N; i++) {
      String s = br.readLine();
      for(int j=0; j<N; j++) {
        if(s.charAt(j) == '#') {
          map[i][j] = 1;
          total++;
        }
      }
    }

    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if(map[i][j]==1) {
          map[i][j]=0;
          if(BFS()) {
            pq.offer(new int[]{i+1,j+1});
            answer++;
          }
          map[i][j]=1;
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    sb.append(answer).append("\n");
    while(!pq.isEmpty()) {
      int[] now = pq.poll();
      sb.append(now[0]).append(" ").append(now[1]);
      if(!pq.isEmpty()) {
        sb.append("\n");
      }
    }
    System.out.print(sb);
  }

  public static boolean BFS() {
    boolean[][] visited = new boolean[N][N];
    Queue<int[]> q = new LinkedList<>();

    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if(map[i][j] == 0) {
          continue;
        }
        q.offer(new int[]{i,j, -1, -1});
        visited[i][j] = true;
        int cnt = 1;

        while(!q.isEmpty()) {
          int[] now = q.poll();
          int r = now[0]; int c = now[1]; int pr = now[2]; int pc = now[3];

          for(int k=0; k<4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];

            if(nr < 0 || nr > N-1 || nc < 0 || nc > N-1) {
              continue;
            }

            if(map[nr][nc] == 0) {
              continue;
            }

            if(visited[nr][nc]) {
              if(nr == pr && nc == pc) {
                continue;
              }
              return false;
            }
            visited[nr][nc] = true;
            q.offer(new int[]{nr, nc, r, c});
            cnt++;
          }
        }
        return cnt == total-1;
      }
    }
    return false;
  }
}