import java.util.*;
import java.io.*;
class Main {
  static int[] dr = {-1, 0, 1, 0};
  static int[] dc = {0, 1, 0, -1};
  static int K,W,H;
  static int[][] m, ans;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());

    StringBuilder sb = new StringBuilder();
    for(int i=0; i<T; i++) {
      st = new StringTokenizer(br.readLine());
      K = Integer.parseInt(st.nextToken());
      W = Integer.parseInt(st.nextToken()); // 열
      H = Integer.parseInt(st.nextToken()); // 행

      m = new int[H][W];
      ans = new int[H][W];
      for(int a = 0; a<H; a++) {
        Arrays.fill(ans[a], Integer.MAX_VALUE);
      }
      Map<String, Integer> map = new HashMap<>();

      for(int j=0; j<K; j++) {
        String[] str = br.readLine().split(" ");
        map.put(str[0], Integer.parseInt(str[1]));
      }

      int sr = 0; int sc =0;
      for(int r=0; r<H; r++) {
        String[] str = br.readLine().split("");
        for(int c=0; c<W; c++) {
          if("E".equals(str[c])) {
            ans[r][c] = 0;
            sr = r; sc = c;
            continue;
          }
          m[r][c] = map.get(str[c]);
        }
      }

      sb.append(dijkstra(sr, sc)).append("\n");
    }
    
    System.out.print(sb);
  }

  public static int dijkstra(int sr, int sc) {
    boolean[][] visited = new boolean[H][W];
    PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> (x[2] - y[2]));
    pq.offer(new int[]{sr, sc, 0});

    while(!pq.isEmpty()) {
      int[] now = pq.poll();
      int r = now[0]; int c = now[1]; int v = now[2];

      if(isLine(r, c)) {
        return v;
      }

      if(visited[r][c]) {
        continue;
      }
      visited[r][c] = true;

      for(int i=0; i<4; i++) {
        int nr = r + dr[i];
        int nc = c + dc[i];
        if(nr < 0 || nr > H-1 || nc < 0 || nc > W-1){
          continue;
        }

        if(ans[nr][nc] > v + m[nr][nc]) {
          ans[nr][nc] = v + m[nr][nc];
          pq.offer(new int[]{nr, nc, ans[nr][nc]});
        }
      }
    }
    return -1;
  }

  public static boolean isLine(int r, int c) {
    if(r == 0 || r == H-1 || c == 0 || c == W-1) {
      return true;
    }
    return false;
  }
}